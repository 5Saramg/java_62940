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

import com.coderhouse.dto.facturaClienteDTO;
import com.coderhouse.dto.productosEnFacturaDTO;
import com.coderhouse.dto.timeDTO;
import com.coderhouse.models.Factura;
import com.coderhouse.services.FacturaService;
import com.coderhouse.services.TimeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Gestion de facturas", description="Operaciones CRUD para facturas")
@RestController
@RequestMapping("/api/factura")
public class FacturaController {
	
	@Autowired
	private FacturaService facturaService;
	
	@Autowired
	private TimeService fechaService;
	
	@Operation(summary="Obtiene todas facturas generadas")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200", description="facturas obtenidos correctamente",
					content= {
							@Content(mediaType="application/json", schema = @Schema(implementation= Factura.class))
					}),
			@ApiResponse(responseCode="404", description="No se pudieron obtener las facturas", 
					content= { @Content	}),
			@ApiResponse(responseCode = "500", description = "Error interno del Servidor", 
			content = @Content(mediaType = "application/json", 
			schema = @Schema(implementation = ErrorResponse.class)))
	})
	@GetMapping
	public ResponseEntity<List<Factura>> getAllFacturas(){
		try {
			List<Factura> facturas = facturaService.getAllFacturas();
			return ResponseEntity.ok(facturas);
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Operation(summary="Obtiene una factura por código")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200", description="Factura obtenida correctamente",
					content= {
							@Content(mediaType="application/json", schema = @Schema(implementation= Factura.class))
					}),
			@ApiResponse(responseCode="404", description="No se pudo encontrar la factura", 
					content= { @Content	}),
			@ApiResponse(responseCode = "500", description = "Error interno del Servidor", 
			content = @Content(mediaType = "application/json", 
			schema = @Schema(implementation = ErrorResponse.class)))
	})
	@GetMapping("/{id}")
	public ResponseEntity<Factura> getFacturaById(@PathVariable Long id){
		try{
			Factura factura = facturaService.findById(id);
			return ResponseEntity.ok(factura);
		
			
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Operation(summary="Crea una Factura")
	@ApiResponses(value= {
			@ApiResponse(responseCode="201", description="Factura creada correctamente",
					content= {
							@Content(mediaType="application/json", schema = @Schema(implementation= Factura.class))
					}),
			@ApiResponse(responseCode="500", description="Error en el servidor", 
			content= { @Content	})
	})
	@PostMapping
	public ResponseEntity<Factura> createFactura(@RequestBody Factura factura) {
		try {
			timeDTO time = fechaService.obtenerFecha();
			if(time != null) {
				factura.setFechaCreacion(time.getDate());
			}
			
			Factura nuevaFactura = facturaService.saveFactura(factura);
			return ResponseEntity.status(HttpStatus.CREATED).body(nuevaFactura);
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Operation(summary="Edición de Factura")
	@ApiResponses(value= {
			@ApiResponse(responseCode="201", description="Factura actualizada correctamente",
					content= {
							@Content(mediaType="application/json", schema = @Schema(implementation= Factura.class))
					}),
			@ApiResponse(responseCode="400", description="No se pudo obtener el productos", 
					content= { @Content	}),
			@ApiResponse(responseCode = "500", description = "Error interno del Servidor", 
			content = @Content(mediaType = "application/json", 
			schema = @Schema(implementation = ErrorResponse.class)))
	})
	@PutMapping("/{id}")
	public ResponseEntity<Factura> updateFacturaById(@PathVariable Long Id, @RequestBody Factura facturaModificado){
		try {
			Factura factura = facturaService.updateFacturaeById(Id, facturaModificado);
			return ResponseEntity.ok(factura);
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	@Operation(summary="Eliminar una factura")
	@ApiResponses(value= {
			@ApiResponse(responseCode="204", description="Factura eliminada correctamente",
					content= {
							@Content(mediaType="application/json", schema = @Schema(implementation= Factura.class))
					}),
			@ApiResponse(responseCode="404", description="No se pudo eliminar la factura", 
					content= { @Content	}),
			@ApiResponse(responseCode = "500", description = "Error interno del Servidor", 
			content = @Content(mediaType = "application/json", 
			schema = @Schema(implementation = ErrorResponse.class)))
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteFacturaById(@PathVariable Long Id){
		try {
			facturaService.deleteFacturaById(Id);
			return ResponseEntity.noContent().build();
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Operation(summary="Asignar productos a una Factura")
	@ApiResponses(value= {
			@ApiResponse(responseCode="201", description="Productos asignados correctamente",
					content= {
							@Content(mediaType="application/json", schema = @Schema(implementation= Factura.class))
					}),
			@ApiResponse(responseCode = "500", description = "Error interno del Servidor", 
			content = @Content(mediaType = "application/json", 
			schema = @Schema(implementation = ErrorResponse.class)))
	})
	@PostMapping("/asignarProductos")
	public ResponseEntity<Factura> asignarProductosFactura(@RequestBody productosEnFacturaDTO dto) {
		try{
			Factura factura = facturaService.asignarProductos(dto);
			return ResponseEntity.ok(factura);
			
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Operation(summary="Asignar Cliente a una Factura")
	@ApiResponses(value= {
			@ApiResponse(responseCode="201", description="Cliente asignado correctamente",
					content= {
							@Content(mediaType="application/json", schema = @Schema(implementation= Factura.class))
					}),
			@ApiResponse(responseCode = "500", description = "Error interno del Servidor", 
			content = @Content(mediaType = "application/json", 
			schema = @Schema(implementation = ErrorResponse.class)))
	})
	@PostMapping("/asignarCliente")
	public ResponseEntity<Factura> asignarClienteFactura(@RequestBody facturaClienteDTO dto){
		try{
			Factura factura = facturaService.asignarCliente(dto.getCodeCliente(), dto.getCodeFac());
			return ResponseEntity.ok(factura);
			
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
