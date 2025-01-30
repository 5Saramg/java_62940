package com.coderhouse.models;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Schema(description = "Modelo de Factura")
@Entity
@Table(name= "Facturas")
public class Factura {
	
	@Schema(description="Número único de factura", requiredMode=Schema.RequiredMode.REQUIRED)
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY) 
	private Long codeFac;
	
	@Schema(description="Fecha de creación")
	private String fechaCreacion;
	
	@Schema(description="Total pagado", requiredMode=Schema.RequiredMode.REQUIRED)
	private int total;
	
	@Schema(description="Listado de productos en la factura", requiredMode=Schema.RequiredMode.REQUIRED)
	@ManyToMany(mappedBy= "facturasProducto", fetch = FetchType.EAGER)
	private List<Producto> productos = new ArrayList<>();
	
	@Schema(description="Cliente asociado a la factura", requiredMode=Schema.RequiredMode.REQUIRED)
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

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	
	
}
