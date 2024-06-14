package ru.mts.starter.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

//@Data
//@Entity
//@Table(name = "customer_deposits")
//public class CustomerDeposit {
//
//    @EmbeddedId
//    private CustomerDepositId id;
//
//    @ManyToOne
//    @JoinColumns({
//            @JoinColumn(name = "customer_id", referencedColumnName = "id_customers"),
//            @JoinColumn(name = "deposit_id", referencedColumnName = "id_deposit")
//    })
//    private Customer customer;
//
//    @ManyToOne
//    @JoinColumn(name = "deposit_id", referencedColumnName = "id_deposit")
//    private Deposit deposit;
//
//}