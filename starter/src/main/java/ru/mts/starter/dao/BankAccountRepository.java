package ru.mts.starter.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mts.starter.entity.BankAccount;


@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

}