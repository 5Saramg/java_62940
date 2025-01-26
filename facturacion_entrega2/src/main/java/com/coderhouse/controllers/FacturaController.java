package com.coderhouse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.dto.facturaClienteDTO;
import com.coderhouse.dto.productosEnFacturaDTO;
import com.coderhouse.models.Factura;
import com.coderhouse.services.FacturaService;

@RestController
@RequestMapping("/api/factura")
public class FacturaController {
	
	@Autowired
	private FacturaService facturaService;
	
	@GetMapping
	public ResponseEntity<List<Factura>> getAllFacturas(){
		try {
			List<Factura> facturas = facturaService.getAllFacturas();
			return ResponseEntity.ok(facturas);
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Factura> getFacturaById(@PathVariable Long id){
		try{
			Factura factura = facturaService.findById(id);
			return ResponseEntity.ok(factura);
		
			
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	@PostMapping
	public ResponseEntity<Factura> createFactura(@RequestBody Factura factura) {
		try {
			Factura nuevaFactura = facturaService.saveFactura(factura);
			return ResponseEntity.status(HttpStatus.CREATED).body(nuevaFactura);
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Factura> updateFacturaById(@PathVariable Long Id, @RequestBody Factura facturaModificado){
		try {
			Factura factura = facturaService.updateFacturaeById(Id, facturaModificado);
			return ResponseEntity.ok(factura);
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteFacturaById(@PathVariable Long Id){
		try {
			facturaService.deleteFacturaById(Id);
			return ResponseEntity.noContent().build();
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping("/asignarProductos")
	public ResponseEntity<Factura> asignarProductosFactura(@RequestBody productosEnFacturaDTO dto) {
		try{
			Factura factura = facturaService.asignarProductos(dto);
			return ResponseEntity.ok(factura);
			
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping("/asignarCliente")
	public ResponseEntity<Factura> asignarClienteFactura(@RequestBody facturaClienteDTO dto){
		try{
			Factura factura = facturaService.asignarCliente(dto.getCodeCliente(), dto.getCodeFac());
			return ResponseEntity.ok(factura);
			
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
