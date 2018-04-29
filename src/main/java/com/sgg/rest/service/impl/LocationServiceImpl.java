package com.sgg.rest.service.impl;

import java.util.Date;

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

import com.sgg.rest.model.Location;
import com.sgg.rest.model.LocationQuery;
import com.sgg.rest.repository.LocationRepository;
import com.sgg.rest.service.LocationService;
@Service
public class LocationServiceImpl implements LocationService{
	@Resource  
    LocationRepository locationRepository;

	@Override
	public boolean createLocation( String parent_id,Location location) {
		if(!parent_id.equals("")&&parent_id!=null) {
			Location parentLocation =locationRepository.findOne(Integer.parseInt(parent_id));
			if(parentLocation !=null) {
				location.setLocation(parentLocation);
			}
		}
		location.setCreater_time(new Date());
		location.setUpdater_time(new Date());
		if(locationRepository.save(location) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteLocation(Integer locationId) {
		Location location =locationRepository.findOne(locationId);
		if(location!=null&&location.getLocationSet().size()==0) {
			locationRepository.delete(location);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateLocation(Integer locationId, Location location) {
		Location f_location =locationRepository.findOne(locationId);
		if (f_location!=null) {
			f_location.setCode(location.getCode());
			f_location.setName(location.getName());
			f_location.setUpdater_time(new Date());
			locationRepository.save(f_location);
			return true;
		}
		return false;
	}

	@Override
	public Page<Location> findLocationNoCriteria(Integer page, Integer size) {
	  Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");  
        return locationRepository.findAll(pageable);  
	}

	@Override
	public Page<Location> findLocationCriteria(Integer page, Integer size, LocationQuery locationQuery) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");  
        Page<Location> locationPage = locationRepository.findAll(new Specification<Location>(){  
            @Override  
            public Predicate toPredicate(Root<Location> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {  
//                
//                Predicate p1 = criteriaBuilder.equal(root.get("location").get("name").as(String.class), locationQuery.getName());
//            	    Predicate p1 = criteriaBuilder.equal(root.get("name").as(String.class), locationQuery.getName()); 
            	    Predicate p1 = criteriaBuilder.like(root.get("name").as(String.class), "%"+ locationQuery.getName()+ "%"); 
//                Predicate p2 = criteriaBuilder.equal(root.get("location").get("id").as(String.class), null);
                if(locationQuery.getParent_id().equals("")||locationQuery.getParent_id()==null) {
	                	Predicate p3 = criteriaBuilder.isNull(root.get("location").get("id").as(String.class));
	                	query.where(criteriaBuilder.and(p1,p3));  
                }else {
                		Predicate p3 = criteriaBuilder.equal(root.get("location").get("id").as(String.class), locationQuery.getParent_id());
                		query.where(criteriaBuilder.and(p1,p3)); 
                }
                
//                Predicate p2 = criteriaBuilder.like(root.get("email").as(String.class), "%"+ locationQuery.getParent_id()+ "%");  
                return query.getRestriction();  
            }  
        },pageable);  
        return locationPage;  
	}

}
