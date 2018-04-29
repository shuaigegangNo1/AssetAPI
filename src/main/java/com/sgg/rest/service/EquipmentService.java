package com.sgg.rest.service;

import java.util.Map;

import org.springframework.data.domain.Page;

import com.sgg.rest.model.EquipmentQuery;
import com.sgg.rest.model.Chart;
import com.sgg.rest.model.Equipment;

public interface EquipmentService {
	boolean createEquipment( Integer locationId,Equipment equipment);
	boolean deleteEquipment(Integer equipmentId);
	boolean updateEquipment(Integer equipmentId,Equipment equipment);
	Page<Equipment> findEquipmentCriteria(Integer page,Integer size,EquipmentQuery equipmentQuery);  
	Map<String,Object> getChartData();
	Iterable<Chart> getChartList();
}
