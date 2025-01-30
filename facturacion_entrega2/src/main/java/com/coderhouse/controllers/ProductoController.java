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

import com.coderhouse.models.Producto;
import com.coderhouse.services.ProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Schema;

@Tag(name="Gestion de productos", description="Operaciones CRUD para productos")
@RestController
@RequestMapping("/api/producto")
public class ProductoController {
	
	@Autowired
	private ProductoService productoService;
	
	@Operation(summary="Obtiene todos los productos disponibles")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200", description="Productos obtenidos correctamente",
					content= {
							@Content(mediaType="application/json", schema = @Schema(implementation= Producto.class))
					}),
			@ApiResponse(responseCode="404", description="No se pudieron obtener los productos", 
					content= { @Content	}),
			@ApiResponse(responseCode = "500", description = "Error interno del Servidor", 
			content = @Content(mediaType = "application/json", 
			schema = @Schema(implementation = ErrorResponse.class)))
	})
	@GetMapping
	public ResponseEntity<List<Producto>> getAllProductos(){
		try {
			List<Producto> facturas = productoService.getAllProductos();
			return ResponseEntity.ok(facturas);
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Operation(summary="Obtiene un producto por código")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200", description="Producto obtenido correctamente",
					content= {
							@Content(mediaType="application/json", schema = @Schema(implementation= Producto.class))
					}),
			@ApiResponse(responseCode="404", description="No se pudo encontrar el producto", 
					content= { @Content	}),
			@ApiResponse(responseCode = "500", description = "Error interno del Servidor", 
			content = @Content(mediaType = "application/json", 
			schema = @Schema(implementation = ErrorResponse.class)))
	})
	@GetMapping("/{id}")
	public ResponseEntity<Producto> getProductoById(@PathVariable Long id){
		try{
			Producto producto = productoService.findById(id);
			return ResponseEntity.ok(producto);
			
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Operation(summary="Crea un producto")
	@ApiResponses(value= {
			@ApiResponse(responseCode="201", description="Producto creado correctamente",
					content= {
							@Content(mediaType="application/json", schema = @Schema(implementation= Producto.class))
					}),
			@ApiResponse(responseCode = "500", description = "Error interno del Servidor", 
			content = @Content(mediaType = "application/json", 
			schema = @Schema(implementation = ErrorResponse.class)))
	})
	@PostMapping
	public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
		try {
			Producto nuevoProducto = productoService.saveProducto(producto);
			return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Operation(summary="Edición de producto")
	@ApiResponses(value= {
			@ApiResponse(responseCode="201", description="Productos actualizado correctamente",
					content= {
							@Content(mediaType="application/json", schema = @Schema(implementation= Producto.class))
					}),
			@ApiResponse(responseCode="400", description="No se pudo obtener el productos", 
					content= { @Content	}),
			@ApiResponse(responseCode = "500", description = "Error interno del Servidor", 
			content = @Content(mediaType = "application/json", 
			schema = @Schema(implementation = ErrorResponse.class)))
	})
	@PutMapping("/{id}")
	public ResponseEntity<Producto> updateProductoById(@PathVariable Long Id, @RequestBody Producto productoModificado){
		try {
			Producto producto = productoService.updateProductoById(Id, productoModificado);
			return ResponseEntity.ok(producto);
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Operation(summary="Eliminar un producto")
	@ApiResponses(value= {
			@ApiResponse(responseCode="204", description="Producto eliminado correctamente",
					content= {
							@Content(mediaType="application/json", schema = @Schema(implementation= Producto.class))
					}),
			@ApiResponse(responseCode="404", description="No se pudieron obtener los productos", 
					content= { @Content	}),
			@ApiResponse(responseCode = "500", description = "Error interno del Servidor", 
			content = @Content(mediaType = "application/json", 
			schema = @Schema(implementation = ErrorResponse.class)))
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteFacturaById(@PathVariable Long Id){
		try {
			productoService.deleteProductoById(Id);
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
