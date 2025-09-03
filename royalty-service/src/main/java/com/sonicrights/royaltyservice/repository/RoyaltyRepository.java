package com.sonicrights.royaltyservice.repository;

import com.sonicrights.royaltyservice.entity.Royalty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoyaltyRepository extends JpaRepository<Royalty, Long> {
}
