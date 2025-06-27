package com.javaweb.repository.custom;
import java.util.List;
import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.entity.BuildingEntity;
public interface BuildingRepositoryCustom{
		//List<BuildingEntity> findAll(String name, Long districtId);
		//List<BuildingEntity> findAlls(Map<String, Object> params, List<String> typeCode);
		List<BuildingEntity> findAlls(BuildingSearchBuilder buildingSearchBuilder);
		void saveBuilding(BuildingEntity buildingEntity);
		void saveUpdateBuilding(BuildingEntity buildingEntity);
		void deleteBuildingRepo(Long id);
		
		
}
