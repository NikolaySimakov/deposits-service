package ru.mts.starter.dto;

import lombok.Data;
import ru.mts.starter.enums.DepositDurationEnum;
import ru.mts.starter.enums.DepositTypeEnum;
import ru.mts.starter.enums.PaymentPeriodEnum;

import java.math.BigDecimal;
import java.util.Objects;

@Data
public class DepositTermsDto {

    private DepositTypeEnum depositType;
    private DepositDurationEnum depositDuration;
    private BigDecimal depositSum;
    private PaymentPeriodEnum paymentPeriod;
    private Boolean capitalization;

    public DepositTermsDto(BigDecimal depositSum, DepositTypeEnum depositType, PaymentPeriodEnum paymentPeriod,
                           DepositDurationEnum depositDuration, Boolean capitalization) {
        this.depositSum = depositSum;
        this.depositType = depositType;
        this.paymentPeriod = paymentPeriod;
        this.depositDuration = depositDuration;
        this.capitalization = capitalization;
    }

    public DepositTermsDto() {
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        DepositTermsDto that = (DepositTermsDto) object;
        return Objects.equals(depositSum, that.depositSum)
                && Objects.equals(depositType, that.depositType)
                && Objects.equals(paymentPeriod, that.paymentPeriod)
                && Objects.equals(depositDuration, that.depositDuration)
                && Objects.equals(capitalization, that.capitalization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(depositSum, depositType, paymentPeriod, depositDuration, capitalization);
    }

    @Override
    public String toString() {
        return "DepositTermsDto{" +
                "depositType=" + depositType +
                ", depositDuration=" + depositDuration +
                ", depositSum=" + depositSum +
                ", paymentPeriod=" + paymentPeriod +
                ", capitalization=" + capitalization +
                '}';
    }
}
