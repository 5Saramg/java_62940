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
	private int price;
	
	public Producto() {
		super();
	}
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name= "producto_factura", 
			joinColumns= @JoinColumn(name= "code_prod"), 
			inverseJoinColumns = @JoinColumn(name= "code_fact"))
	private List<Factura> facturas = new ArrayList<>();

	public Long getcodeFac() {
		return codeProd;
	}

	public void setcodeFac(Long codeProd) {
		this.codeProd = codeProd;
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
		return facturas;
	}

	public void setOrdenes(List<Factura> facturas) {
		this.facturas = facturas;
	}
}
