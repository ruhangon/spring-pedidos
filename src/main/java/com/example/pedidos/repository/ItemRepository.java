package com.example.pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pedidos.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
