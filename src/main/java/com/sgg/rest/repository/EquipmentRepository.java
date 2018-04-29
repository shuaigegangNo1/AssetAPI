package com.sgg.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sgg.rest.model.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer>,JpaSpecificationExecutor<Equipment> {

}
