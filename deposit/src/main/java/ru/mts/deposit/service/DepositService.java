package ru.mts.deposit.service;

import org.springframework.stereotype.Service;
import ru.mts.starter.dao.DepositRepository;
import ru.mts.starter.dto.DepositDto;
import ru.mts.starter.mapper.DepositMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepositService {

    private final DepositRepository depositRepository;

    public DepositService(DepositRepository depositRepository) {
        this.depositRepository = depositRepository;
    }

    public List<DepositDto> getDeposits() {
        return depositRepository.findAll().stream()
                .map(DepositMapper::toDto)
                .collect(Collectors.toList());
    }

}
