package ru.mts.account.service;

import org.springframework.stereotype.Service;
import ru.mts.starter.dao.BankAccountRepository;
import ru.mts.starter.dao.CustomerRepository;
import ru.mts.starter.dto.CustomerDto;
import ru.mts.starter.mapper.CustomerMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerDto> getCustomers() {
        return customerRepository.findAll().stream()
                .map(CustomerMapper::toDto)
                .collect(Collectors.toList());
    }

    public boolean customerExists(String phoneNumber) {
        return customerRepository.existsByPhoneNumber(phoneNumber);
    }

}
