package com.javaweb.repository.custom.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.activation.FileDataSource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.NumberUtil;
import com.javaweb.utils.StringUtil;

@Repository
//@Primary
@PropertySource("classpath:application.properties")
public class JDBCBuildingRepositoryImpl implements BuildingRepositoryCustom {
//	
//	@Value("${spring.datasource.url}")
//	private String DB_URL;
//	
//	@Value("${spring.datasource.username}")
//	private String USER;
//	
//	@Value("${spring.datasource.password}")
//	private String PASS;

//	@Override
//	public List<BuildingEntity> findAll(String name, Long districtId) {
//		//String sql = new String"Select * from building  where name like '%"+name+"%'";
//		StringBuilder sql = new StringBuilder("Select * from building b where 1 = 1 ");
//		if(name != null && !name.isEmpty()) {
//			sql.append("and b.name like '%" + name + "%' ");
//		}
//		if(districtId != null ) {
//			sql.append("and b.districtid =" + districtId + " ");
//		}
//		List<BuildingEntity> result = new ArrayList<>();
//		try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//			Statement stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery(sql.toString());
//			){
//			while(rs.next()) {
//				BuildingEntity building = new BuildingEntity();
//				building.setName(rs.getString("name"));
//				building.setStreet(rs.getString("street"));
//				building.setWard(rs.getString("ward"));
//				building.setNumberOfBasement(rs.getInt("numberofbasement"));
//				result.add(building);
//			}
//		}catch (SQLException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			System.out.println("conn db failed");
//		}
//		return result;
//	}
	//----------------------------------------------------------------------------------------------
	public static void joinTable(BuildingSearchBuilder buildingSearchBuilder,  StringBuilder sql) {
		//Long staffId = Long.parseLong(params.get("staffId").toString());
		//String staffId = buildingSearchBuilder.getStaffId().toString();
		Long staffId = buildingSearchBuilder.getStaffId();
		if(staffId != null) {
			sql.append(" inner join assignmentbuilding on b.id = assignmentbuilding.buildingid ");
		}
		List<String> typeCode = buildingSearchBuilder.getTypeCode();
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
	public static void queryNomal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
//		for(Map.Entry<String, Object> it : params.entrySet()) {
//			if(!it.getKey().equals("staffId")  && !it.getKey().equals("typeCode") && !it.getKey().startsWith("area") && !it.getKey().startsWith("rentPrice")) {
//				String value = it.getValue().toString();
//					if(StringUtil.checkString(value)) {
//						if(NumberUtil.isNumber(value) == true) {
//							where.append(" and b." + it.getKey() + " = "+ value);
//						}
//						else {
//							where.append(" and b." + it.getKey() + " like '%" + value + "%' ");
//						}
//					}
//				}
//			}
		try {
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
			for(Field item : fields) {
				item.setAccessible(true);
				String fieldName = item.getName();
				if(!fieldName.equals("staffId")  && !fieldName.equals("typeCode") 
						&& !fieldName.startsWith("area") && !fieldName.startsWith("rentPrice")) {
					Object value = item.get(buildingSearchBuilder);
					if(value != null) {
						if(item.getType().getName().equals("java.lang.Long") || item.getType().getName().equals("java.lang.Integer")) {
							where.append(" and b." + fieldName + " = "+ value);
						}
						else if (item.getType().getName().equals("java.lang.String")){
							where.append(" and b." + fieldName + " like '%" + value + "%' ");
						}
					}
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		}
	public static void queySpecial(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
		Long staffId = buildingSearchBuilder.getStaffId();
		if(staffId != null) {
			where.append(" and assignmentbuilding.staffid = " + staffId);
		}
		
		Long rentAreaTo = buildingSearchBuilder.getAreaTo();
		Long rentAreaFrom = buildingSearchBuilder.getAreaFrom();
		if(rentAreaTo != null || rentAreaFrom != null){
			where.append(" and exists (select * from rentarea r where b.id = r.buildingid ");
			if(rentAreaFrom != null) {
				where.append(" and r.value >= " + rentAreaFrom);
				
			}
			if(rentAreaTo != null) {
				where.append(" and r.value <= " + rentAreaTo);
			}
			where.append(") ");
			
		}
		Long rentPriceTo = buildingSearchBuilder.getRentPriceTo();
		Long rentPriceFrom = buildingSearchBuilder.getRentPriceFrom();
		if(rentPriceTo != null || rentPriceFrom != null){
			if(rentPriceFrom != null) {
				where.append(" and b.rentprice >= " + rentPriceFrom);
				
			}
			if(rentPriceTo != null) {
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
		if(buildingSearchBuilder.getTypeCode() != null && buildingSearchBuilder.getTypeCode().size() != 0) {
			where.append(" and(");
			String sql = buildingSearchBuilder.getTypeCode().stream().map(it-> "renttype.code Like" + "'%"+ it + "%'").collect(Collectors.joining(" or "));
			where.append(sql);
			where.append(" ) ");
		}
	}
	
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<BuildingEntity> findAlls(BuildingSearchBuilder buildingSearchBuilder) {
		StringBuilder sql = new StringBuilder("Select b.* from building b ");
		joinTable(buildingSearchBuilder, sql);
		StringBuilder where = new StringBuilder(" where 1=1 ");
		queryNomal(buildingSearchBuilder, where);
		queySpecial(buildingSearchBuilder, where);
		where.append(" group by b.id; ");
		sql.append(where);
		
		Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
		return query.getResultList();
//		System.out.println(sql);
//		List<BuildingEntity> result = new ArrayList<>();
//		try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//				Statement stmt = conn.createStatement();
//				ResultSet rs = stmt.executeQuery(sql.toString());
//				){
//				while(rs.next()) {
//					BuildingEntity buildingEntity = new BuildingEntity();
//					buildingEntity.setId(rs.getLong("b.id"));
//					buildingEntity.setName(rs.getString("b.name"));
//					buildingEntity.setStreet(rs.getString("b.street"));
//					buildingEntity.setWard(rs.getString("b.ward"));
//					buildingEntity.setNumberOfBasement(rs.getInt("b.numberofbasement"));
//					//buildingEntity.setDistrictid(rs.getLong("b.districtid"));
//					buildingEntity.setFloorArea(rs.getLong("b.floorarea"));
//					buildingEntity.setRentPrice(rs.getLong("b.rentprice"));
//					buildingEntity.setServiceFee(rs.getString("b.servicefee"));
//					buildingEntity.setBrokerageFee(rs.getLong("b.brokeragefee"));
//					buildingEntity.setManagerName(rs.getString("b.managername"));
//					buildingEntity.setManagerPhoneNumber(rs.getString("b.managerphonenumber"));
//					result.add(buildingEntity);
//				}
//			}catch (SQLException e) {
//				// TODO: handle exception
//				e.printStackTrace();
//				System.out.println("conn db failed");
//			}
//			return result;
	}
	@Override
	public void saveBuilding(BuildingEntity buildingEntity) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void saveUpdateBuilding(BuildingEntity buildingEntity) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteBuildingRepo(Long id) {
		// TODO Auto-generated method stub
		
	}
}
