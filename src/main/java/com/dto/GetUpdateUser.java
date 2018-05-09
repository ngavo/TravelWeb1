package com.dto;

import org.springframework.web.multipart.MultipartFile;

public class GetUpdateUser {
	
	private String id;
	
	private String User_Name;
	
	private String Password;
	
	private String NicName;
	
	private String Gender;
	
	private String Address;
	
	private String Sdt;
	
	private String Email;
	
	
	


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getUser_Name() {
		return User_Name;
	}


	public void setUser_Name(String user_Name) {
		User_Name = user_Name;
	}


	public String getPassword() {
		return Password;
	}


	public void setPassword(String password) {
		Password = password;
	}


	public String getNicName() {
		return NicName;
	}


	public void setNicName(String nicName) {
		NicName = nicName;
	}


	public String getGender() {
		return Gender;
	}


	public void setGender(String gender) {
		Gender = gender;
	}


	public String getAddress() {
		return Address;
	}


	public void setAddress(String address) {
		Address = address;
	}


	public String getSdt() {
		return Sdt;
	}


	public void setSdt(String sdt) {
		Sdt = sdt;
	}


	public String getEmail() {
		return Email;
	}


	public void setEmail(String email) {
		Email = email;
	}


	
	
	

}
