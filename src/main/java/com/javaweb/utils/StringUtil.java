package com.javaweb.utils;


public class StringUtil {
	public static Boolean checkString(String Data) {
		if(Data != null && !Data.equals("")){
			return true;
		}
		return false;
	}
}
