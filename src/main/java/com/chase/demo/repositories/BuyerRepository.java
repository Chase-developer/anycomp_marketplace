package com.chase.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chase.demo.entities.Buyer;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {
	Optional<Buyer> findByEmail(String email);
}
