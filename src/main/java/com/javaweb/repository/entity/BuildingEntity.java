package com.javaweb.repository.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "building")
public class BuildingEntity {
	
	@Id // đánh dấu khóa chính
	@GeneratedValue(strategy = GenerationType.IDENTITY) // tự  động tăng dần
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "numberofbasement")
	private Integer numberOfBasement;
	
	@Column(name = "ward")
	private String ward;
	
	@Column(name = "street")
	private String street;
	
//	@Column(name = "districtid")
//	private Long districtid;
	
	@Column(name = "managerName")
	private String managerName;
	
	@Column(name = "managerPhoneNumber")
	private String managerPhoneNumber;
	
	@Column(name = "floorArea")
	private Long floorArea;
	
	@Column(name = "emptyArea")
	private String emptyArea;
	
	@Column(name = "rentPrice")
	private Long rentPrice;
	
	@Column(name = "serviceFee")
	private String serviceFee;
	
	@Column(name = "brokerageFee")
	private Long brokerageFee;
	
	
	@ManyToOne
	@JoinColumn(name = "districtid") // tạo 1 cột có tên districtID
	private DistrictEntity district;
	
	@OneToMany(mappedBy = "building", fetch = FetchType.LAZY)
	private List<RentAreaEntity> areaEntities = new ArrayList<>();
	
	
	public DistrictEntity getDistrict() {
		return district;
	}
	public void setDistrictEntity(DistrictEntity district) {
		this.district = district;
	}
	
	
	public List<RentAreaEntity> getAreaEntities() {
		return areaEntities;
	}
	public void setAreaEntities(List<RentAreaEntity> areaEntities) {
		this.areaEntities = areaEntities;
	}
	
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}
	public void setNumberOfBasement(Integer numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
//	public Long getDistrictid() {
//		return districtid;
//	}
//	public void setDistrictid(Long districtid) {
//		this.districtid = districtid;
//	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerPhoneNumber() {
		return managerPhoneNumber;
	}
	public void setManagerPhoneNumber(String managerPhoneNumber) {
		this.managerPhoneNumber = managerPhoneNumber;
	}
	public Long getFloorArea() {
		return floorArea;
	}
	public void setFloorArea(Long floorArea) {
		this.floorArea = floorArea;
	}
	public String getEmptyArea() {
		return emptyArea;
	}
	public void setEmptyArea(String emptyArea) {
		this.emptyArea = emptyArea;
	}
	public Long getRentPrice() {
		return rentPrice;
	}
	public void setRentPrice(Long rentPrice) {
		this.rentPrice = rentPrice;
	}
	public String getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
	}

	public Long getBrokerageFee() {
		return brokerageFee;
	}
	public void setBrokerageFee(Long brokerageFee) {
		this.brokerageFee = brokerageFee;
	}


	
	
	
}
