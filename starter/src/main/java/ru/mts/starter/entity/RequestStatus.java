package ru.mts.starter.entity;

import jakarta.persistence.*;
import lombok.Data;
import ru.mts.starter.enums.RequestStatusEnum;

import java.util.Objects;

@Data
@Entity
@Table(name = "request_statuses")
public class RequestStatus {

    @Id
    @GeneratedValue
    @Column(name = "id_request_status")
    private Long id;

    @Column(name = "request_status_name")
    @Enumerated(EnumType.STRING)
    private RequestStatusEnum requestStatusName;

    public RequestStatus(Long id, RequestStatusEnum requestStatusName) {
        this.id = id;
        this.requestStatusName = requestStatusName;
    }

    public RequestStatus() {
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        RequestStatus that = (RequestStatus) object;
        return Objects.equals(requestStatusName, that.requestStatusName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestStatusName);
    }

    @Override
    public String toString() {
        return "RequestStatus{" +
                "id=" + id +
                ", requestStatusName=" + requestStatusName +
                '}';
    }
}
