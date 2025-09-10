package com.sonicrights.artistservice.controller;

import com.sonicrights.artistservice.dto.ArtistDTO;
import com.sonicrights.artistservice.dto.ArtistEvent;
import com.sonicrights.artistservice.entity.Artist;
import com.sonicrights.artistservice.kafka.KafkaProducerService;
import com.sonicrights.artistservice.service.ArtistService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistService artistService;
    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public ArtistController(ArtistService artistService, KafkaProducerService kafkaProducerService) {
        this.artistService = artistService;
        this.kafkaProducerService = kafkaProducerService;
    }

    // Create artist
    @PostMapping
    public ResponseEntity<ArtistDTO> createArtist(@Valid @RequestBody ArtistDTO artistDTO) {
        ArtistDTO createdArtist = artistService.creatArtist(artistDTO);

        // Optional: send event to Kafka after saving
        ArtistEvent event = new ArtistEvent();
        event.setArtistId(createdArtist.getId());
        event.setName(createdArtist.getName());
        event.setGenre(createdArtist.getGenre());
        kafkaProducerService.sendArtistCreatedEvent(event);

        return new ResponseEntity<>(createdArtist, HttpStatus.CREATED);
    }

    // Get artist by ID
    @GetMapping("/{id}")
    public ResponseEntity<ArtistDTO> getArtistById(@PathVariable Long id) {
        Optional<ArtistDTO> artistDTO = artistService.getArtistById(id);
        return artistDTO.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get all artists
    @GetMapping
    public ResponseEntity<List<ArtistDTO>> getAllArtists() {
        List<ArtistDTO> artists = artistService.getALLArtists();
        return ResponseEntity.ok(artists);
    }

    // Update artist
    @PutMapping("/{id}")
    public ResponseEntity<ArtistDTO> updateArtist(@PathVariable Long id, @Valid @RequestBody ArtistDTO artistDTO) {
        Optional<ArtistDTO> updated = artistService.updateArtist(id, artistDTO);
        return updated.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete artist
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Long id) {
        boolean deleted = artistService.deleteArtist(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/All")
    public ResponseEntity<Page<Artist>> getAllArtists(
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) Boolean active,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name,asc") String sort) {

        if(page<0 || size<=0)
        {
            throw new IllegalArgumentException("Page must be >= 0 and size must be > 0");
        }
        Page<Artist> result = artistService.getAllArtists(genre, country, active, page, size, sort);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/sorted-by-contract-value")
    public ResponseEntity<List<Artist>> getArtistsSortedByTotalContractValue() {
        return ResponseEntity.ok(artistService.getArtistsSortedByTotalContractValue());
    }

    @GetMapping("/ArtistByPage")
    public List<Artist>geteartistsbyPage(Pageable pageable)
    {
        return artistService.getAllArtistsbypage(pageable).getContent();
    }

    @GetMapping("/search")
    public List<Artist> searchArtists(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String genre) {

        if (name != null) {
            return artistService.findByName(name);
        } else if (genre != null) {
            return artistService.findByGenre(genre);
        }
        return artistService.findAll();
    }

    @GetMapping("/searchMap")
    public List<Artist> searchArtists1(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String genre) {

        if (name != null) {
            return artistService.findByName(name);
        } else if (genre != null) {
            return artistService.findByGenre(genre);
        }
        return artistService.findAll();
    }

}