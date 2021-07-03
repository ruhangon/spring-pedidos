package com.example.pedidos.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.example.pedidos.model.Item;

import lombok.Getter;

@Getter
public class ItemDto {
	private Long id;
	private String nome;
	private String descricao;
	private Integer quantidade;
	private Double custo;

	public ItemDto(Item item) {
		this.id = item.getId();
		this.nome = item.getNome();
		this.descricao = item.getDescricao();
		this.quantidade = item.getQuantidade();
		this.custo = item.getCusto();
	}

	public static List<ItemDto> converter(List<Item> itens) {
		return itens.stream().map(ItemDto::new).collect(Collectors.toList());
	}

}
