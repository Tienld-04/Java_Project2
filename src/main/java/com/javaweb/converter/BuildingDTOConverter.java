package com.javaweb.converter;
//Chuyen doi du lieu
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.model.BuildingDTO;
import com.javaweb.model.BuildingRequestDTO;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentAreaEntity;

@Component
public class BuildingDTOConverter {
//	@Autowired
//	private DistrictRepository districtRepository;
//	
//	@Autowired
//	private RentAreaRepository rentAreaRepository;
//	
	@Autowired
	private ModelMapper modelMapper;
	
	public BuildingDTO toBuildingDTO(BuildingEntity item) {
		BuildingDTO building = modelMapper.map(item, BuildingDTO.class);
		//DistrictEntity districtEntity = districtRepository.findNameById(item.getDistrictid());
		//DistrictEntity districtEntity = districtRepository.findNameById(item.getDistrictid());
//		building.setAddress(item.getStreet()+","+item.getWard()+","+districtEntity.getName() );
		building.setAddress(item.getStreet()+","+item.getWard()+","+item.getDistrict().getName() );
		//List<RentAreaEntity> rentAreas = rentAreaRepository.getValueByBuildingId(item.getId());
		String listRentArea = item.getAreaEntities().stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
		building.setRentArea(listRentArea);
		return building;
	}
	
	public BuildingEntity toBuildingEntity(BuildingRequestDTO buildingRequestDTO) {
		BuildingEntity buildingEntity = new BuildingEntity();
		buildingEntity.setId(buildingRequestDTO.getId());
		buildingEntity.setName(buildingRequestDTO.getName());
		buildingEntity.setWard(buildingRequestDTO.getWard());
		buildingEntity.setStreet(buildingRequestDTO.getStreet());
		DistrictEntity districtEntity = new DistrictEntity();
		districtEntity.setId(buildingRequestDTO.getDistrictid());
		buildingEntity.setDistrict(districtEntity);
		return buildingEntity;
	}
	
}
