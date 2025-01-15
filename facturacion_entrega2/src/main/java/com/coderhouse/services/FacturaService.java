package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.coderhouse.models.Factura;
import com.coderhouse.repositories.FacturaRepository;

import jakarta.transaction.Transactional;

public class FacturaService {
	
	@Autowired
	private FacturaRepository facturaRepository;
	
	public List<Factura> getAllFacturas(){
		return facturaRepository.findAll();
	}
	
	public Factura findById(Long id) {
		return facturaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Factura no encontrado"));
	}
	
	@Transactional
	public Factura saveFactura (Factura factura) {
		return facturaRepository.save(factura);
	}
	
	@Transactional
	public Factura updateFacturaeById(Long id, Factura facturaDetails) {
		Factura factura = facturaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Factura no encontrado"));
		factura.setTotal(facturaDetails.getTotal());
		
		
		return facturaRepository.save(factura);
	}
	
	@Transactional
	public void deleteFacturaById(Long id) {
		if(!facturaRepository.existsById(id)) {
			throw new IllegalArgumentException("Factura no encontrado");
		}
		
		facturaRepository.deleteById(id);
		
	}

}
