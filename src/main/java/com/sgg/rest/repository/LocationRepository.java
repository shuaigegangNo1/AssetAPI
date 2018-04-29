package com.sgg.rest.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.sgg.rest.model.Location;

public interface LocationRepository extends JpaRepository<Location, Integer>,JpaSpecificationExecutor<Location>  {
//    @Transactional
//    List<Location> findByparent_id(Integer parent_id);
    
    @Query(value="select * from location where parent_id is not null", nativeQuery=true)
    @Modifying
    List<Location> findAllLocations();
    
	 @Transactional
	 List<Location> findLocationsByLocation(Location l);
	 
    @Query(value="select id,name from location where parent_id is not null", nativeQuery=true)
    List<Object> findAllCabinets();
}
