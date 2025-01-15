package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.coderhouse.models.Factura;
import com.coderhouse.models.Producto;
import com.coderhouse.repositories.FacturaRepository;
import com.coderhouse.repositories.ProductoRepository;

import jakarta.transaction.Transactional;

public class ProductoService {
	
	@Autowired
	private ProductoRepository productoRepository;
	
	public List<Producto> getAllProductos(){
		return productoRepository.findAll();
	}
	
	public Producto findById(Long id) {
		return productoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
	}
	
	@Transactional
	public Producto saveProducto (Producto producto) {
		return productoRepository.save(producto);
	}
	
	@Transactional
	public Producto updateProductoById(Long id, Producto productoDetails) {
		Producto producto = productoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
		producto.setName(productoDetails.getName());
		producto.setPrice(productoDetails.getPrice());
		return productoRepository.save(producto);
	}
	
	@Transactional
	public void deleteProductoById(Long id) {
		if(!productoRepository.existsById(id)) {
			throw new IllegalArgumentException("Producto no encontrado");
		}
		
		productoRepository.deleteById(id);
		
	}
}
