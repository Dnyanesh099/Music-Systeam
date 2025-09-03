package com.sonicrights.artistservice.repository;

import com.sonicrights.artistservice.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract,Long> {

    List<Contract> findByArtistId(Long artistId);

}
