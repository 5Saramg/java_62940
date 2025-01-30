package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.dto.productosEnFacturaDTO;
import com.coderhouse.models.Cliente;
import com.coderhouse.models.Factura;
import com.coderhouse.models.Producto;
import com.coderhouse.repositories.ClienteRepository;
import com.coderhouse.repositories.FacturaRepository;
import com.coderhouse.repositories.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class FacturaService {
	
	@Autowired
	private FacturaRepository facturaRepository;
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
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
	
	@Transactional
	public Factura asignarProductos(productosEnFacturaDTO dto) {
		Factura factura = facturaRepository.findById(dto.getCodeFac()).orElseThrow(() -> new IllegalArgumentException("Factura no encontrado"));
		
		for(Long prodId : dto.getProductIds()){
			Producto producto = productoRepository.findById(prodId).orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
			factura.getProductos().add(producto);
			producto.getOrdenes().add(factura);
			productoRepository.save(producto);
		}
		
		return facturaRepository.save(factura);
	} 
	
	@Transactional
	public Factura asignarCliente(Long codeCliente, Long codeFac) {
		
		Cliente cliente = clienteRepository.findById(codeCliente).orElseThrow(() -> new IllegalArgumentException("Cliente no registrado"));
		Factura factura = facturaRepository.findById(codeFac).orElseThrow(() -> new IllegalArgumentException("Factura no encontrado"));
		
		factura.setCliente(cliente);
		cliente.getFacturasCliente().add(factura);
		clienteRepository.save(cliente);
		return facturaRepository.save(factura);
		
	}

}
