package ru.mts.starter.entity.mapper;


import ru.mts.starter.entity.RequestStatus;
import ru.mts.starter.entity.dto.RequestStatusDto;

public class RequestStatusMapper {

    public static RequestStatusDto toDto(RequestStatus rs) {
        return new RequestStatusDto(rs.getId(), rs.getRequestStatusName());
    }

    public static RequestStatus toModel(RequestStatusDto dto) {
        return new RequestStatus(dto.getId(), dto.getRequestStatusName());
    }

}
