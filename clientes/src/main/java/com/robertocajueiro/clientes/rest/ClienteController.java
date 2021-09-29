package com.robertocajueiro.clientes.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;


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
	
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar( @RequestBody @Validated Cliente cliente ){
        return repository.save(cliente);
    }
    
    @GetMapping
	public List<Cliente> obterTodos(){
		return repository.findAll();
	}
    
    @GetMapping("{id}")
    public Cliente acharPorId(@PathVariable Integer id) {
    	return repository
    			.findById(id)
    			.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND) );
    }
    
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id) {
    	 repository
    	 .findById(id)
    	 .map(cliente -> {
    		repository.delete(cliente);
    		return Void.TYPE;
    	 })
    	 .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND) );
    }
    
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody Cliente clienteAtualizado) {
    	repository
	   	 .findById(id)
	   	 .map(cliente -> {
	   		clienteAtualizado.setId(cliente.getId());
	   		return repository.save(clienteAtualizado);
	   	 })
	   	 .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND) );
	    }
    

}
