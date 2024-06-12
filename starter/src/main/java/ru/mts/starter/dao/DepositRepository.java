package ru.mts.starter.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mts.starter.entity.Deposit;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Long> {

}
