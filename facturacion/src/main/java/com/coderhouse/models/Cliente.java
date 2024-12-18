package com.coderhouse.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "Clientes")
public class Cliente {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY) 
	private Long codeCliente;
	
	private String name;
	private String lastName;
	
	public Cliente() {
		super();
	}
	
	public Cliente(String lastName, String name) {
		this();
		this.name = name;
		this.lastName = lastName;
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
}
