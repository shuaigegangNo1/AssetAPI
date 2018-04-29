package com.sgg.rest.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import com.sgg.rest.model.Cabinet;
import com.sgg.rest.repository.LocationRepositoryCustom;

public class LocationRepositoryImpl implements LocationRepositoryCustom {
	@Autowired
    @PersistenceContext
    private EntityManager entityManager;
	
    private EntityManagerFactory emf;

    @PersistenceUnit
    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }
	@Override
	public List<Cabinet> getCabinets() {
	      EntityManager em = emf.createEntityManager();
	        Query query = em.createQuery("select new com.sgg.rest.model.Cabinet(id,name) from Location where parent_id is not null");
	        List result = query.getResultList();
        return result;
	}
	@Override
	public List<Cabinet> getCabinetsByParentId(Integer parentId) {
	      EntityManager em = emf.createEntityManager();
	        Query query = em.createQuery("select new com.sgg.rest.model.Cabinet(id,name) from Location where parent_id = ?");
	        query.setParameter(1, parentId);
	        List result = query.getResultList();
	        return result;
	}
	@Override
	public List<Cabinet> getLocations() {
	      EntityManager em = emf.createEntityManager();
	        Query query = em.createQuery("select new com.sgg.rest.model.Cabinet(id,name) from Location where parent_id is null");
	        List result = query.getResultList();
        return result;
	}
}
