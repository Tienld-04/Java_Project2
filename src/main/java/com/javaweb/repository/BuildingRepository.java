package com.javaweb.repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.javaweb.repository.entity.BuildingEntity;
public interface BuildingRepository {
		List<BuildingEntity> findAll(String name, Long districtId);
		List<BuildingEntity> findAlls(Map<String, Object> params, List<String> typeCode);
		
}
