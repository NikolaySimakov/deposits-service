package ru.mts.starter.mapper;


import ru.mts.starter.dto.DepositDto;
import ru.mts.starter.entity.Deposit;

public class DepositMapper {

    public static DepositDto toDto(Deposit dt) {
        return new DepositDto(dt.getId(), dt.getDepositRefill(), dt.getDepositsAmount(), dt.getStartDate(),
                dt.getEndDate(), dt.getDepositRate(), dt.getPercentPaymentDate(), dt.getCapitalization());
    }

    public static Deposit toModel(DepositDto dto) {
        return new Deposit(dto.getId(), dto.getDepositRefill(), dto.getDepositsAmount(), dto.getStartDate(),
                dto.getEndDate(), dto.getDepositRate(), dto.getPercentPaymentDate(), dto.getCapitalization());
    }

}
