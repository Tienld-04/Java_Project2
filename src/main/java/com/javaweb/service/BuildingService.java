package com.javaweb.service;
import java.util.List;
import java.util.Map;

import com.javaweb.model.BuildingDTO;
public interface BuildingService {
	List<BuildingDTO> findAll(String name, Long districtId);
	//List<BuildingDataTransferObject> findAlls(Map<String, Object> params, List<String> typeCode);
	List<BuildingDTO> findAlls(Map<String, Object> params, List<String> typeCode);
}
