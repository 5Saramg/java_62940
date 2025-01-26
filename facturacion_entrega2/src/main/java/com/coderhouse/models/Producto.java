package com.coderhouse.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name= "Productos")
public class Producto {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY) 
	private Long codeProd;
	
	private String name;
	private String descripcion;
	private int price;
	private int stock;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name= "producto_factura", 
			joinColumns= @JoinColumn(name= "code_prod"), 
			inverseJoinColumns = @JoinColumn(name= "code_fact"))
	private List<Factura> facturasProducto = new ArrayList<>();
	
	public Producto() {
		super();
	}
	
	public Producto(String name, int price, int stock, String descripcion) {
		this();
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.descripcion = descripcion;
	}
	


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public List<Factura> getOrdenes() {
		return facturasProducto;
	}

	public void setOrdenes(List<Factura> facturas) {
		this.facturasProducto = facturas;
	}

	public Long getCodeProd() {
		return codeProd;
	}

	public void setCodeProd(Long codeProd) {
		this.codeProd = codeProd;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public List<Factura> getFacturasProducto() {
		return facturasProducto;
	}

	public void setFacturasProducto(List<Factura> facturasProducto) {
		this.facturasProducto = facturasProducto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
