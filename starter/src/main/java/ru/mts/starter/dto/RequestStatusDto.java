package ru.mts.starter.dto;

import lombok.Data;
import ru.mts.starter.enums.RequestStatusEnum;

import java.util.Objects;

@Data
public class RequestStatusDto {

    private Long id;
    private RequestStatusEnum requestStatusName;

    public RequestStatusDto(Long id, RequestStatusEnum requestStatusName) {
        this.id = id;
        this.requestStatusName = requestStatusName;
    }

    public RequestStatusDto() {
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        RequestStatusDto that = (RequestStatusDto) object;
        return Objects.equals(requestStatusName, that.requestStatusName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestStatusName);
    }

    @Override
    public String toString() {
        return "RequestStatusDto{" +
                "id=" + id +
                ", requestStatusName=" + requestStatusName +
                '}';
    }
}
