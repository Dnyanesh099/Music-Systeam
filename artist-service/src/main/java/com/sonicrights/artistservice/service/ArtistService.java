package com.sonicrights.artistservice.service;

import com.sonicrights.artistservice.dto.ArtistDTO;
import com.sonicrights.artistservice.entity.Artist;
import com.sonicrights.artistservice.mapper.ArtistMapper;
import com.sonicrights.artistservice.repository.ArtistRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.sonicrights.artistservice.helperSpecification.ArtistSpecifications;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    // Create Artist
    public ArtistDTO creatArtist(ArtistDTO artistDTO) {
        Artist artist = ArtistMapper.toEntity(artistDTO);
        Artist saved = artistRepository.save(artist);
        return ArtistMapper.toDTO(saved);
    }

    // Get all artists
    public List<ArtistDTO> getALLArtists() {
        return artistRepository.findAll()
                .stream()
                .map(ArtistMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Get artist by ID
    public Optional<ArtistDTO> getArtistById(Long id) {
        return artistRepository.findById(id)
                .map(ArtistMapper::toDTO);
    }

    // Update artist
    public Optional<ArtistDTO> updateArtist(Long id, ArtistDTO artistDTO) {
        return artistRepository.findById(id).map(existingArtist -> {
            existingArtist.setName(artistDTO.getName());
            existingArtist.setGenre(artistDTO.getGenre());
            existingArtist.setDebutDate(artistDTO.getDebutDate());
            existingArtist.setCountry(artistDTO.getCountry());
            existingArtist.setActive(artistDTO.isActive());
            existingArtist.setEmail(artistDTO.getEmail());

            Artist updated = artistRepository.save(existingArtist);
            return ArtistMapper.toDTO(updated);
        });
    }

    // Delete artist
    public boolean deleteArtist(Long id) {
        if (artistRepository.existsById(id)) {
            artistRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Page<Artist> getAllArtists(String genre, String country, Boolean active, int page, int size, String sort) {
        String[] sortParts = sort.split(",");
        String sortField = sortParts[0];
        Sort.Direction direction = sortParts.length > 1 && sortParts[1].equalsIgnoreCase("desc")
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortField));

        Specification<Artist> spec = Specification
                .where(ArtistSpecifications.hasGenre(genre))
                .and(ArtistSpecifications.hasCountry(country))
                .and(ArtistSpecifications.isActive(active));

        Page<Artist> artists = artistRepository.findAll(spec, pageable);

        if (artists.isEmpty()) {
            throw new RuntimeException("No artists found matching the criteria");
        }

        return artists;
    }

    public List<Artist> getArtistsSortedByTotalContractValue() {
        return artistRepository.findAllOrderByTotalContractValueDesc();
    }

    public Page<Artist> getAllArtistsbypage( Pageable pageable)
    {
        return artistRepository.findAll(pageable);
    }
    public List<Artist> findByName(String name) {
        return artistRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Artist> findByGenre(String genre) {
        return artistRepository.findByGenreContainingIgnoreCase(genre);
    }

    public List<Artist> findAll() {
        return artistRepository.findAll();
    }
}