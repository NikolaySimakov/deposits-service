package ru.mts.starter.dto;


import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

@Data
public class BankAccountDto {

    private Long id;
    private Long numBankAccounts;
    private BigDecimal amount;

    public BankAccountDto(Long id, Long numBankAccounts, BigDecimal amount) {
        this.id = id;
        this.numBankAccounts = numBankAccounts;
        this.amount = amount;
    }

    public BankAccountDto() {
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        BankAccountDto that = (BankAccountDto) object;
        return Objects.equals(numBankAccounts, that.numBankAccounts) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numBankAccounts, amount);
    }

    @Override
    public String toString() {
        return "BankAccountDto{" +
                "id=" + id +
                ", numBankAccounts=" + numBankAccounts +
                ", amount=" + amount +
                '}';
    }
}