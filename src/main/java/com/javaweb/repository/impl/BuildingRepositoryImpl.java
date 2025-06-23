package com.javaweb.repository.impl;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

@Repository
@Primary
public class BuildingRepositoryImpl implements BuildingRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public List<BuildingEntity> findAll(String name, Long districtId) {
		// TODO JPQL
		String sql = "select * from Building b ";
		Query query = entityManager.createQuery(sql, BuildingEntity.class);
		return query.getResultList();
	}

	@Override
	public List<BuildingEntity> findAlls(BuildingSearchBuilder buildingSearchBuilder) {
//		String sql = "from BuildingEntity";
//		Query query = entityManager.createQuery(sql, BuildingEntity.class);
		
		//sql native
		String sql = "select * from Building b where b.id = 1 ";
		Query query = entityManager.createNativeQuery(sql, BuildingEntity.class);
		return query.getResultList();
		
	}

}
