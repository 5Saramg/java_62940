package com.coderhouse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.dto.timeDTO;
import com.coderhouse.services.TimeService;

@RestController
@RequestMapping("/api/fecha")
public class TimeController {
	
	@Autowired
	private TimeService fechaService;
	
	@GetMapping
	public ResponseEntity<String> getFecha() {
		timeDTO fecha = fechaService.obtenerFecha();
		
		if(fecha == null) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Error al obtener la fecha");
		}
		String date = fecha.getDate();
				
		return ResponseEntity.ok(date);
	}
}
