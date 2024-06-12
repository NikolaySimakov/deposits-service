package ru.mts.starter.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
@Table(name = "deposits")
public class Deposit {

    @Id
    @GeneratedValue
    @Column(name = "id_deposit")
    private Long id;

    @Column(name = "deposit_refill")
    private Boolean depositRefill;

    @Column(name = "deposits_amount", precision = 15, scale = 2)
    private BigDecimal depositsAmount;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "deposit_rate", precision = 4, scale = 2)
    private BigDecimal depositRate;

    @Column(name = "percent_payment_date")
    private Date percentPaymentDate;

    @Column(name = "capitalization")
    private Boolean capitalization;

    public Deposit() {
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Deposit that = (Deposit) object;
        return Objects.equals(depositRefill, that.depositRefill) && Objects.equals(depositsAmount, that.depositsAmount) && Objects.equals(startDate, that.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(depositRefill, depositsAmount, startDate, endDate,
                depositRate, percentPaymentDate, capitalization);
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "id=" + id +
                ", depositRefill=" + depositRefill +
                ", depositsAmount=" + depositsAmount +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", depositRate=" + depositRate +
                ", percentPaymentDate=" + percentPaymentDate +
                ", capitalization=" + capitalization +
                '}';
    }

}
