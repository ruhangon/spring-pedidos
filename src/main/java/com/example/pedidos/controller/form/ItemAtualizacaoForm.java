package com.example.pedidos.controller.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
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
	@Length(max = 500, message = "A descrição precisa ter no máximo {max} caracteres")
	private String descricao;
	@Min(value = 1, message = "É preciso ter pelo menos uma quantidade do item")
	private Integer quantidade;
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
