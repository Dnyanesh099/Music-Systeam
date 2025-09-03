package com.sonicrights.artistservice.mapper;

import com.sonicrights.artistservice.dto.ContractDTO;
import com.sonicrights.artistservice.entity.Contract;

public class ContractMapper {

    // DTO → Entity
    public static Contract toEntity(ContractDTO dto) {
        if (dto == null) return null;

        Contract contract = new Contract();
        contract.setId(dto.getId());
        contract.setContractName(dto.getContractName());
        contract.setStartDate(dto.getStartDate());
        contract.setEndDate(dto.getEndDate());
        contract.setRoyaltyPercentage(dto.getRoyaltyPercentage());
        // Artist will be set in Service layer when saving

        return contract;
    }

    // Entity → DTO
    public static ContractDTO toDTO(Contract contract) {
        if (contract == null) return null;

        return new ContractDTO(
                contract.getId(),
                contract.getContractName(),
                contract.getStartDate(),
                contract.getEndDate(),
                contract.getRoyaltyPercentage(),
                (contract.getArtist() != null) ? contract.getArtist().getId() : null
        );
    }
}
