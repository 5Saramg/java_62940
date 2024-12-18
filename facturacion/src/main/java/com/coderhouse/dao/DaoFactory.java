package com.coderhouse.dao;

import org.springframework.stereotype.Service;

import com.coderhouse.models.Factura;
import com.coderhouse.models.Producto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class DaoFactory {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public void persistirFactura(Factura factura) {
		em.persist(factura);
	}
	
	@Transactional
	public void persistirProducto(Producto producto) {
		em.persist(producto);
	}

}
