package com.example.pedidos.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.pedidos.controller.dto.ItemDetalhadoDto;
import com.example.pedidos.controller.dto.ItemDto;
import com.example.pedidos.controller.form.ItemAtualizacaoForm;
import com.example.pedidos.controller.form.ItemForm;
import com.example.pedidos.model.Item;
import com.example.pedidos.repository.ItemRepository;

@RestController
@RequestMapping("/itens")
public class ItemController {
	@Autowired
	private ItemRepository itemRep;

	@GetMapping
	public List<ItemDto> lista() {
		List<Item> itens = itemRep.findAll();
		return ItemDto.converter(itens);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ItemDetalhadoDto> detalha(@PathVariable Long id) {
		Optional<Item> item = itemRep.findById(id);
		if (item.isPresent()) {
			return ResponseEntity.ok(new ItemDetalhadoDto(item.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@Transactional
	@PostMapping
	public ResponseEntity<ItemDto> insere(@RequestBody @Valid ItemForm form, UriComponentsBuilder uriBuilder) {
		Item item = form.converter();
		itemRep.save(item);
		URI uri = uriBuilder.path("/itens/{id}").buildAndExpand(item.getId()).toUri();
		return ResponseEntity.created(uri).body(new ItemDto(item));
	}

	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<ItemDto> atualiza(@PathVariable Long id, @RequestBody @Valid ItemAtualizacaoForm form) {
		Optional<Item> optional = itemRep.findById(id);
		if (optional.isPresent()) {
			Item item = form.atualiza(id, itemRep);
			return ResponseEntity.ok(new ItemDto(item));
		}
		return ResponseEntity.notFound().build();
	}

	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleta(@PathVariable Long id) {
		Optional<Item> optional = itemRep.findById(id);
		if (optional.isPresent()) {
			itemRep.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
