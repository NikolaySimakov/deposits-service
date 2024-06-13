package ru.mts.deposit.service;

import org.springframework.stereotype.Service;
import ru.mts.starter.dao.DepositTypeRepository;
import ru.mts.starter.dao.RequestStatusRepository;
import ru.mts.starter.dao.TypePercentPaymentRepository;
import ru.mts.starter.dto.DepositTypeDto;
import ru.mts.starter.dto.RequestStatusDto;
import ru.mts.starter.dto.TypePercentPaymentDto;
import ru.mts.starter.mapper.DepositTypeMapper;
import ru.mts.starter.mapper.RequestStatusMapper;
import ru.mts.starter.mapper.TypePercentPaymentMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TermsService {

    private final RequestStatusRepository requestStatusRepository;
    private final DepositTypeRepository depositTypeRepository;
    private final TypePercentPaymentRepository typePercentPaymentRepository;


    public TermsService(RequestStatusRepository requestStatusRepository, DepositTypeRepository depositTypeRepository,
                        TypePercentPaymentRepository typePercentPaymentRepository) {
        this.requestStatusRepository = requestStatusRepository;
        this.depositTypeRepository = depositTypeRepository;
        this.typePercentPaymentRepository = typePercentPaymentRepository;
    }

    public List<RequestStatusDto> getRequestStatuses() {
        return requestStatusRepository.findAll().stream()
                .map(RequestStatusMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<DepositTypeDto> getDepositTypes() {
        return depositTypeRepository.findAll().stream()
                .map(DepositTypeMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<TypePercentPaymentDto> getTypePercentPayments() {
        return typePercentPaymentRepository.findAll().stream()
                .map(TypePercentPaymentMapper::toDto)
                .collect(Collectors.toList());
    }

    public BigDecimal calculateRate() {
        return new BigDecimal("");
    }
}
