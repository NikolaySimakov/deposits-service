package ru.mts.starter.dto;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Data
public class DepositDto {

    private Long id;
    private BankAccountDto depositAccount; // Simplified version of BankAccount
    private DepositTypeDto depositType; // Simplified version of DepositType
    private Boolean depositRefill;
    private BigDecimal depositsAmount;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal depositRate;
    private TypePercentPaymentDto typePercentPayment; // Simplified version of TypePercentPayment
    private BankAccountDto percentPaymentAccount; // Simplified version of BankAccount
    private LocalDate percentPaymentDate;
    private Boolean capitalization;
    private BankAccountDto depositRefundAccount; // Simplified version of BankAccount

    public DepositDto(Long id, Long depositAccountId, Long depositTypeId,
                      Boolean depositRefill, BigDecimal depositsAmount, LocalDate startDate,
                      LocalDate endDate, BigDecimal depositRate, Long typePercentPaymentId,
                      Long percentPaymentAccountId, LocalDate percentPaymentDate, Boolean capitalization,
                      Long depositRefundAccountId) {
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
                ", depositAccount=" + depositAccount.toString() +
                ", depositType=" + depositType.toString() +
                ", depositRefill=" + depositRefill +
                ", depositsAmount=" + depositsAmount +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", depositRate=" + depositRate +
                ", typePercentPayment=" + typePercentPayment.toString() +
                ", percentPaymentAccount=" + percentPaymentAccount.toString() +
                ", percentPaymentDate=" + percentPaymentDate +
                ", capitalization=" + capitalization +
                ", depositRefundAccount=" + depositRefundAccount.toString() +
                '}';
    }

}
