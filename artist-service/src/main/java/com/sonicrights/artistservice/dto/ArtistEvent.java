package com.sonicrights.artistservice.dto;

public class ArtistEvent {
    private Long artistId;
    private String name;
    private String genre;

    // No-args constructor
    public ArtistEvent() {}

    // All-args constructor
    public ArtistEvent(Long artistId, String name, String genre) {
        this.artistId = artistId;
        this.name = name;
        this.genre = genre;
    }

    // Getters and Setters

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
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
}
