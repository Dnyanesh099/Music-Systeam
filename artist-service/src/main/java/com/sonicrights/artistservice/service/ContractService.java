package com.sonicrights.artistservice.service;

import com.sonicrights.artistservice.dto.ContractDTO;
import com.sonicrights.artistservice.entity.Artist;
import com.sonicrights.artistservice.entity.Contract;
import com.sonicrights.artistservice.mapper.ContractMapper;
import com.sonicrights.artistservice.repository.ArtistRepository;
import com.sonicrights.artistservice.repository.ContractRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContractService {

    private final ContractRepository contractRepository;
    private final ArtistRepository artistRepository;

    public ContractService(ContractRepository contractRepository, ArtistRepository artistRepository) {
        this.contractRepository = contractRepository;
        this.artistRepository = artistRepository;
    }

    // Create Contract
    public ContractDTO createContract(ContractDTO dto) {
        Contract contract = ContractMapper.toEntity(dto);

        // Set the artist from artistId
        Artist artist = artistRepository.findById(dto.getArtistId())
                .orElseThrow(() -> new RuntimeException("Artist not found with ID: " + dto.getArtistId()));
        contract.setArtist(artist);

        Contract saved = contractRepository.save(contract);
        return ContractMapper.toDTO(saved);
    }

    // Get all contracts
    public List<ContractDTO> getAllContracts() {
        return contractRepository.findAll()
                .stream()
                .map(ContractMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Get contract by ID
    public Optional<ContractDTO> getContractById(Long id) {
        return contractRepository.findById(id)
                .map(ContractMapper::toDTO);
    }

    // Update contract
    public Optional<ContractDTO> updateContract(Long id, ContractDTO dto) {
        return contractRepository.findById(id).map(existing -> {
            existing.setContractName(dto.getContractName());
            existing.setStartDate(dto.getStartDate());
            existing.setEndDate(dto.getEndDate());
            existing.setRoyaltyPercentage(dto.getRoyaltyPercentage());

            // Update artist if artistId changed
            if (dto.getArtistId() != null) {
                Artist artist = artistRepository.findById(dto.getArtistId())
                        .orElseThrow(() -> new RuntimeException("Artist not found with ID: " + dto.getArtistId()));
                existing.setArtist(artist);
            }

            Contract updated = contractRepository.save(existing);
            return ContractMapper.toDTO(updated);
        });
    }

    // Delete contract
    public boolean deleteContract(Long id) {
        if (contractRepository.existsById(id)) {
            contractRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
