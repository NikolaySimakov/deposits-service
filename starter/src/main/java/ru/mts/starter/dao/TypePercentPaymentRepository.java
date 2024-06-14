package ru.mts.starter.dao;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.mts.starter.entity.TypePercentPayment;
import ru.mts.starter.enums.PaymentPeriodEnum;

import java.util.Optional;

@Repository
public interface TypePercentPaymentRepository extends JpaRepository<TypePercentPayment, Long> {

    @Query("SELECT t FROM TypePercentPayment t WHERE t.typePercentPaymentPeriod = :period")
    TypePercentPayment findByTypePercentPaymentPeriod(@Param("period") PaymentPeriodEnum period);

}