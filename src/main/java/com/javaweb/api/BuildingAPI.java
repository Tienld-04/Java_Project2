package com.javaweb.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.model.BuildingDTO;
import com.javaweb.model.BuildingRequestDTO;
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
	
	@GetMapping("/api/building/{id}")
	public BuildingDTO getBuilding(@PathVariable Long id) {
		BuildingDTO buildingDTO = buildingService.getBuildingById(id);
		return buildingDTO;
	}
	
	@DeleteMapping(value = "/api/building/{id}")
	public void deleteBuildingId(@PathVariable Long id) {
		buildingService.deleteBuildingById(id);
	}
	
	@DeleteMapping("/api/buildings/{ids}")
	public void deleteBuildingIds(@PathVariable Long[] ids) {
		buildingService.deleteBuildingByIds(ids);
	}
	
	@GetMapping("/api/buildingSearch/{name}")
	public List<BuildingDTO> findByNameBuilding(@PathVariable String name){
		List<BuildingDTO> result = buildingService.findByName(name);
		return result;
	}
	
	@GetMapping("/api/buildingsSearch/{name}/{street}")
	public List<BuildingDTO> findByNameBuilding(@PathVariable String name, @PathVariable String street){
		List<BuildingDTO> result = buildingService.findByNameAndStreet(name, street);
		return result;
	}
	
	@PutMapping("/api/building/")
	public void updateBuilding(@RequestBody BuildingRequestDTO buildingRequestDTO) {
		buildingService.saveBuilding(buildingRequestDTO);
	}
	
	@PostMapping("/api/building/")
	public void insertBuilding(@RequestBody BuildingRequestDTO buildingRequestDTO) {
		buildingService.insertBuilding(buildingRequestDTO);
	}
}
