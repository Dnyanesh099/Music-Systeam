package com.sonicrights.royaltyservice.dto;

public class ArtistEvent {

    private Long artistId;
    private String name;
    private String genre;

    public ArtistEvent() {
    }

    public ArtistEvent(Long artistId, String name, String genre) {
        this.artistId = artistId;
        this.name = name;
        this.genre = genre;
    }

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
