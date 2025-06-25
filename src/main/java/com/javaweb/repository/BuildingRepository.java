package com.javaweb.repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.model.BuildingRequestDTO;
import com.javaweb.repository.entity.BuildingEntity;
public interface BuildingRepository {
		List<BuildingEntity> findAll(String name, Long districtId);
		//List<BuildingEntity> findAlls(Map<String, Object> params, List<String> typeCode);
		List<BuildingEntity> findAlls(BuildingSearchBuilder buildingSearchBuilder);
		void saveBuilding(BuildingEntity buildingEntity);
		void saveUpdateBuilding(BuildingEntity buildingEntity);
		void deleteBuildingRepo(Long id);
		
		
}
