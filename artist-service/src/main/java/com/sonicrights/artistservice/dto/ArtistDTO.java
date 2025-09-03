package com.sonicrights.artistservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDate;

public class ArtistDTO {

    private Long id; // Optional for responses, not required in create requests

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Genre is required")
    private String genre;

    @NotNull(message = "Debut date is required")
    @PastOrPresent(message = "Debut date cannot be in the future")
    private LocalDate debutDate;

    @NotBlank(message = "Country is required")
    private String country;

    private boolean active = true;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    public ArtistDTO() {}

    public ArtistDTO(Long id, String name, String genre, LocalDate debutDate, String country, boolean active, String email) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.debutDate = debutDate;
        this.country = country;
        this.active = active;
        this.email = email;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDate getDebutDate() {
        return debutDate;
    }

    public void setDebutDate(LocalDate debutDate) {
        this.debutDate = debutDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
