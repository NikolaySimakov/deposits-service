package ru.mts.customerservice.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import ru.mts.starter.dao.BankAccountRepository;
import ru.mts.starter.dao.CustomerRepository;
import ru.mts.starter.dao.DepositTypeRepository;
import ru.mts.starter.dao.TypePercentPaymentRepository;
import ru.mts.starter.dto.CustomerDto;
import ru.mts.starter.dto.DepositDto;
import ru.mts.starter.dto.DepositTermsDto;
import ru.mts.starter.entity.*;
import ru.mts.starter.enums.DepositDurationEnum;
import ru.mts.starter.enums.PaymentPeriodEnum;
import ru.mts.starter.mapper.BankAccountMapper;
import ru.mts.starter.mapper.CustomerMapper;
import ru.mts.starter.mapper.DepositTypeMapper;
import ru.mts.starter.mapper.TypePercentPaymentMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final BankAccountRepository bankAccountRepository;
    private final TypePercentPaymentRepository typePercentPaymentRepository;
    private final DepositTypeRepository depositTypeRepository;

    public CustomerService(CustomerRepository customerRepository, BankAccountRepository bankAccountRepository, TypePercentPaymentRepository typePercentPaymentRepository, DepositTypeRepository depositTypeRepository) {
        this.customerRepository = customerRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.typePercentPaymentRepository = typePercentPaymentRepository;
        this.depositTypeRepository = depositTypeRepository;
    }

    public List<CustomerDto> getCustomers() {
        return customerRepository.findAll().stream()
                .map(CustomerMapper::toDto)
                .collect(Collectors.toList());
    }

    public void addCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        BankAccount bankAccount = new BankAccount();
        bankAccount.setNumBankAccounts(customerDto.getNumBankAccounts());
        bankAccount.setAmount(customerDto.getAmount());

        customer.setBankAccount(bankAccount);
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        bankAccountRepository.save(bankAccount);
        customerRepository.save(customer);
    }

    public Boolean verifyCustomerBySmsCode(String code) {

        RestClient restClient = RestClient.create();
        ResponseEntity<Boolean> result = restClient.get()
                .uri("http://localhost:3001/api/verify?code={code}", code)
                .retrieve()
                .toEntity(Boolean.class);

        return result.getBody();

//        return createRequest(phoneNumber);
    }

    public boolean validateDepositAmount(String phone, BigDecimal depositAmount) {
        BigDecimal currentAmount = customerRepository.findAmountByPhoneNumber(phone);
        return depositAmount.compareTo(currentAmount) <= 0;
    }

    public void subtractDepositAmount(String phone, BigDecimal depositAmount) {
        Customer customer = customerRepository.findByPhoneNumber(phone);
        BankAccount bankAccount = customer.getBankAccount();
        BigDecimal newBalance = bankAccount.getAmount().subtract(depositAmount);
        bankAccount.setAmount(newBalance);
        customer.setBankAccount(bankAccount);
        customerRepository.save(customer);
    }

    public void createDeposit(String phone, DepositTermsDto depositTerms) {
        RestClient restClient = RestClient.create();

        DepositDto depositDto = new DepositDto();

        Customer customer = customerRepository.findByPhoneNumber(phone);
        DepositType depositType = depositTypeRepository
                .findByDepositsTypesName(depositTerms.getDepositType());
        TypePercentPayment typePercentPayment = typePercentPaymentRepository
                .findByTypePercentPaymentPeriod(depositTerms.getPaymentPeriod());


        // set ids
        depositDto.setDepositAccount(BankAccountMapper.toDto(customer.getBankAccount()));
        depositDto.setPercentPaymentAccount(BankAccountMapper.toDto(customer.getBankAccount()));
        depositDto.setDepositRefundAccount(BankAccountMapper.toDto(customer.getBankAccount()));
        depositDto.setTypePercentPayment(TypePercentPaymentMapper.toDto(typePercentPayment));
        depositDto.setDepositType(DepositTypeMapper.toDto(depositType));

        // deposit settings
        depositDto.setDepositRefill(false);
        depositDto.setDepositsAmount(depositTerms.getDepositSum());
        depositDto.setStartDate(LocalDate.now());
        depositDto.setEndDate(getDepositEndDate(depositTerms.getDepositDuration()));
        depositDto.setPercentPaymentDate(
                getPercentPaymentDate(depositTerms.getPaymentPeriod(), depositTerms.getDepositDuration()));
        depositDto.setCapitalization(depositTerms.getCapitalization());
        depositDto.setDepositRate(getDepositRate(depositTerms));


        ResponseEntity<Void> response = restClient.post()
                .uri("http://localhost:3003/api/new")
                .contentType(APPLICATION_JSON)
                .body(depositDto)
                .retrieve()
                .toBodilessEntity();
    }

    private BigDecimal getDepositRate(DepositTermsDto depositTerms) {
        RestClient restClient = RestClient.create();
        ResponseEntity<BigDecimal> response = restClient.post()
                .uri("http://localhost:3003/api/deposit-rate")
                .contentType(APPLICATION_JSON)
                .body(depositTerms)
                .retrieve()
                .toEntity(BigDecimal.class);

        return response.getBody();
    }

    private LocalDate getDepositEndDate(DepositDurationEnum selectedDuration) {
        return switch (selectedDuration) {
            case MONTH_3 -> LocalDate.now().plusMonths(3);
            case MONTH_6 -> LocalDate.now().plusMonths(6);
            case YEAR -> LocalDate.now().plusYears(1);
        };
    }

    private LocalDate getPercentPaymentDate(PaymentPeriodEnum paymentPeriodEnum, DepositDurationEnum selectedDuration) {
        return switch (paymentPeriodEnum) {
            case MONTHLY -> LocalDate.now().plusMonths(1);
            case END_OF_TERM -> getDepositEndDate(selectedDuration);
        };
    }

    private Request createRequest(String phoneNumber) {
        return new Request();
    }
}
