package ru.mts.starter.entity.dto;

import lombok.Data;
import ru.mts.starter.entity.enums.DepositTypeEnum;

import java.util.Objects;

@Data
public class DepositTypeDto {

    private Long id;
    private DepositTypeEnum depositsTypesName;

    public DepositTypeDto(Long id, DepositTypeEnum depositsTypesName) {
        this.id = id;
        this.depositsTypesName = depositsTypesName;
    }

    public DepositTypeDto() {
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        DepositTypeDto that = (DepositTypeDto) object;
        return Objects.equals(depositsTypesName, that.depositsTypesName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(depositsTypesName);
    }

    @Override
    public String toString() {
        return "DepositTypeDto{" +
                "id=" + id +
                ", depositsTypesName=" + depositsTypesName +
                '}';
    }
}