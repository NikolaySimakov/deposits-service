package ru.mts.starter.mapper;

import ru.mts.starter.dto.SmsDto;
import ru.mts.starter.entity.Sms;

public class SmsMapper {
    public static SmsDto toDto(Sms sms) {
        return new SmsDto(sms.getId(), sms.getPhoneNumber(), sms.getCode(), sms.getEndTime());
    }

    public static Sms toModel(SmsDto dto) {
        return new Sms(dto.getId(), dto.getPhoneNumber(), dto.getCode(), dto.getEndTime());
    }
}
