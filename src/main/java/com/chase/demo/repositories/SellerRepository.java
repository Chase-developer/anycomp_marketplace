package com.chase.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chase.demo.entities.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
    // Custom queries can go here too
	Optional<Seller> findByEmail(String email);
}
