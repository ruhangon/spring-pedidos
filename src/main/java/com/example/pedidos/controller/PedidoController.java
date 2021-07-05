package com.example.pedidos.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.pedidos.controller.dto.PedidoDto;
import com.example.pedidos.controller.form.PedidoForm;
import com.example.pedidos.model.Pedido;
import com.example.pedidos.repository.PedidoRepository;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	@Autowired
	private PedidoRepository pedidoRep;

	@GetMapping
	public List<PedidoDto> lista() {
		List<Pedido> pedidos = pedidoRep.findAll();
		return PedidoDto.converter(pedidos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PedidoDto> detalha(@PathVariable Long id) {
		Optional<Pedido> pedido = pedidoRep.findById(id);
		if (pedido.isPresent()) {
			return ResponseEntity.ok(new PedidoDto(pedido.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@Transactional
	@PostMapping
	public ResponseEntity<PedidoDto> insere(@RequestBody @Valid PedidoForm form, UriComponentsBuilder uriBuilder) {
		Pedido pedido = form.converter();
		pedidoRep.save(pedido);
		URI uri = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedido.getId()).toUri();
		return ResponseEntity.created(uri).body(new PedidoDto(pedido));
	}

}
