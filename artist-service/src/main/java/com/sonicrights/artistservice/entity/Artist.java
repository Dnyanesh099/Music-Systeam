package com.sonicrights.artistservice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String genre;

    @Column(name = "debut_date")
    private LocalDate debutDate;

    private String country;

    private boolean active = true;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    // mappedBy must match the field name in Contract entity (artist)
    @JsonManagedReference
    private List<Contract> contracts = new ArrayList<>();

    public Artist() {}

    public Artist(Long id, String name, String genre, LocalDate debutDate, String country, boolean active, String email) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.debutDate = debutDate;
        this.country = country;
        this.active = active;
        this.email = email;
    }


    public void addContract(Contract contract) {
        contracts.add(contract);
        contract.setArtist(this);  // set back-reference
    }

    public void removeContract(Contract contract) {
        contracts.remove(contract);
        contract.setArtist(null);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public LocalDate getDebutDate() { return debutDate; }
    public void setDebutDate(LocalDate debutDate) { this.debutDate = debutDate; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<Contract> getContracts() { return contracts; }
    public void setContracts(List<Contract> contracts) { this.contracts = contracts; }
}
