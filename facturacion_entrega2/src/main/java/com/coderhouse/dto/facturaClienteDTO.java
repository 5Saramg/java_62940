package com.coderhouse.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Modelo de asignacion de clientes a facturas",  title="Asignación de clientes")
public class facturaClienteDTO {
	
	private Long codeFac;
	private Long codeCliente;
	
	public Long getCodeFac() {
		return codeFac;
	}
	public void setCodeFac(Long codeFac) {
		this.codeFac = codeFac;
	}
	public Long getCodeCliente() {
		return codeCliente;
	}
	public void setCodeCliente(Long codeCliente) {
		this.codeCliente = codeCliente;
	}

}
