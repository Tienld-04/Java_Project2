package com.javaweb.model;

import javax.persistence.Column;

public class BuildingRequestDTO {
	private Long id;
	private String name;
	private Integer numberOfBasement;
	private String ward;
	private String street;
	private Long districtid;
	private Long rentPrice;
	
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

	public Long getDistrictid() {
		return districtid;
	}

	public void setDistrictid(Long districtid) {
		this.districtid = districtid;
	}

	public Long getRentPrice() {
		return rentPrice;
	}

	public void setRentPrice(Long rentPrice) {
		this.rentPrice = rentPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


}
