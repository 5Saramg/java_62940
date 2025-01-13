package com.coderhouse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.models.Cliente;
import com.coderhouse.repositories.ClienteRepository;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping
	public List<Cliente> getAllClientes(){
		return clienteRepository.findAll();
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getClienteByDni(@PathVariable Long dni){
		if(clienteRepository.existsById(dni)) {
			Cliente cliente = clienteRepository.findById(dni).get();
			return ResponseEntity.ok(cliente);
			
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	@PostMapping
	public Cliente createCliente(@RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	
}
