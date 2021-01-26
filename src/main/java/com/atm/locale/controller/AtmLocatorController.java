package com.atm.locale.controller;

import static com.atm.locale.utils.AppConstants.CONTENT_TYPE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atm.locale.service.AtmRestClient;
import com.atm.locale.utils.IngResponseParser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class AtmLocatorController {

	@Autowired
	private AtmRestClient atmRestClient;

	@RequestMapping(method = RequestMethod.GET, value = "/atms", produces = CONTENT_TYPE)
	public ResponseEntity<String> getAtmLocationList() {

		log.info("Request initiated to get ATM location");
		return new ResponseEntity<String>(atmRestClient.getAllATMLocations(), null, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/atms/{city}", produces = CONTENT_TYPE)
	public ResponseEntity<String> searchAtmByCity(@PathVariable(name = "city", required = true) String city) {

		log.info("Request initiated to get ATM location by city");
		String value = atmRestClient.getAllATMLocations();
		return new ResponseEntity<String>(IngResponseParser.filterByCity(value, city), null, HttpStatus.OK);

	}

}
