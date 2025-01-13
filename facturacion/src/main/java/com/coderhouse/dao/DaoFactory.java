package com.coderhouse.dao;

import org.springframework.stereotype.Service;

import com.coderhouse.models.Cliente;
import com.coderhouse.models.Factura;
import com.coderhouse.models.Producto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
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
	
	@Transactional
	public void persistirCliente(Cliente cliente) {
		em.persist(cliente);
	}
	
	@Transactional
	public Cliente getClienteById(Long id) throws Exception{
		try {
			TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c WHERE c.id = :id", Cliente.class);
			return query.setParameter("id", id).getSingleResult();
		}
		catch(Exception e) {
			throw new Exception("El cliente no existe");
		}
	}
	
	@Transactional
	public Factura getFacturaById(Long id) throws Exception{
		try {
			TypedQuery<Factura> query = em.createQuery("SELECT f FROM Factura f WHERE f.id = :id", Factura.class);
			return query.setParameter("id", id).getSingleResult();
		}
		catch(Exception e) {
			throw new Exception("El cliente no existe");
		}
	}
	
	@Transactional
	public void asignarFacturaCliente(Long clienteId, Long facturaId) throws Exception {
		Cliente cliente = getClienteById(clienteId);
		Factura factura = getFacturaById(facturaId);
		
		if(cliente == null){
			throw new Exception("El cliente con Id "+ clienteId+" no existe");
		}
		
		if(factura == null){
			throw new Exception("La factura con Id "+ facturaId+" no existe");
		}
		
		factura.setCliente(cliente);
		em.merge(factura);
		
	}

}
