package ru.mts.starter.mapper;

import ru.mts.starter.dto.CustomerDto;
import ru.mts.starter.entity.Customer;

public class CustomerMapper {

    public static CustomerDto toDto(Customer dt) {
        return new CustomerDto(dt.getId(), dt.getPhoneNumber(),
                dt.getBankAccount().getNumBankAccounts(),
                dt.getBankAccount().getAmount());
    }

}
