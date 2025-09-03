package com.sonicrights.artistservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDate;

public class ContractDTO {

    private Long id; // For responses only

    @NotBlank(message = "Contract name is required")
    private String contractName;

    @NotNull(message = "Start date is required")
    @PastOrPresent(message = "Start date cannot be in the future")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;

    @Positive(message = "Royalty percentage must be positive")
    private double royaltyPercentage;

    @NotNull(message = "Artist ID is required")
    private Long artistId; // We pass only the artist ID instead of full Artist object

    public ContractDTO() {}

    public ContractDTO(Long id, String contractName, LocalDate startDate, LocalDate endDate, double royaltyPercentage, Long artistId) {
        this.id = id;
        this.contractName = contractName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.royaltyPercentage = royaltyPercentage;
        this.artistId = artistId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getRoyaltyPercentage() {
        return royaltyPercentage;
    }

    public void setRoyaltyPercentage(double royaltyPercentage) {
        this.royaltyPercentage = royaltyPercentage;
    }

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }
}
