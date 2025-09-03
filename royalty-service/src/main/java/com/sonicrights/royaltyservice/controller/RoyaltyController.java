package com.sonicrights.royaltyservice.controller;

import com.sonicrights.royaltyservice.entity.Royalty;
import com.sonicrights.royaltyservice.repository.RoyaltyRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/royalties")
public class RoyaltyController {

    private final RoyaltyRepository repository;

    public RoyaltyController(RoyaltyRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Royalty> create(@RequestBody Royalty royalty) {
        return ResponseEntity.ok(repository.save(royalty));
    }

    @GetMapping
    public List<Royalty> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Royalty> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
