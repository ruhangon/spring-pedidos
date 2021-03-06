package com.example.pedidos.controller.dto;

import com.example.pedidos.model.Item;

import lombok.Getter;

@Getter
public class ItemDetalhadoDto {
	private Long id;
	private String nome;
	private String descricao;
	private Integer quantidade;
	private Double custo;
	private Long idPedido;
	private String nomePedido;
	private String descricaoPedido;

	public ItemDetalhadoDto(Item item) {
		this.id = item.getId();
		this.nome = item.getNome();
		this.descricao = item.getDescricao();
		this.quantidade = item.getQuantidade();
		this.custo = item.getCusto();
		this.idPedido = item.getPedido().getId();
		this.nomePedido = item.getPedido().getNome();
		this.descricaoPedido = item.getPedido().getDescricao();
	}

}
