package com.example.code.roadsService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.code.roadsService.service.ICityService;

@RestController
public class RoadServiceController {
	
	@Autowired
	private ICityService cityService;
	
	

	@GetMapping(value = "/connected")
    public String findCityRoutes( @RequestParam(value="origin", required=true) String origin,
            @RequestParam(value="destination", required=true) String destination) {

        String returnValue = cityService.findCityRoutes(origin, destination);

        return returnValue;
    }
	
	
	

}
