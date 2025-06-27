package com.javaweb.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.model.BuildingDTO;
import com.javaweb.service.BuildingService;
import java.util.List;
@RestController
public class BuildingAPI {
	@Autowired
	private BuildingService buildingService;
	
	@GetMapping(value = "/api/building/")
	public List<BuildingDTO> getAllBuilding(){
		List<BuildingDTO> result = buildingService.getAllBuilding();
		return result;
	}
	@DeleteMapping(value = "/api/building/{id}")
	public void deleteBuilding(@PathVariable Long id) {
		buildingService.deleteBuildingById(id);
	}
	
	
}
