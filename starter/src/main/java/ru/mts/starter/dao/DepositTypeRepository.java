package ru.mts.starter.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.mts.starter.entity.DepositType;
import org.springframework.data.jpa.repository.Query;
import ru.mts.starter.enums.DepositTypeEnum;

@Repository
public interface DepositTypeRepository extends JpaRepository<DepositType, Long> {

    @Query("SELECT dt FROM DepositType dt WHERE dt.depositsTypesName = :depositTypeName")
    DepositType findByDepositsTypesName(@Param("depositTypeName") DepositTypeEnum depositTypeName);

}
