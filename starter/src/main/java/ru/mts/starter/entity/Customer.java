package ru.mts.starter.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue
    @Column(name = "id_customers")
    private Long id;

    @Column(name = "phone_number")
    private String phoneNumber;

    public Customer() {
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Customer that = (Customer) object;
        return Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
