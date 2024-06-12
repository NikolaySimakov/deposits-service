package ru.mts.starter.entity;

import jakarta.persistence.*;
import lombok.Data;
import ru.mts.starter.enums.DepositTypeEnum;

import java.util.Objects;

@Data
@Entity
@Table(name = "deposits_types")
public class DepositType {

    @Id
    @GeneratedValue
    @Column(name = "id_deposits_types")
    private Long id;

    @Column(name = "deposits_types_name")
    @Enumerated(EnumType.STRING)
    private DepositTypeEnum depositsTypesName;

    public DepositType(Long id, DepositTypeEnum depositsTypesName) {
        this.id = id;
        this.depositsTypesName = depositsTypesName;
    }

    public DepositType() {
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        DepositType that = (DepositType) object;
        return Objects.equals(depositsTypesName, that.depositsTypesName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(depositsTypesName);
    }

    @Override
    public String toString() {
        return "DepositType{" +
                "id=" + id +
                ", depositsTypesName=" + depositsTypesName +
                '}';
    }
}
