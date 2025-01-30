package com.coderhouse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.coderhouse.dto.timeDTO;

@Service
public class TimeService {
	
	@Autowired
	private RestTemplate rt;
	
	public timeDTO obtenerFecha() {
		try {
			final String timeURL = "https://timeapi.io/api/Time/current/zone?timeZone=America/Bogota";
			return rt.getForObject(timeURL, timeDTO.class);
		}
		catch(RestClientException e) {
			System.err.println("Error con la API: " + e.getMessage());
		}
		return null;
	};
	
}
