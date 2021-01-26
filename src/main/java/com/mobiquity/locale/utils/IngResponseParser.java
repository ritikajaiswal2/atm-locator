package com.mobiquity.locale.utils;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class IngResponseParser {

	private static final ObjectMapper mapper = new ObjectMapper();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String filterByCity(String inputJSON, String city) {
		try {
			List<Map> locations = readValue(inputJSON);
			Iterator<Map> it = locations.iterator();

			while (it.hasNext()) {
				Map<String, Map> address = it.next();
				if (city != null && city.length() > 0
						&& !city.trim().equalsIgnoreCase((String) address.get("address").get("city"))) {
					it.remove();
					continue;
				}
			}

			return writeValueAsString(locations);
		} catch (IOException e) {
			// Just ignore
			log.error(e.getMessage(), e);
		}

		return "";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected static List<Map> readValue(String json) throws JsonParseException, JsonMappingException, IOException {

		return mapper.readValue(json, List.class);
	}

	protected static <T> String writeValueAsString(T object) throws JsonProcessingException {
		return mapper.writeValueAsString(object);
	}

}
