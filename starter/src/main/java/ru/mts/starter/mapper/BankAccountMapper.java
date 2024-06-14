package ru.mts.starter.mapper;

import ru.mts.starter.dto.BankAccountDto;
import ru.mts.starter.dto.DepositTypeDto;
import ru.mts.starter.entity.BankAccount;
import ru.mts.starter.entity.DepositType;

public class BankAccountMapper {
    public static BankAccountDto toDto(BankAccount dt) {
        return new BankAccountDto(dt.getId(), dt.getNumBankAccounts(), dt.getAmount());
    }

    public static BankAccount toModel(BankAccountDto dto) {
        return new BankAccount(dto.getId(), dto.getNumBankAccounts(), dto.getAmount());
    }
}