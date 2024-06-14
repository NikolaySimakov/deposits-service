package ru.mts.starter.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;


@Data
@Entity
@Table(name = "deposits")
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_deposit")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "deposit_account_id", referencedColumnName = "id_bank_accounts")
    private BankAccount depositAccount;

    @ManyToOne
    @JoinColumn(name = "deposits_type_id", referencedColumnName = "id_deposits_types")
    private DepositType depositType;

    @Column(name = "deposit_refill")
    private Boolean depositRefill;

    @Column(name = "deposits_amount", precision = 15, scale = 2)
    private BigDecimal depositsAmount;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "deposit_rate", precision = 4, scale = 2)
    private BigDecimal depositRate;

    @ManyToOne
    @JoinColumn(name = "type_percent_payment_id", referencedColumnName = "id_type_percent_payment")
    private TypePercentPayment typePercentPayment;

    @ManyToOne
    @JoinColumn(name = "percent_payment_account_id", referencedColumnName = "id_bank_accounts")
    private BankAccount percentPaymentAccount;

    @Column(name = "percent_payment_date")
    private LocalDate percentPaymentDate;

    @Column(name = "capitalization")
    private Boolean capitalization;

    @ManyToOne
    @JoinColumn(name = "deposit_refund_account_id", referencedColumnName = "id_bank_accounts")
    private BankAccount depositRefundAccount;

    public Deposit(Long id, BankAccount depositAccount, DepositType depositType,
                   Boolean depositRefill, BigDecimal depositsAmount, LocalDate startDate,
                   LocalDate endDate, BigDecimal depositRate, TypePercentPayment typePercentPayment,
                   BankAccount percentPaymentAccount, LocalDate percentPaymentDate, Boolean capitalization,
                   BankAccount depositRefundAccount) {
        this.id = id;
        this.depositAccount = depositAccount;
        this.depositType = depositType;
        this.depositRefill = depositRefill;
        this.depositsAmount = depositsAmount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.depositRate = depositRate;
        this.typePercentPayment = typePercentPayment;
        this.percentPaymentAccount = percentPaymentAccount;
        this.percentPaymentDate = percentPaymentDate;
        this.capitalization = capitalization;
        this.depositRefundAccount = depositRefundAccount;
    }

    public Deposit() {
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Deposit that = (Deposit) object;
        return Objects.equals(depositRefill, that.depositRefill) && Objects.equals(depositsAmount, that.depositsAmount)
                && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate)
                && Objects.equals(depositRate, that.depositRate)
                && Objects.equals(percentPaymentDate, that.percentPaymentDate)
                && Objects.equals(capitalization, that.capitalization);
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
