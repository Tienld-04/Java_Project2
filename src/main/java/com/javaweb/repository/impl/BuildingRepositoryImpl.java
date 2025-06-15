package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.NumberUtil;
import com.javaweb.utils.StringUtil;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
	
	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	static final String USER = "root";
	static final String PASS = "123456";
	@Override
	public List<BuildingEntity> findAll(String name, Long districtId) {
		//String sql = new String"Select * from building  where name like '%"+name+"%'";
		StringBuilder sql = new StringBuilder("Select * from building b where 1 = 1 ");
		if(name != null && !name.isEmpty()) {
			sql.append("and b.name like '%" + name + "%' ");
		}
		if(districtId != null ) {
			sql.append("and b.districtid =" + districtId + " ");
		}
		List<BuildingEntity> result = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql.toString());
			){
			while(rs.next()) {
				BuildingEntity building = new BuildingEntity();
				building.setName(rs.getString("name"));
				building.setStreet(rs.getString("street"));
				building.setWard(rs.getString("ward"));
				building.setNumberOfBasement(rs.getInt("numberofbasement"));
				result.add(building);
			}
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("conn db failed");
		}
		return result;
	}
	//----------------------------------------------------------------------------------------------
	public static void joinTable(Map<String, Object> params, List<String> typeCode, StringBuilder sql) {
		//Long staffId = Long.parseLong(params.get("staffId").toString());
		String staffId = (String)params.get("staffId");
		if(StringUtil.checkString(staffId)) {
			sql.append(" inner join assignmentbuilding on b.id = assignmentbuilding.buildingid ");
		}
		if(typeCode != null && typeCode.size() != 0) {
			sql.append(" inner join buildingrenttype on b.id = buildingrenttype.buildingid ");
			sql.append(" inner join renttype on renttype.id = buildingrenttype.renttypeid ");
		}
		
//		String rentAreaTo = (String)params.get("areaTo");
//		String rentAreaFrom = (String)params.get("areaFrom");
//		
//		if(StringUtil.checkString(rentAreaTo) == true || StringUtil.checkString(rentAreaFrom) == true){
//			sql.append(" inner join rentarea on rentarea.buildingid = b.id ");
//		}
	}
	public static void queryNomal(Map<String, Object> params, StringBuilder where) {
		for(Map.Entry<String, Object> it : params.entrySet()) {
			if(!it.getKey().equals("staffId")  && !it.getKey().equals("typeCode") && !it.getKey().startsWith("area") && !it.getKey().startsWith("rentPrice")) {
				String value = it.getValue().toString();
					if(StringUtil.checkString(value)) {
						if(NumberUtil.isNumber(value) == true) {
							where.append(" and b." + it.getKey() + " = "+ value);
						}
						else {
							where.append(" and b." + it.getKey() + " like '%" + value + "%' ");
						}
					}
				}
			}
		}
	public static void queySpecial(Map<String, Object> params, List<String> typeCode, StringBuilder where) {
		String staffId = (String)params.get("staffId");
		if(StringUtil.checkString(staffId)) {
			where.append(" and assignmentbuilding.staffid = " + staffId);
		}
		String rentAreaTo = (String)params.get("areaTo");
		String rentAreaFrom = (String)params.get("areaFrom");
		if(StringUtil.checkString(rentAreaTo) == true || StringUtil.checkString(rentAreaFrom) == true){
			where.append(" and exists (select * from rentarea r where b.id = r.buildingid ");
			if(StringUtil.checkString(rentAreaFrom) == true) {
				where.append(" and r.value >= " + rentAreaFrom);
				
			}
			if(StringUtil.checkString(rentAreaTo)) {
				where.append(" and r.value <= " + rentAreaTo);
			}
			where.append(") ");
			
		}
		String rentPriceTo = (String)params.get("rentPriceTo");
		String rentPriceFrom = (String)params.get("rentPriceFrom");
		if(StringUtil.checkString(rentPriceTo) == true || StringUtil.checkString(rentPriceFrom) == true){
			if(StringUtil.checkString(rentAreaFrom) == true) {
				where.append(" and b.rentprice >= " + rentPriceFrom);
				
			}
			if(StringUtil.checkString(rentPriceTo)) {
				where.append(" and b.rentprice <= " + rentPriceTo);
			}
		}
		//java7
//		if(typeCode != null && typeCode.size() != 0) {
//			//where.append(" and renttype.code in('" + String.join(",", typeCode) + "')" );
//			List<String> code = new ArrayList<>();
//			for(String item : typeCode) {
//				code.add("'"+ item + "'");
//			}
//			where.append(" and renttype.code in(" + String.join(",", code) + ") ");
//			
//		}
		//java8
		if(typeCode != null && typeCode.size() != 0) {
			where.append(" and(");
			String sql = typeCode.stream().map(it-> "renttype.code Like" + "'%"+ it + "%'").collect(Collectors.joining(" or "));
			where.append(sql);
			where.append(" ) ");
		}
	}
	
	@Override
	public List<BuildingEntity> findAlls(Map<String, Object> params, List<String> typeCode) {
		StringBuilder sql = new StringBuilder("Select b.id, b.name, b.districtid, b.street, b.ward,b.numberofbasement, b.floorarea,b.rentprice, "
				+ " b.managername,b.managerphonenumber, b.servicefee, b.brokeragefee from building b");
		joinTable(params, typeCode, sql);
		StringBuilder where = new StringBuilder(" where 1=1 ");
		queryNomal(params, where);
		queySpecial(params, typeCode, where);
		where.append(" group by b.id; ");
		sql.append(where);
		System.out.println(sql);
		List<BuildingEntity> result = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql.toString());
				){
				while(rs.next()) {
					BuildingEntity buildingEntity = new BuildingEntity();
					buildingEntity.setId(rs.getLong("b.id"));
					buildingEntity.setName(rs.getString("b.name"));
					buildingEntity.setStreet(rs.getString("b.street"));
					buildingEntity.setWard(rs.getString("b.ward"));
					buildingEntity.setNumberOfBasement(rs.getInt("b.numberofbasement"));
					buildingEntity.setDistrictid(rs.getLong("b.districtid"));
					buildingEntity.setFloorArea(rs.getLong("b.floorarea"));
					buildingEntity.setRentPrice(rs.getLong("b.rentprice"));
					buildingEntity.setServiceFee(rs.getString("b.servicefee"));
					buildingEntity.setBrokerageFee(rs.getLong("b.brokeragefee"));
					buildingEntity.setManagerName(rs.getString("b.managername"));
					buildingEntity.setManagerPhoneNumber(rs.getString("b.managerphonenumber"));
					result.add(buildingEntity);
				}
			}catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
				System.out.println("conn db failed");
			}
			return result;
	}
}
