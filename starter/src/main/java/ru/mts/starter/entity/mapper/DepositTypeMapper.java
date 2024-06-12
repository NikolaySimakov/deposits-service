package ru.mts.starter.entity.mapper;

import ru.mts.starter.entity.DepositType;
import ru.mts.starter.entity.dto.DepositTypeDto;

public class DepositTypeMapper {
    public static DepositTypeDto toDto(DepositType dt) {
        return new DepositTypeDto(dt.getId(), dt.getDepositsTypesName());
    }

    public static DepositType toModel(DepositTypeDto dto) {
        return new DepositType(dto.getId(), dto.getDepositsTypesName());
    }
}
