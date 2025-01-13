package com.coderhouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.coderhouse.dao.DaoFactory;
import com.coderhouse.models.Cliente;
import com.coderhouse.models.Factura;
import com.coderhouse.models.Producto;

@SpringBootApplication
public class FacturacionApplication implements CommandLineRunner {
	
	@Autowired
	private DaoFactory dao;

	public static void main(String[] args) {
		SpringApplication.run(FacturacionApplication.class, args);
	}
	
	@Override
	public void run(String...arg) throws Exception{
		
		try {
			
			Producto prod1 = new Producto("Tomate", 1);
			Producto prod2 = new Producto("Cebolla", 2);
			Producto prod3 = new Producto("Carne molida", 5);
			Producto prod4 = new Producto("Pasta", 8);
			Producto prod5 = new Producto("Pollo", 4);
			
			Factura fact1 = new Factura(001L, 123);
			Factura fact2 = new Factura(002L, 23423);
			Factura fact3 = new Factura(003L,1234233);
			Factura fact4 = new Factura(004L,734);
			
			Cliente cliente1 = new Cliente(111L,"Milean", "Bormeo");
			Cliente cliente2 = new Cliente(112L,"Karen", "Molinos");
			Cliente cliente3 = new Cliente(113L,"Daniela", "Medina");
			Cliente cliente4 = new Cliente(114L,"Sol", "Silva");
			
			dao.persistirFactura(fact1);
			dao.persistirFactura(fact2);
			dao.persistirFactura(fact3);
			dao.persistirFactura(fact4);
			
			
			dao.persistirProducto(prod1);
			dao.persistirProducto(prod2);
			dao.persistirProducto(prod3);
			dao.persistirProducto(prod4);
			dao.persistirProducto(prod5);
			
			
			dao.persistirCliente(cliente1);
			dao.persistirCliente(cliente2);
			
			dao.asignarFacturaCliente(cliente1.getDni(), fact1.getCodeFac());
			dao.asignarFacturaCliente(cliente1.getDni(), fact2.getCodeFac());
			dao.asignarFacturaCliente(cliente2.getDni(), fact3.getCodeFac());
			dao.asignarFacturaCliente(cliente4.getDni(), fact4.getCodeFac());
			
		}
		catch(Exception e){
			e.printStackTrace(System.err);
		}
		
	}

}
