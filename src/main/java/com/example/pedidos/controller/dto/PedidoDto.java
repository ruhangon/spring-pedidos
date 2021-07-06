package com.example.pedidos.controller.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.example.pedidos.model.Pedido;

import lombok.Getter;

@Getter
public class PedidoDto {
	private Long id;
	private String nome;
	private String descricao;
	private LocalDate data;

	public PedidoDto(Pedido pedido) {
		this.id = pedido.getId();
		this.nome = pedido.getNome();
		this.descricao = pedido.getDescricao();
		this.data = pedido.getData();
	}

	public static List<PedidoDto> converter(List<Pedido> pedidos) {
		return pedidos.stream().map(PedidoDto::new).collect(Collectors.toList());
	}

}
