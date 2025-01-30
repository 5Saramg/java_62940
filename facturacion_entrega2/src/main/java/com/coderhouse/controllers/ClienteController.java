package com.coderhouse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.models.Cliente;
import com.coderhouse.services.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Gestion de clientes", description="Operaciones CRUD para clientes")
@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteServicio;
	
	@Operation(summary="Obtiene todos los clientes creados")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200", description="clientes obtenidos correctamente",
					content= {
							@Content(mediaType="application/json", schema = @Schema(implementation= Cliente.class))
					}),
			@ApiResponse(responseCode="404", description="No se pudieron obtener los clientes", 
					content= { @Content(mediaType="application/json", 
										schema= @Schema(implementation = ErrorResponse.class))}),
			@ApiResponse(responseCode = "500", description = "Error interno del Servidor", 
			content = @Content(mediaType = "application/json", 
			schema = @Schema(implementation = ErrorResponse.class))) 
			})
	@GetMapping
	public ResponseEntity<List<Cliente>> getAllClientes(){
		try {
			List<Cliente> clientes = clienteServicio.getAllClientes();
			return ResponseEntity.ok(clientes);
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Operation(summary="Obtiene un cliente por código")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200", description="Factura obtenida correctamente",
					content= {
							@Content(mediaType="application/json", schema = @Schema(implementation= Cliente.class))
					}),
			@ApiResponse(responseCode="404", description="No se pudo encontrar la factura", 
					content= { @Content	}),
			@ApiResponse(responseCode = "500", description = "Error interno del Servidor", 
			content = @Content(mediaType = "application/json", 
			schema = @Schema(implementation = ErrorResponse.class)))
	})
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable Long Id){
		try{
				Cliente cliente = clienteServicio.findById(Id);
				return ResponseEntity.ok(cliente);
			
				
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Operation(summary="Crea un cliente")
	@ApiResponses(value= {
			@ApiResponse(responseCode="201", description="Cliente creado",
					content= {
							@Content(mediaType="application/json", schema = @Schema(implementation= Cliente.class))
					}),
			@ApiResponse(responseCode = "500", description = "Error interno del Servidor", 
			content = @Content(mediaType = "application/json", 
			schema = @Schema(implementation = ErrorResponse.class)))
	})
	@PostMapping
	public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
		try {
			Cliente nuevoCliente = clienteServicio.saveCliente(cliente);
			return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Operation(summary="Edición de Cliente")
	@ApiResponses(value= {
			@ApiResponse(responseCode="201", description="Cliente actualizado correctamente",
					content= {
							@Content(mediaType="application/json", schema = @Schema(implementation= Cliente.class))
					}),
			@ApiResponse(responseCode="400", description="No se pudo obtener el cliente", 
					content= { @Content	}),
			@ApiResponse(responseCode = "500", description = "Error interno del Servidor", 
			content = @Content(mediaType = "application/json", 
			schema = @Schema(implementation = ErrorResponse.class)))
	})
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> updateClienteById(@PathVariable Long Id, @RequestBody Cliente clienteModificado){
		try {
			Cliente cliente = clienteServicio.updateClienteById(Id, clienteModificado);
			return ResponseEntity.ok(cliente);
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Operation(summary="Eliminar un cliente")
	@ApiResponses(value= {
			@ApiResponse(responseCode="204", description="Cliente eliminado correctamente",
					content= {
							@Content(mediaType="application/json", schema = @Schema(implementation= Cliente.class))
					}),
			@ApiResponse(responseCode="404", description="No se pudo eliminar el cliente", 
					content= { @Content	}),
			@ApiResponse(responseCode = "500", description = "Error interno del Servidor", 
			content = @Content(mediaType = "application/json", 
			schema = @Schema(implementation = ErrorResponse.class)))
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteClienteById(@PathVariable Long Id){
		try {
			clienteServicio.deleteClienteById(Id);
			return ResponseEntity.noContent().build();
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
}
