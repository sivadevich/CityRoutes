package com.example.code.roadsService.service;

import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class CityService implements ICityService {

	@Autowired
	private ResourceLoader resourceLoader;

	@Override
	public String findCityRoutes(String origin, String destination) {
		String returnValue = "No";
		// TODO Auto-generated method stub
		Resource fileResource = resourceLoader.getResource("classpath:city.txt");
		Map<String, String> cityMap = new HashMap<String, String>();
		try (Stream<String> stream = Files.lines(fileResource.getFile().toPath())) {
			cityMap = stream.map(line -> line).collect(Collectors.toList()).stream()
					.collect(Collectors.toMap(p -> p.split(",").clone()[0], p -> p.split(",").clone()[1]));
		} catch (IOException e) {
			e.printStackTrace();
			return "NO";
		}

		boolean routeFlag = false;
		routeFlag = findRoute(origin, destination, cityMap);
		if (!routeFlag) {
			routeFlag = findRoute(destination, origin, cityMap);
		}
		if (routeFlag) {
			returnValue = "Yes";
		}


		return returnValue;
	}

	private boolean findRoute(String origin, String destination, Map<String, String> cityMap) {

		boolean routeExits = false;

		Map<String, String> result = cityMap.entrySet().stream().filter(p -> p.getKey().equals(origin)) // filter by key
				.filter(map -> map.getValue().equals(destination)) // filter by value
				.collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));

		if (!result.isEmpty()) {
			routeExits = true;
		}

		else {
			List<Entry<String, String>> resultL = cityMap.entrySet().stream()
					.filter(p -> p.getKey().equals(destination)).filter(map -> map.getKey().equals(destination))// filter
					.collect(Collectors.toList());
			for (Entry<String, String> entry : resultL) {
				String value = cityMap.get(entry.getValue());
				if (value != null && value.equals(origin))
					routeExits = true;
			}

		}

		return routeExits;
	}

}
