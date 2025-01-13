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

import com.coderhouse.models.Factura;
import com.coderhouse.repositories.FacturaRepository;

@RestController
@RequestMapping("/api/factura")
public class FacturaController {
	
	@Autowired
	private FacturaRepository facturaRepository;
	
	@GetMapping
	public List<Factura> getAllFacturas(){
		return facturaRepository.findAll();
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Factura> getClienteByDni(@PathVariable Long id){
		if(facturaRepository.existsById(id)) {
			Factura factura = facturaRepository.findById(id).get();
			return ResponseEntity.ok(factura);
			
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	@PostMapping
	public Factura createCliente(@RequestBody Factura factura) {
		return facturaRepository.save(factura);
	}

}
