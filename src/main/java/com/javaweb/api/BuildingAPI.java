package com.javaweb.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.customeException.FieldRequiredException;
//import org.springframework.web.bind.annotation.RestController;
import com.javaweb.model.BuildingDTO;
import com.javaweb.service.BuildingService;

//@RestController
//@Controller // chuyển từ 1 java class thông thường qua 1 restFull API web service
//Requet : từ client gửi tới server
//Responbody : từ server trả về client : chuyển data bean -> json
@RestController
@PropertySource("classpath:application.properties")
public class BuildingAPI {
	
	@Autowired
	private BuildingService buildingService;
	
	@Value("${dev.Tienld}")
	private String data;
//	@GetMapping("/test")
//    public String hello(){
//        return "hello";
//
//    }
//	@RequestMapping(value="/api/building/", method = RequestMethod.GET)
//	public void getBuilding(@RequestParam(value="name", required = false) String name,
//							@RequestParam(value="numberOfBasement", required = false) Integer numberOfBasement,
//							@RequestParam(value="ward", required = false) String ward
//			) {
//		
//		System.out.println(name +"   " +numberOfBasement+ " "+ ward);
//	}
//	@RequestMapping(value="/api/building/", method = RequestMethod.POST)
//	public void postBuilding(@RequestBody Map<String, String> params) {
//		System.out.println(params);
//	}
	
//	@RequestMapping(value="/api/building/", method = RequestMethod.POST)
//	public void postBuilding(@RequestBody BuildingDataTransferObject buildingDataTransferObject) {
//		System.out.println(buildingDataTransferObject);
//	}
	
	//@RequestMapping(value="/api/building/", method = RequestMethod.GET)
//	@GetMapping(value="/api/building/")
//	public BuildingDataTransferObject getBuilding(@RequestParam(value="name", required = false) String name,
//							@RequestParam(value="numberOfBasement", required = false) Integer numberOfBasement,
//							@RequestParam(value="ward", required = false) String ward
//			) {
//		BuildingDataTransferObject result = new BuildingDataTransferObject();
//		result.setName(name);
//		result.setNumberOfBasement(numberOfBasement);
//		result.setWard(ward);		
//		return result;
//	}
	public void valiDate(BuildingDTO buildingDataTransferObject){
		if(buildingDataTransferObject.getName() == null || buildingDataTransferObject.getName().equals("") || 
				buildingDataTransferObject.getNumberOfBasement() == null ) {
			throw new FieldRequiredException("name or num is null");
		}
	}
	@PostMapping(value="/api/building/")
	public Object getListBuilding(@RequestBody BuildingDTO buildingDataTransferObject) {
//		try {
////			valiDate(buildingDataTransferObject);
//			System.out.println(5/0);
			valiDate(buildingDataTransferObject);
//		} catch (Exception e) {
//			// TODO: handle exception
//			ErrorResponeDTO errorResponeDTO = new ErrorResponeDTO();
//			errorResponeDTO.setError(e.getMessage());
//			List<String> details = new ArrayList<>();
//			details.add("check name of num is error");
//			errorResponeDTO.setDetailError(details);
//			return errorResponeDTO;
//		}
//		List<BuildingDataTransferObject> result = new ArrayList();
//		BuildingDataTransferObject b = new BuildingDataTransferObject();
//		b.setName(name);
//		b.setNumberOfBasement(numberOfBasement);
//		b.setWard(ward);
		
//		BuildingDataTransferObject b2 = new BuildingDataTransferObject();
//		b2.setName("ABC building");
//		b2.setNumberOfBasement(4);
//		b2.setWard("Cầu Giấy");
//		b2.setStreet("44 Tran Thái Tông");
//		result.add(b);
//		result.add(b2);
		return buildingDataTransferObject;
	}
	
	
	//@RequestMapping(value="/api/buildings/", method = RequestMethod.POST)
//	@PostMapping(value="/api/buildings")
//	public BuildingDataTransferObject postUpdateBuilding(BuildingDataTransferObject buildingDataTransferObject) {
//		return buildingDataTransferObject;
//	}
////	
	//@PathVariable là một annotation được sử dụng để trích xuất giá trị từ các tham số trong đường dẫn URL (URI path) của một yêu cầu HTTP. 
	//Nó thường được dùng trong các phương thức xử lý yêu cầu (controller methods) để ánh xạ các biến trong đường dẫn đến các tham số của phương thức
	@DeleteMapping("api/building/{id}")
	public void deleteBuilding(@PathVariable Integer id
//			, @PathVariable String name,
//			@RequestParam(value = "ward", required = false) String ward
			) {
//		System.out.println("Đã xóa tòa nhà id = "+ id + " "+name);
		System.out.println(data);
	}
	
	
	@GetMapping("/api/building/")
	public List<BuildingDTO> getBuilding(@RequestParam(value="name", required = false) String name, 
												@RequestParam(value="districtId", required = false) Long districtid){
		List<BuildingDTO> result = buildingService.findAll(name, districtid);
		return result;
	}
	
	@GetMapping("/api/buildings/")
	public List<BuildingDTO> getBuildings(@RequestParam Map<String, Object> params,
														@RequestParam(value="typeCode", required = false) List<String> typeCode){
		List<BuildingDTO> result = buildingService.findAlls(params, typeCode);
		return result;
	}
	
}
