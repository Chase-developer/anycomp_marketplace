package com.chase.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chase.demo.entities.Buyer;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {
    // You can add custom query methods here if needed
}
