package ru.mts.deposit.service;

import org.springframework.stereotype.Service;
import ru.mts.starter.dao.DepositTypeRepository;
import ru.mts.starter.dao.RequestStatusRepository;
import ru.mts.starter.dao.TypePercentPaymentRepository;
import ru.mts.starter.dto.DepositTermsDto;
import ru.mts.starter.dto.DepositTypeDto;
import ru.mts.starter.dto.RequestStatusDto;
import ru.mts.starter.dto.TypePercentPaymentDto;
import ru.mts.starter.enums.DepositDurationEnum;
import ru.mts.starter.enums.DepositTypeEnum;
import ru.mts.starter.enums.PaymentPeriodEnum;
import ru.mts.starter.mapper.DepositTypeMapper;
import ru.mts.starter.mapper.RequestStatusMapper;
import ru.mts.starter.mapper.TypePercentPaymentMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
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

    public BigDecimal calculateRate(DepositTermsDto depositTermsDto) {

        // Предполагаемая базовая ставка
        BigDecimal baseRate = new BigDecimal("0.05");
        Map<DepositTypeEnum, BigDecimal> typeAdjustments = Map.of(
                DepositTypeEnum.DEPOSIT_WITHDRAWAL, new BigDecimal("0.005"),
                DepositTypeEnum.DEPOSIT_ONLY, new BigDecimal("0.01"),
                DepositTypeEnum.NONE_DEPOSIT_NONE_WITHDRAWAL, BigDecimal.ZERO
        );
        Map<DepositDurationEnum, BigDecimal> durationAdjustments = Map.of(
                DepositDurationEnum.MONTH_3, new BigDecimal("0.0025"),
                DepositDurationEnum.MONTH_6, new BigDecimal("0.005"),
                DepositDurationEnum.YEAR, new BigDecimal("0.01")
        );
        Map<PaymentPeriodEnum, BigDecimal> periodAdjustments = Map.of(
                PaymentPeriodEnum.MONTHLY, new BigDecimal("-0.0025"),
                PaymentPeriodEnum.END_OF_TERM, new BigDecimal("0.0075")
        );

        if (depositTermsDto.getDepositType() != null) {
            baseRate = baseRate.add(typeAdjustments.get(depositTermsDto.getDepositType()));
        }
        if (depositTermsDto.getDepositDuration() != null) {
            baseRate = baseRate.add(durationAdjustments.get(depositTermsDto.getDepositDuration()));
        }
        if (depositTermsDto.getPaymentPeriod() != null) {
            baseRate = baseRate.add(periodAdjustments.get(depositTermsDto.getPaymentPeriod()));
        }

        // Увеличение базовой ставки на процент от суммы вклада
        BigDecimal sumFactor = depositTermsDto.getDepositSum().divide(new BigDecimal(10000)); // За каждые 1000 единиц суммы вклада
        baseRate = baseRate.add(baseRate.multiply(sumFactor));

        if (depositTermsDto.getCapitalization() != null && depositTermsDto.getCapitalization()) {
            baseRate = new BigDecimal("0");
        }

        return baseRate;
    }
}
