package ru.mts.deposit.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import ru.mts.starter.dao.DepositRepository;
import ru.mts.starter.dto.DepositDto;
import ru.mts.starter.entity.Deposit;
import ru.mts.starter.mapper.DepositMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepositService {

    private final DepositRepository depositRepository;

    public DepositService(DepositRepository depositRepository) {
        this.depositRepository = depositRepository;
    }

    public List<DepositDto> getActiveDeposits() {
        // todo: fetch active
        return depositRepository.findAll().stream()
                .map(DepositMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<DepositDto> getRejectedDeposits() {
        // todo: fetch rejected
        return depositRepository.findAll().stream()
                .map(DepositMapper::toDto)
                .collect(Collectors.toList());
    }

    public DepositDto getDepositById(Long id) {
        Optional<Deposit> depositOpt = depositRepository.findById(id);

        if (depositOpt.isEmpty()) {
            throw new EntityNotFoundException("Deposit not found with id: " + id);
        }

        Deposit deposit = depositOpt.get();
        return DepositMapper.toDto(deposit);
    }

    public void createNewDeposit(DepositDto deposit) {
        depositRepository.save(DepositMapper.toModel(deposit));
    }

    public void deleteDepositById(Long id) {
        depositRepository.deleteById(id);
    }
}
