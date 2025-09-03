package com.sonicrights.artistservice.mapper;

import com.sonicrights.artistservice.dto.ArtistDTO;
import com.sonicrights.artistservice.entity.Artist;

public class ArtistMapper {

    // Convert DTO → Entity
    public static Artist toEntity(ArtistDTO dto) {
        if (dto == null) return null;

        Artist artist = new Artist();
        artist.setId(dto.getId());
        artist.setName(dto.getName());
        artist.setGenre(dto.getGenre());
        artist.setDebutDate(dto.getDebutDate());
        artist.setCountry(dto.getCountry());
        artist.setActive(dto.isActive());
        artist.setEmail(dto.getEmail());

        return artist;
    }

    // Convert Entity → DTO
    public static ArtistDTO toDTO(Artist artist) {
        if (artist == null) return null;

        return new ArtistDTO(
                artist.getId(),
                artist.getName(),
                artist.getGenre(),
                artist.getDebutDate(),
                artist.getCountry(),
                artist.isActive(),
                artist.getEmail()
        );
    }
}
