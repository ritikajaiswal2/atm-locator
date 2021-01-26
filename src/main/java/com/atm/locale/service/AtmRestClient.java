package com.atm.locale.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AtmRestClient {

	@Value("${ing.url.locator.atm}")
	private String ingAtmLocatorURL;

	private RestTemplate template = new RestTemplate();

	public String getAllATMLocations() {
//		https://www.ing.nl/api/locator/atms/
//	    The json response is not valid, it has invalid characters at the beginning )]}',
//      Using substring to make it valid		
		return template.getForObject(ingAtmLocatorURL, String.class).substring(5);
	}

}
