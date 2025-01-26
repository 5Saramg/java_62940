package com.coderhouse.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "Facturas")
public class Factura {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY) 
	private Long codeFac;
	
	private Date fechaCreacion;
	
	private int total;
	
	@ManyToMany(mappedBy= "facturasProducto", fetch = FetchType.EAGER)
	private List<Producto> productos = new ArrayList<>();
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Cliente cliente;
	
	public Factura() {
		super();
	}
	
	public Factura(int total) {
		this();
		this.total = total;
	}
	
	public Long getCode() {
		return codeFac;
	}

	public void setCode(Long codeFac) {
		this.codeFac = codeFac;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Long getCodeFac() {
		return codeFac;
	}

	public void setCodeFac(Long codeFac) {
		this.codeFac = codeFac;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	
}
