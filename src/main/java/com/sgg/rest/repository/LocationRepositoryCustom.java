package com.sgg.rest.repository;

import java.util.List;

import com.sgg.rest.model.Cabinet;

public interface LocationRepositoryCustom {
	public List<Cabinet> getLocations();
	public List<Cabinet> getCabinets();
	public List<Cabinet> getCabinetsByParentId(Integer parentId);
}
