package ru.mts.starter.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mts.starter.entity.DepositType;

@Repository
public interface DepositTypeRepository extends JpaRepository<DepositType, Long> {

}
