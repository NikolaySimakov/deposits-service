package ru.mts.starter.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.mts.starter.entity.Customer;

import java.math.BigDecimal;
import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM Customer c WHERE c.phoneNumber = :phoneNumber")
    boolean existsByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Query("SELECT c.bankAccount.amount FROM Customer c WHERE c.phoneNumber = :phoneNumber")
    BigDecimal findAmountByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    Optional<Customer> findByPhoneNumber(String phoneNumber);

}