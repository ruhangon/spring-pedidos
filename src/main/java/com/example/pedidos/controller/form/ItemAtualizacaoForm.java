package com.example.pedidos.controller.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

import com.example.pedidos.model.Item;
import com.example.pedidos.repository.ItemRepository;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemAtualizacaoForm {
	@NotBlank(message = "Nome não pode estar em branco")
	@Length(min = 1, max = 100, message = "O nome precisa ter entre {min} e {max} caracteres")
	private String nome;
	@NotNull(message = "A descrição não pode ser nula")
	@Length(max = 500, message = "A descrição precisa ter no máximo {max} caracteres")
	private String descricao;
	@NotNull(message = "A quantidade não pode ser nula")
	@Min(value = 1, message = "É preciso ter pelo menos uma quantidade do item")
	private Integer quantidade;
	@NotNull(message = "O custo não pode ser nulo")
	@Positive(message = "O custo precisa ser um valor positivo")
	private Double custo;

	public Item atualiza(Long id, ItemRepository itemRep) {
		Item item = itemRep.getOne(id);
		item.setNome(this.nome);
		item.setDescricao(this.descricao);
		item.setQuantidade(this.quantidade);
		item.setCusto(this.custo);
		return item;
	}

}
