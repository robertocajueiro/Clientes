package com.robertocajueiro.clientes.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.robertocajueiro.clientes.model.entity.Cliente;
import com.robertocajueiro.clientes.model.entity.ServicoPrestado;
import com.robertocajueiro.clientes.model.repository.ClienteRepository;
import com.robertocajueiro.clientes.model.repository.ServicoPrestadoRepository;
import com.robertocajueiro.clientes.rest.dto.ServicoPrestadoDTO;
import com.robertocajueiro.clientes.util.BigDecimalConverter;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/servicos-prestados")
@RequiredArgsConstructor
public class ServicoPrestadoController {
	
	private final ClienteRepository clienteRepository;
	private final ServicoPrestadoRepository repository;
	private final BigDecimalConverter bigDecimalConverter;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ServicoPrestado salvar( @RequestBody ServicoPrestadoDTO dto ) {
		
		LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		Integer idCliente = dto.getIdCliente();
		
		Cliente cliente =
				clienteRepository
					.findById(idCliente)
					.orElseThrow(() ->
						new ResponseStatusException(
								HttpStatus.BAD_REQUEST, "Cliente inexistente"));
		
		ServicoPrestado servicoPrestado = new ServicoPrestado();
		servicoPrestado.setDescricao(dto.getDescricao());
		servicoPrestado.setData(data);
		servicoPrestado.setCliente(cliente);
		servicoPrestado.setValor(bigDecimalConverter.converter(dto.getPreco()) );
		
		return repository.save(servicoPrestado);
		
	}

}
