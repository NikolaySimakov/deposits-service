package ru.mts.starter.dto;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;


@Data
public class CustomerDto {

    private Long id;
    private Long numBankAccounts;
    private BigDecimal amount;
    private String phoneNumber;

    public CustomerDto(Long id, String phoneNumber, Long numBankAccounts, BigDecimal amount) {
        this.id = id;
        this.numBankAccounts = numBankAccounts;
        this.amount = amount;
        this.phoneNumber = phoneNumber;
    }

    public CustomerDto() {
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        CustomerDto that = (CustomerDto) object;
        return Objects.equals(phoneNumber, that.phoneNumber)
                && Objects.equals(numBankAccounts, that.numBankAccounts)
                && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numBankAccounts, phoneNumber, amount);
    }

    @Override
    public String toString() {
        return "CustomerDto{" +
                "id=" + id +
                ", phoneNumber=" + phoneNumber +
                ", numBankAccounts=" + numBankAccounts +
                ", amount=" + amount +
                '}';
    }
}
