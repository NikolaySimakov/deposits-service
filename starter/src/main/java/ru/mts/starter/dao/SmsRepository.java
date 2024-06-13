package ru.mts.starter.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.mts.starter.entity.Sms;

@Repository
public interface SmsRepository extends JpaRepository<Sms, Long> {

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN TRUE ELSE FALSE END FROM Sms s WHERE s.phoneNumber = :phoneNumber " +
            "AND s.code = :code AND s.endTime > CURRENT_TIMESTAMP()")
    boolean existsByPhoneNumberAndCodeAndIsActive(@Param("phoneNumber") String phoneNumber, @Param("code") String code);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN TRUE ELSE FALSE END FROM Sms s WHERE s.code = :code " +
            "AND CURRENT_TIMESTAMP() < s.endTime")
    boolean existsByCodeAndIsActive(@Param("code") String code);

}