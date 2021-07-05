package com.example.pedidos.controller.form;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.Length;

import com.example.pedidos.model.Pedido;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoForm {
	@NotBlank(message = "Nome não pode estar em branco")
	@Length(min = 1, max = 100, message = "O nome precisa ter entre {min} e {max} caracteres")
	private String nome;
	@Length(max = 500, message = "A descrição precisa ter no máximo {max} caracteres")
	private String descricao;
	@PastOrPresent(message = "A data não pode estar no futuro")
	private LocalDate data;

	public Pedido converter() {
		Pedido pedido = new Pedido(nome, descricao, data);
		return pedido;
	}

}
