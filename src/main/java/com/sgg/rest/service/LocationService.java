package com.sgg.rest.service;

import org.springframework.data.domain.Page;

import com.sgg.rest.model.Location;
import com.sgg.rest.model.LocationQuery;

public interface LocationService {
	boolean createLocation(String parent_id,Location location);
	boolean deleteLocation(Integer locationId);
	boolean updateLocation(Integer locationId,Location location);
    Page<Location> findLocationNoCriteria(Integer page,Integer size);  
//    Iterable<Location> findListByParsentId(Integer parentId);
    Page<Location> findLocationCriteria(Integer page,Integer size,LocationQuery locationQuery);  
}
