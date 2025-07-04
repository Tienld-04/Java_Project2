package com.javaweb.api;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.customeException.FieldRequiredException;
//import org.springframework.web.bind.annotation.RestController;
import com.javaweb.model.BuildingDTO;
import com.javaweb.model.BuildingRequestDTO;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.service.BuildingService;

//@RestController
//@Controller // chuyển từ 1 java class thông thường qua 1 restFull API web service
//Requet : từ client gửi tới server
//Responbody : từ server trả về client : chuyển data bean -> json
@RestController
@PropertySource("classpath:application.properties")
public class BuildingAPICustom {
	
	@Autowired
	private BuildingService buildingService;
	
	@Value("${dev.Tienld}")
	private String data;

	//@PathVariable là một annotation được sử dụng để trích xuất giá trị từ các tham số trong đường dẫn URL (URI path) của một yêu cầu HTTP. 
	//Nó thường được dùng trong các phương thức xử lý yêu cầu (controller methods) để ánh xạ các biến trong đường dẫn đến các tham số của phương thức
//	@DeleteMapping("api/building/{id}")
//	public void deleteBuilding(@PathVariable Integer id
////			, @PathVariable String name,
////			@RequestParam(value = "ward", required = false) String ward
//			) {
////		System.out.println("Đã xóa tòa nhà id = "+ id + " "+name);
//		System.out.println(data);
//	}
	
	
//	@GetMapping("/api/building/")
//	public List<BuildingDTO> getBuilding(@RequestParam(value="name", required = false) String name, 
//												@RequestParam(value="districtId", required = false) Long districtid){
//		List<BuildingDTO> result = buildingService.findAll(name, districtid);
//		return result;
//	}
	
	@GetMapping("/api/buildingCustom/")
	public List<BuildingDTO> getBuildings(@RequestParam Map<String, Object> params,
						@RequestParam(value="typeCode", required = false) List<String> typeCode){
		List<BuildingDTO> result = buildingService.findAlls(params, typeCode);
		return result;
	}
	
	
	@PostMapping(value = "/api/buildingCustom/")
	public void creatBuildings(@RequestBody BuildingRequestDTO buildingRequestDTO) {
			buildingService.creatBuilding(buildingRequestDTO);
	}
	
	@PutMapping(value = "/api/buildingCustom/{id}")
	public void updateBuilding(@PathVariable Long id, @RequestBody BuildingRequestDTO buildingRequestDTO) {
		buildingRequestDTO.setId(id);
		buildingService.updateBuilding(buildingRequestDTO);
	}
	
	@DeleteMapping(value = "/api/buildingCustom/{id}")
	public void deleteBuilding(@PathVariable Long id) {
		buildingService.deleteBuilding(id);
	}
}
