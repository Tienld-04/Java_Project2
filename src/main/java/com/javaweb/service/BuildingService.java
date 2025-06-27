package com.javaweb.service;
import java.util.List;
import java.util.Map;

import com.javaweb.model.BuildingDTO;
import com.javaweb.model.BuildingRequestDTO;
public interface BuildingService {
	//List<BuildingDTO> findAll(String name, Long districtId);
	//List<BuildingDataTransferObject> findAlls(Map<String, Object> params, List<String> typeCode);
	List<BuildingDTO> findAlls(Map<String, Object> params, List<String> typeCode);
	void creatBuilding(BuildingRequestDTO buildingRequestDTO);
	void updateBuilding(BuildingRequestDTO buildingRequestDTO);
	void deleteBuilding(Long id);
	
	//using string data jpa 
	void deleteBuildingById(Long id);
	void deleteBuildingByIds(Long[] ids);
	List<BuildingDTO> getAllBuilding();
	BuildingDTO getBuildingById(Long id);

}
