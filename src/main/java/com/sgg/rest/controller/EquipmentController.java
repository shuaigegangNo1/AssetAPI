package com.sgg.rest.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sgg.rest.model.EquipmentQuery;
import com.sgg.rest.repository.EquipmentRepository;
import com.sgg.rest.model.Chart;
import com.sgg.rest.model.Equipment;
import com.sgg.rest.service.EquipmentService;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {
	@Autowired  
	private EquipmentRepository equipmentRepository;
	@Autowired  
	private EquipmentService equipmentService;
	@RequestMapping(value="/create", method= {RequestMethod.POST,RequestMethod.GET})
	public ResponseEntity<Map<String,Object>> createNewEquipment(@RequestParam int locationId,@RequestBody Equipment equipment) {
		boolean res =equipmentService.createEquipment(locationId, equipment);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result",res);
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>>deleteEquipment(@RequestParam int equipmentId) {
		boolean res =equipmentService.deleteEquipment(equipmentId);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result",res);
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>>showEquipment(@RequestParam int equipmentId) {
		Equipment equipment = equipmentRepository.findOne(equipmentId);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result",equipment);
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	@RequestMapping(value="/update", method= RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> updateEquipment(@RequestParam int equipmentId,@RequestBody Equipment equipment) {
		boolean res =equipmentService.updateEquipment(equipmentId, equipment);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result",res);
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	@RequestMapping(value = "/queryList", method=RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> getEquipmentListByLocationId(@PageableDefault(value = 15, sort = { "id" }) @RequestParam int page,@RequestBody EquipmentQuery equipmentQuery) {
		 Integer size=10;
		 Page<Equipment>  equipmentList= equipmentService.findEquipmentCriteria(page, size, equipmentQuery);
		 Map<String,Object> map = new HashMap<String,Object>();
		 map.put("equipmentList", equipmentList);
	    return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/chart", method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> getChartData() {
		 Map<String,Object> map = equipmentService.getChartData();
	    return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/chartJson", method=RequestMethod.GET)
	public Iterable<Chart> getChartJsonData() {
		return equipmentService.getChartList();
	}
}
