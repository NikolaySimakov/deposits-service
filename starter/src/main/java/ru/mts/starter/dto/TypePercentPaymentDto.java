package ru.mts.starter.dto;

import lombok.Data;
import ru.mts.starter.enums.PaymentPeriodEnum;

import java.util.Objects;

@Data
public class TypePercentPaymentDto {

    private Long id;
    private PaymentPeriodEnum typePercentPaymentPeriod;

    public TypePercentPaymentDto(Long id, PaymentPeriodEnum typePercentPaymentPeriod) {
        this.id = id;
        this.typePercentPaymentPeriod = typePercentPaymentPeriod;
    }

    public TypePercentPaymentDto() {
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        TypePercentPaymentDto that = (TypePercentPaymentDto) object;
        return Objects.equals(typePercentPaymentPeriod, that.typePercentPaymentPeriod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typePercentPaymentPeriod);
    }

    @Override
    public String toString() {
        return "TypePercentPaymentDto{" +
                "id=" + id +
                ", typePercentPaymentPeriod=" + typePercentPaymentPeriod +
                '}';
    }

}
