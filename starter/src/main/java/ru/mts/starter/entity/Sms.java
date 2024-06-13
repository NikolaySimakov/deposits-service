package ru.mts.starter.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Entity
@Table(name = "sms")
public class Sms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sms")
    private Long id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "code")
    private String code;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    public Sms(Long id, String phoneNumber, String code, LocalDateTime endTime) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.code = code;
        this.endTime = endTime;
    }

    public Sms() {
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Sms that = (Sms) object;
        return Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(code, that.code)
                && Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber, code, endTime);
    }

    @Override
    public String toString() {
        return "Sms{" +
                "id=" + id +
                ", phoneNumber=" + phoneNumber +
                ", code=" + code +
                ", endTime=" + endTime +
                '}';
    }
}
