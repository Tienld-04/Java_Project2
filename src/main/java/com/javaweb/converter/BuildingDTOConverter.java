package com.javaweb.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentAreaEntity;

@Component
public class BuildingDTOConverter {
	@Autowired
	private DistrictRepository districtRepository;
	
	@Autowired
	private RentAreaRepository rentAreaRepository;
	
	public BuildingDTO toBuildingDTO(BuildingEntity item) {
		BuildingDTO building = new BuildingDTO();
		DistrictEntity districtEntity = districtRepository.findNameById(item.getDistrictid());
		
		building.setName(item.getName());
		building.setNumberOfBasement(item.getNumberOfBasement());
		building.setAddress(item.getStreet()+","+item.getWard()+","+districtEntity.getName() );
		List<RentAreaEntity> rentAreas = rentAreaRepository.getValueByBuildingId(item.getId());
		String listRentArea = rentAreas.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
		building.setRentArea(listRentArea);
		building.setManagerName(item.getManagerName());
		building.setManagerPhoneNumber(item.getManagerPhoneNumber());
		building.setFloorArea(item.getFloorArea());
		building.setEmptyArea(item.getEmptyArea());
		building.setRentPrice(item.getRentPrice());
		building.setServiceFee(item.getServiceFee());
		building.setBrokerageFee(item.getBrokerageFee());
		return building;
	}
}
