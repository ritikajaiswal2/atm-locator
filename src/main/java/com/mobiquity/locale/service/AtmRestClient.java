package com.mobiquity.locale.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AtmRestClient {

	@Value("${ing.url.locator.atm}")
	private String ingAtmlURL;

	private RestTemplate template = new RestTemplate();

	public String getAllATMLocations() {
//		https://www.ing.nl/api/locator/atms/
//			 The json response is not well formed, the number of invalid characters leads
//			 the JSON contents
//			 )]}',
//      Using substring to make it valid		
		return template.getForObject(ingAtmlURL, String.class).substring(5);
	}

}
