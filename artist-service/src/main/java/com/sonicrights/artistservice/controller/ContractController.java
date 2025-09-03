package com.sonicrights.artistservice.controller;

import com.sonicrights.artistservice.dto.ContractDTO;
import com.sonicrights.artistservice.service.ContractService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contracts")
public class ContractController {

    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    // Create contract
    @PostMapping
    public ResponseEntity<ContractDTO> createContract(@Valid @RequestBody ContractDTO contractDTO) {
        ContractDTO created = contractService.createContract(contractDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // Get all contracts
    @GetMapping
    public ResponseEntity<List<ContractDTO>> getAllContracts() {
        return ResponseEntity.ok(contractService.getAllContracts());
    }

    // Get contract by ID
    @GetMapping("/{id}")
    public ResponseEntity<ContractDTO> getContractById(@PathVariable Long id) {
        return contractService.getContractById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update contract
    @PutMapping("/{id}")
    public ResponseEntity<ContractDTO> updateContract(@PathVariable Long id, @Valid @RequestBody ContractDTO contractDTO) {
        return contractService.updateContract(id, contractDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete contract
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContract(@PathVariable Long id) {
        if (contractService.deleteContract(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
