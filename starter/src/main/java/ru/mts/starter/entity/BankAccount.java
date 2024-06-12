package ru.mts.starter.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

@Data
@Entity
@Table(name = "bank_accounts")
public class BankAccount {

    @Id
    @GeneratedValue
    @Column(name = "id_bank_accounts")
    private Long id;

    @Column(name = "num_bank_accounts")
    private BigDecimal numBankAccounts;

    @Column(name = "amount")
    private BigDecimal amount;

    public BankAccount() {
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        BankAccount that = (BankAccount) object;
        return Objects.equals(numBankAccounts, that.numBankAccounts) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numBankAccounts, amount);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", numBankAccounts=" + numBankAccounts +
                ", amount=" + amount +
                '}';
    }
}
