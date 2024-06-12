package ru.mts.starter.dto;


import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Data
public class DepositDto {

    private Long id;
    private Boolean depositRefill;
    private BigDecimal depositsAmount;
    private Date startDate;
    private Date endDate;
    private BigDecimal depositRate;
    private Date percentPaymentDate;
    private Boolean capitalization;

    public DepositDto(Long id, Boolean depositRefill, BigDecimal depositsAmount, Date startDate,
                      Date endDate, BigDecimal depositRate, Date percentPaymentDate, Boolean capitalization) {
        this.id = id;
        this.depositRefill = depositRefill;
        this.depositsAmount = depositsAmount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.depositRate = depositRate;
        this.percentPaymentDate = percentPaymentDate;
        this.capitalization = capitalization;
    }

    public DepositDto() {
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        DepositDto that = (DepositDto) object;
        return Objects.equals(depositRefill, that.depositRefill) && Objects.equals(depositsAmount, that.depositsAmount) && Objects.equals(startDate, that.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(depositRefill, depositsAmount, startDate, endDate,
                depositRate, percentPaymentDate, capitalization);
    }

    @Override
    public String toString() {
        return "DepositDto{" +
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
