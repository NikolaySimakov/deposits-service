package ru.mts.starter.mapper;

import ru.mts.starter.entity.TypePercentPayment;
import ru.mts.starter.dto.TypePercentPaymentDto;

public class TypePercentPaymentMapper {

    public static TypePercentPaymentDto toDto(TypePercentPayment tpp) {
        return new TypePercentPaymentDto(tpp.getId(), tpp.getTypePercentPaymentPeriod());
    }

    public static TypePercentPayment toModel(TypePercentPaymentDto dto) {
        return new TypePercentPayment(dto.getId(), dto.getTypePercentPaymentPeriod());
    }

}
