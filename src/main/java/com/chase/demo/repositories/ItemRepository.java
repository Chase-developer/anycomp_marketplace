package com.chase.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chase.demo.entities.Item;
import com.chase.demo.entities.Seller;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findBySeller(Seller seller); // for /sellers/{sellerId}/items
}
