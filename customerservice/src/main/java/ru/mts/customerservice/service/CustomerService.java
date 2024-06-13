package ru.mts.customerservice.service;

import org.springframework.stereotype.Service;
import ru.mts.starter.dao.BankAccountRepository;
import ru.mts.starter.dao.CustomerRepository;
import ru.mts.starter.dto.CustomerDto;
import ru.mts.starter.entity.BankAccount;
import ru.mts.starter.entity.Customer;
import ru.mts.starter.mapper.CustomerMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final BankAccountRepository bankAccountRepository;

    public CustomerService(CustomerRepository customerRepository, BankAccountRepository bankAccountRepository) {
        this.customerRepository = customerRepository;
        this.bankAccountRepository = bankAccountRepository;
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
}
