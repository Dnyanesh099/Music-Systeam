package com.sonicrights.artistservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "contracts")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String contractName;

    private double amount;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id", nullable = false)
    @JsonBackReference
    private Artist artist; // Many contracts belong to one artist

    @Column(name = "royalty_percentage", nullable = false)
    private double royaltyPercentage;



    public Contract() {}

    public Contract(Long id, String contractName, double amount, LocalDate startDate, LocalDate endDate, Artist artist,Double royaltyPercentage) {
        this.id = id;
        this.contractName = contractName;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.artist = artist;
        this.royaltyPercentage=royaltyPercentage;
    }

    // Getters & Setters
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public double getRoyaltyPercentage() {
        return royaltyPercentage;
    }

    public void setRoyaltyPercentage(double royaltyPercentage) {
        this.royaltyPercentage = royaltyPercentage;
    }
}
