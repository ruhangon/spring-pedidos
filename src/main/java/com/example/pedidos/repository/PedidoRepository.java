package com.example.pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pedidos.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	Pedido findByNome(String nome);

}
