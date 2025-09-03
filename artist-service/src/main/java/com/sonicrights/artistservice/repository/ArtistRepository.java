package com.sonicrights.artistservice.repository;

import com.sonicrights.artistservice.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long>, JpaSpecificationExecutor {
    // You can add custom queries later (e.g., findByGenre, findByCountry, etc.)

    Optional<Artist> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("SELECT a FROM Artist a " +
            "LEFT JOIN a.contracts c " +
            "GROUP BY a.id " +
            "ORDER BY SUM(COALESCE(c.amount, 0)) DESC")
    List<Artist> findAllOrderByTotalContractValueDesc();

    @Query("SELECT a FROM Artist a WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Artist> findByNameContainingIgnoreCase(String name);

    @Query("SELECT a FROM Artist a WHERE LOWER(a.genre) LIKE LOWER(CONCAT('%', :genre, '%'))")
    List<Artist> findByGenreContainingIgnoreCase(String genre);
}