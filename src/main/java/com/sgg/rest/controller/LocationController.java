package com.sgg.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sgg.rest.model.Cabinet;
import com.sgg.rest.model.Location;
import com.sgg.rest.model.LocationQuery;
import com.sgg.rest.repository.LocationRepository;
import com.sgg.rest.repository.LocationRepositoryCustom;
import com.sgg.rest.service.LocationService;

@RestController
@RequestMapping("/location")
public class LocationController {
	@Autowired  
	private LocationService locationService;
	@Autowired  
	private LocationRepository locationRepository;
	@Autowired  
	private LocationRepositoryCustom locationRepositoryCustom;
	@RequestMapping(value="/create", method= {RequestMethod.POST,RequestMethod.GET})
	public ResponseEntity<Map<String,Object>> createNewLocation(@RequestParam String parentId,@RequestBody Location location) {
		boolean res =locationService.createLocation(parentId,location);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result",res);
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	@RequestMapping(value="/delete", method= RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> deleteLocation(@RequestParam int locationId) {
		boolean res =locationService.deleteLocation(locationId);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result",res);
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	@RequestMapping(value="/update", method= RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> updateLocation(@RequestParam int locationId,@RequestBody Location location) {
		boolean res =locationService.updateLocation(locationId, location);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result",res);
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	@RequestMapping(value = "/list", method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> getEntryByPageable(@PageableDefault(value = 15, sort = { "id" }) @RequestParam int page) {
		 Integer size=10;
		 Page<Location>  locationList= locationService.findLocationNoCriteria(page, size);
		 Map<String,Object> map = new HashMap<String,Object>();
		 map.put("locationList", locationList);
	    return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	@RequestMapping(value = "/AllList", method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> getAllLocaionlist() {
		 Map<String,Object> map = new HashMap<String,Object>();
		 ArrayList<Location> locationList = (ArrayList<Location>) locationRepository.findAllLocations();
		 map.put("locationList", locationList);
	    return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/queryList", method=RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> getLocationListByParentId(@PageableDefault(value = 15, sort = { "id" }) @RequestParam int page,@RequestBody LocationQuery locationQuery) {
		 Integer size=10;
		 Page<Location>  locationList= locationService.findLocationCriteria(page, size, locationQuery);
		 Map<String,Object> map = new HashMap<String,Object>();
		 map.put("locationList", locationList);
	    return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	@GetMapping(path="/allLocations")
	public Iterable<Location> getAllLocations(@RequestParam int locationId) {
		Location l = locationRepository.findOne(locationId);
		return locationRepository.findLocationsByLocation(l);
	}
	@GetMapping(path="/CabinetList")
	public Iterable<Cabinet> getCabinetList() {
		return locationRepositoryCustom.getCabinets();
	}
	@GetMapping(path="/CabinetListByPatentId")
	public Iterable<Cabinet> getCabinetListByParentId(@RequestParam int parentId) {
		return locationRepositoryCustom.getCabinetsByParentId(parentId);
	}
	@GetMapping(path="/ObjectList")
	public Iterable<Object> getObjectList() {
		return locationRepository.findAllCabinets();
	}
	@GetMapping(path="/LocationList")
	public Iterable<Cabinet> getLocationList() {
		return locationRepositoryCustom.getLocations();
	}
}
