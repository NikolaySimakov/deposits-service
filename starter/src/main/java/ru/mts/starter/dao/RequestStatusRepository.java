package ru.mts.starter.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mts.starter.entity.RequestStatus;

@Repository
public interface RequestStatusRepository extends JpaRepository<RequestStatus, Long> {

}
