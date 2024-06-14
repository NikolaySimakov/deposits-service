package ru.mts.starter.mapper;


import ru.mts.starter.dto.DepositDto;
import ru.mts.starter.entity.BankAccount;
import ru.mts.starter.entity.Deposit;
import ru.mts.starter.entity.DepositType;
import ru.mts.starter.entity.TypePercentPayment;

public class DepositMapper {

    public static DepositDto toDto(Deposit deposit) {
        DepositDto depositDto = new DepositDto();
        depositDto.setId(deposit.getId());
        depositDto.setDepositAccount(BankAccountMapper.toDto(deposit.getDepositAccount())); // dto
        depositDto.setDepositType(DepositTypeMapper.toDto(deposit.getDepositType())); // dto
        depositDto.setDepositRefill(deposit.getDepositRefill());
        depositDto.setDepositsAmount(deposit.getDepositsAmount());
        depositDto.setStartDate(deposit.getStartDate());
        depositDto.setEndDate(deposit.getEndDate());
        depositDto.setDepositRate(deposit.getDepositRate());
        depositDto.setTypePercentPayment(TypePercentPaymentMapper.toDto(deposit.getTypePercentPayment())); // dto
        depositDto.setPercentPaymentAccount(BankAccountMapper.toDto(deposit.getPercentPaymentAccount())); // dto
        depositDto.setPercentPaymentDate(deposit.getPercentPaymentDate());
        depositDto.setCapitalization(deposit.getCapitalization());
        depositDto.setDepositRefundAccount(BankAccountMapper.toDto(deposit.getDepositRefundAccount())); // dto
        return depositDto;
    }

    public static Deposit toModel(DepositDto depositDto) {
        Deposit deposit = new Deposit();
        deposit.setId(depositDto.getId());
        deposit.setDepositAccount(BankAccountMapper.toModel(depositDto.getDepositAccount())); // model
        deposit.setDepositType(DepositTypeMapper.toModel(depositDto.getDepositType())); // model
        deposit.setDepositRefill(depositDto.getDepositRefill());
        deposit.setDepositsAmount(depositDto.getDepositsAmount());
        deposit.setStartDate(depositDto.getStartDate());
        deposit.setEndDate(depositDto.getEndDate());
        deposit.setDepositRate(depositDto.getDepositRate());
        deposit.setTypePercentPayment(TypePercentPaymentMapper.toModel(depositDto.getTypePercentPayment())); // model
        deposit.setPercentPaymentAccount(BankAccountMapper.toModel(depositDto.getPercentPaymentAccount())); // model
        deposit.setPercentPaymentDate(depositDto.getPercentPaymentDate());
        deposit.setCapitalization(depositDto.getCapitalization());
        deposit.setDepositRefundAccount(BankAccountMapper.toModel(depositDto.getDepositRefundAccount())); // model
        return deposit;
    }

}
