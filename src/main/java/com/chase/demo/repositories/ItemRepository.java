package com.chase.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chase.demo.entities.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
