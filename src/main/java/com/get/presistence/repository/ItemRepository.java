package com.get.presistence.repository;


import com.get.businesslayer.response.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, String> {
}