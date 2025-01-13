package com.coderhouse.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name= "Clientes")
public class Cliente {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY) 
	private Long codeCliente;
	
	@Column(unique=true, nullable=false)
	private Long dni;
	
	private String name;
	private String lastName;
	
	@OneToMany(mappedBy="cliente", fetch = FetchType.EAGER)
	private List<Factura> facturasCliente = new ArrayList<>();
	
	public Cliente() {
		super();
	}
	
	public Cliente(Long dni, String lastName, String name) {
		this();
		this.name = name;
		this.lastName = lastName;
		this.dni = dni;
	}
	
	public Long getCodeCliente() {
		return codeCliente;
	}
	public void setCodeCliente(Long codeCliente) {
		this.codeCliente = codeCliente;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getApellido() {
		return lastName;
	}
	public void setApellido(String lastName) {
		this.lastName = lastName;
	}

	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Factura> getFacturasCliente() {
		return facturasCliente;
	}

	public void setFacturasCliente(List<Factura> facturasCliente) {
		this.facturasCliente = facturasCliente;
	}
	
	
}
