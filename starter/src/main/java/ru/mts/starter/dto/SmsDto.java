package ru.mts.starter.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
public class SmsDto {

    private Long id;
    private String phoneNumber;
    private String code;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime endTime;

    public SmsDto(Long id, String phoneNumber, String code, LocalDateTime endTime) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.code = code;
        this.endTime = endTime;
    }

    public SmsDto() {
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        SmsDto that = (SmsDto) object;
        return Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(code, that.code)
                && Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber, code, endTime);
    }

    @Override
    public String toString() {
        return "SmsDto{" +
                "id=" + id +
                ", phoneNumber=" + phoneNumber +
                ", code=" + code +
                ", endTime=" + endTime +
                '}';
    }
}
