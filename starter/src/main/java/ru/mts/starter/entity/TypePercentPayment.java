package ru.mts.starter.entity;

import jakarta.persistence.*;
import lombok.Data;
import ru.mts.starter.entity.enums.PaymentPeriodEnum;

import java.util.Objects;

@Data
@Entity
@Table(name = "types_percent_payment")
public class TypePercentPayment {

    @Id
    @GeneratedValue
    @Column(name = "id_type_percent_payment")
    private Long id;

    @Column(name = "type_percent_payment_period")
    @Enumerated(EnumType.STRING)
    private PaymentPeriodEnum typePercentPaymentPeriod;

    public TypePercentPayment(Long id, PaymentPeriodEnum typePercentPaymentPeriod) {
        this.id = id;
        this.typePercentPaymentPeriod = typePercentPaymentPeriod;
    }

    public TypePercentPayment() {
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        TypePercentPayment that = (TypePercentPayment) object;
        return Objects.equals(typePercentPaymentPeriod, that.typePercentPaymentPeriod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typePercentPaymentPeriod);
    }

    @Override
    public String toString() {
        return "TypePercentPayment{" +
                "id=" + id +
                ", typePercentPaymentPeriod=" + typePercentPaymentPeriod +
                '}';
    }

}
