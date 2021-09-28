package com.robertocajueiro.clientes.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.robertocajueiro.clientes.model.entity.Cliente;

public interface ClientesRepository extends JpaRepository<Cliente, Integer> {

}
