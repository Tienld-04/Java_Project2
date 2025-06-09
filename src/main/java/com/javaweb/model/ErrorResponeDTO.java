package com.javaweb.model;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponeDTO {
	private String error;
	private List<String> detailError = new ArrayList<String>();
	
	public String getError() {
		return error;
	}       
	public void setError(String error) {
		this.error = error;
	}
	public List<String> getDetailError() {
		return detailError;
	}
	public void setDetailError(List<String> detailError) {
		this.detailError = detailError;
	}
	
	
}
