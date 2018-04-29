package com.sgg.rest.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.sgg.rest.model.EquipmentQuery;
import com.sgg.rest.model.Chart;
import com.sgg.rest.model.Equipment;
import com.sgg.rest.model.Location;
import com.sgg.rest.repository.EquipmentRepository;
import com.sgg.rest.repository.LocationRepository;
import com.sgg.rest.service.EquipmentService;
@Service
public class EquipmentServiceImpl implements EquipmentService{
	@Resource  
    EquipmentRepository equipmentRepository;
	@Resource  
	LocationRepository locationRepository;
	@Override
	public boolean createEquipment(Integer locationId, Equipment equipment) {
		Location parentLocation =locationRepository.findOne(locationId);
		if(parentLocation !=null) {
			equipment.setLocation(parentLocation);
		}
		equipment.setCreater_time(new Date());
		equipment.setUpdater_time(new Date());
		if(equipmentRepository.save(equipment) != null) {
			return true;
		}
		return false;
	}
	@Override
	public boolean deleteEquipment(Integer equipmentId) {
		Equipment equipment =equipmentRepository.findOne(equipmentId);
		if(equipment!=null) {
			equipmentRepository.delete(equipment);
			return true;
		}
		return false;
	}
	@Override
	public boolean updateEquipment(Integer equipmentId, Equipment equipment) {
		Equipment f_equipment =equipmentRepository.findOne(equipmentId);
		if (f_equipment!=null) {
			f_equipment.setCode(equipment.getCode());
			f_equipment.setName(equipment.getName());
			f_equipment.setBatch_no(equipment.getBatch_no());
			f_equipment.setType(equipment.getType());
			f_equipment.setBuy_date(equipment.getBuy_date());
			f_equipment.setUpdater_time(new Date());
			equipmentRepository.save(f_equipment);
			return true;
		}
		return false;
	}
	@Override
	public Page<Equipment> findEquipmentCriteria(Integer page, Integer size, EquipmentQuery equipmentQuery) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");  
        Page<Equipment> equipmentPage = equipmentRepository.findAll(new Specification<Equipment>(){  
            @Override  
            public Predicate toPredicate(Root<Equipment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {  
            	    		Predicate p1 = criteriaBuilder.like(root.get("name").as(String.class), "%"+ equipmentQuery.getName()+ "%");
            	    		if(!equipmentQuery.getLocation_id().equals("")&&equipmentQuery.getLocation_id()!=null) {
            	    			Predicate p3 = criteriaBuilder.equal(root.get("location").get("id").as(String.class), equipmentQuery.getLocation_id());
            	    			query.where(criteriaBuilder.and(p1,p3)); 
            	    		}else {
            	    			query.where(criteriaBuilder.and(p1)); 
            	    		}
                return query.getRestriction();  
            }  
        },pageable);  
        return equipmentPage;  
	}
	@Override
	public Map<String,Object> getChartData(){
		Map<String,Object> map = new HashMap<String,Object>();
		List<Location> locationList= locationRepository.findAllLocations();
		List<Chart> chartList = new ArrayList<Chart>();
		for(Location location: locationList) {
			if(location.getEquipmentSet().size()>0) {

				Chart chart = new Chart();
				chart.setName(location.getName());
				chart.setValue(location.getEquipmentSet().size());
				chartList.add(chart);
			}
		}
		map.put("chartList", chartList);
		return map;
	}
	@Override
	public Iterable<Chart> getChartList() {
		List<Location> locationList= locationRepository.findAllLocations();
		List<Chart> chartList = new ArrayList<Chart>();
		for(Location location: locationList) {
			if(location.getEquipmentSet().size()>0) {
				Chart chart = new Chart();
				chart.setName(location.getName());
				chart.setValue(location.getEquipmentSet().size());
				chartList.add(chart);
			}
		}
		return chartList;
	}
}
