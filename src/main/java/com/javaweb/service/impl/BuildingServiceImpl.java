package com.javaweb.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingDTOConverter;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.model.BuildingDTO;
import com.javaweb.model.BuildingRequestDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService {
	
	@Autowired
	private BuildingRepositoryCustom buildingRepositoryCustom;
	
	@Autowired
	private BuildingRepository buildingRepository;
	
	
	@Autowired
	private BuildingDTOConverter buildingDTOConverter;
	
	@Autowired
	private BuildingSearchBuilderConverter buildingSearchBuilderConverter;
	
//	@Override
//	public List<BuildingDTO> findAll(String name, Long districtId) {
//		
//		List<BuildingEntity> buildingEntities = buildingRepositoryCustom.findAll(name, districtId);
//		List<BuildingDTO> result = new ArrayList<BuildingDTO>();
//		for(BuildingEntity item : buildingEntities) {
//			BuildingDTO building = new BuildingDTO();
//			building.setName(item.getName());
//			building.setNumberOfBasement(item.getNumberOfBasement());
//			building.setAddress(item.getStreet()+" "+item.getWard());
//			result.add(building);
//		}
//		
//		return result;		
//	}
	@Override  //---------------------------using custom Jpa----------------------------------//
	public List<BuildingDTO> findAlls(Map<String,Object> params, List<String> typeCode) {
		BuildingSearchBuilder buildingSearchBuilder = buildingSearchBuilderConverter.toBuildingSearchBuilder(params, typeCode);
		List<BuildingEntity> buildingEntities = buildingRepositoryCustom.findAlls(buildingSearchBuilder);
		//List<BuildingEntity> buildingEntities = buildingRepository.findAll();
		List<BuildingDTO> result = new ArrayList<BuildingDTO>();
		for(BuildingEntity item : buildingEntities) {
			BuildingDTO building = buildingDTOConverter.toBuildingDTO(item);
			result.add(building);
		}
		
		return result;		
	}
	
	@Override
	@Transactional
	public void creatBuilding(BuildingRequestDTO buildingRequestDTO) {
		BuildingEntity buildingEntity = new BuildingEntity();
		buildingEntity.setName(buildingRequestDTO.getName());
		buildingEntity.setWard(buildingRequestDTO.getWard());
		buildingEntity.setStreet(buildingRequestDTO.getStreet());
		DistrictEntity districtEntity = new DistrictEntity();
		districtEntity.setId(buildingRequestDTO.getDistrictid());
		buildingEntity.setDistrict(districtEntity);
		buildingRepositoryCustom.saveBuilding(buildingEntity);
	}
	@Override
	@Transactional
	public void updateBuilding(BuildingRequestDTO buildingRequestDTO) {
		BuildingEntity buildingEntity = new BuildingEntity();
		buildingEntity.setId(buildingRequestDTO.getId());
		buildingEntity.setName(buildingRequestDTO.getName());
		buildingEntity.setWard(buildingRequestDTO.getWard());
		buildingEntity.setStreet(buildingRequestDTO.getStreet());
		DistrictEntity districtEntity = new DistrictEntity();
		districtEntity.setId(buildingRequestDTO.getDistrictid());
		buildingEntity.setDistrict(districtEntity);
		buildingRepositoryCustom.saveUpdateBuilding(buildingEntity);
	}
	
	@Override
	@Transactional
	public void deleteBuilding(Long id) {
		buildingRepositoryCustom.deleteBuildingRepo(id);
	}

	// using spring data Jpa
	@Override
	public void deleteBuildingById(Long id) {
			buildingRepository.deleteById(id);	
	}

	@Override
	public List<BuildingDTO> getAllBuilding() {
		List<BuildingEntity> buildingEntities = buildingRepository.findAll();
		List<BuildingDTO> result = new ArrayList<BuildingDTO>();
		for(BuildingEntity item : buildingEntities) {
			BuildingDTO building = buildingDTOConverter.toBuildingDTO(item);
			result.add(building);
		}
		
		return result;		
	}
	@Override
	public BuildingDTO getBuildingById(Long id) {
		BuildingEntity buildingEntity = buildingRepository.getOne(id);
		BuildingDTO result = buildingDTOConverter.toBuildingDTO(buildingEntity);
		return result;
		
	}

	@Override
	@Transactional
	public void deleteBuildingByIds(Long[] ids) {
		buildingRepository.deleteByIdIn(ids);
		
	}
	
	@Override
	public List<BuildingDTO> findByName(String s){
		List<BuildingEntity> buildingEntities = buildingRepository.findByNameContaining(s);
		List<BuildingDTO> result = new ArrayList<BuildingDTO>();
		for(BuildingEntity item : buildingEntities) {
			BuildingDTO building = buildingDTOConverter.toBuildingDTO(item);
			result.add(building);
		}
		
		return result;		
	}
	
	@Override
	public List<BuildingDTO> findByNameAndStreet(String name, String street){
		List<BuildingEntity> buildingEntities = buildingRepository.findByNameContainingAndStreet(name, street);
		List<BuildingDTO> result = new ArrayList<BuildingDTO>();
		for(BuildingEntity item : buildingEntities) {
			BuildingDTO building = buildingDTOConverter.toBuildingDTO(item);
			result.add(building);
		}
		
		return result;		
	}
	@Override
	public void saveBuilding(BuildingRequestDTO buildingRequestDTO) {
		BuildingEntity buildingEntity = buildingRepository.findById(buildingRequestDTO.getId()).get();
		buildingEntity.setName(buildingRequestDTO.getName());
		buildingEntity.setWard(buildingRequestDTO.getWard());
		buildingEntity.setStreet(buildingRequestDTO.getStreet());
		DistrictEntity districtEntity = new DistrictEntity();
		districtEntity.setId(buildingRequestDTO.getDistrictid());
		buildingEntity.setDistrict(districtEntity);
		buildingRepository.save(buildingEntity);
	}
	
	@Override
	public void insertBuilding(BuildingRequestDTO buildingRequestDTO) {
		BuildingEntity buildingEntity = new BuildingEntity();
		buildingEntity = buildingDTOConverter.toBuildingEntity(buildingRequestDTO);
		buildingRepository.save(buildingEntity);
	}
}
