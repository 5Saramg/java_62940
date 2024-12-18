package com.coderhouse.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name= "Facturas")
public class Factura {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY) 
	private Long codeFac;
	
	private int total;
	
	@ManyToMany(mappedBy= "facturas", fetch = FetchType.EAGER)
	private List<Producto> productos = new ArrayList<>();
	
	public Factura() {
		super();
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

}
