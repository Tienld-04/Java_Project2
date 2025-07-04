package com.javaweb.repository.custom.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.model.BuildingRequestDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;

@Repository
@Primary
public class BuildingRepositoryImpl implements BuildingRepositoryCustom{
	
	// using Jpa 
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<BuildingEntity> findAlls(BuildingSearchBuilder buildingSearchBuilder) {
//		String sql = "from BuildingEntity";
//		Query query = entityManager.createQuery(sql, BuildingEntity.class);
		
		//sql native
		StringBuilder sql = new StringBuilder("SELECT * FROM Building WHERE 1=1");

	    if (buildingSearchBuilder.getName() != null && !buildingSearchBuilder.getName().isEmpty()) {
	        sql.append(" AND name LIKE :name");
	    }
	    if (buildingSearchBuilder.getDistrictId() != null && !buildingSearchBuilder.getDistrictId().equals("")) {
	        sql.append(" AND districtid = :districtId");
	    }
	    if (buildingSearchBuilder.getTypeCode() != null && !buildingSearchBuilder.getTypeCode().isEmpty()) {
	        sql.append(" AND typecode IN (:typecode)");
	    }

	    Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);

	    if (buildingSearchBuilder.getName() != null && !buildingSearchBuilder.getName().isEmpty()) {
	        query.setParameter("name", "%" + buildingSearchBuilder.getName() + "%");
	    }
	    if (buildingSearchBuilder.getDistrictId() != null && !buildingSearchBuilder.getDistrictId().equals("")) {
	        query.setParameter("districtId", buildingSearchBuilder.getDistrictId());
	    }
	    if (buildingSearchBuilder.getTypeCode() != null && !buildingSearchBuilder.getTypeCode().isEmpty()) {
	        query.setParameter("typecode", buildingSearchBuilder.getTypeCode());
	    }

	    return query.getResultList();
//		String sql = "select * from Building ";
//		Query query = entityManager.createNativeQuery(sql, BuildingEntity.class);
//		return query.getResultList();
//		
	}
	
	@Override
	public void saveBuilding(BuildingEntity buildingEntity) {
			entityManager.persist(buildingEntity);
		}
	
	@Override
	public void saveUpdateBuilding(BuildingEntity buildingEntity) {
		entityManager.merge(buildingEntity);
	}
	
	@Override
	public void deleteBuildingRepo(Long id) {
			BuildingEntity buildingEntity = entityManager.find(BuildingEntity.class, id);
			entityManager.remove(buildingEntity);
	}



}
