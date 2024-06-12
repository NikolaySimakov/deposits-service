package ru.mts.starter.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mts.starter.entity.TypePercentPayment;

@Repository
public interface TypePercentPaymentRepository extends JpaRepository<TypePercentPayment, Long> {

}