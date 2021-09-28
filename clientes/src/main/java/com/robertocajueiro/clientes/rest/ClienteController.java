package com.robertocajueiro.clientes.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robertocajueiro.clientes.model.entity.Cliente;
import com.robertocajueiro.clientes.model.repository.ClientesRepository;


@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	
	private final ClientesRepository repository;
	
	@Autowired
	public ClienteController(ClientesRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping
	public java.util.List<Cliente> obterTodos(){
		return repository.findAll();
	}

}
