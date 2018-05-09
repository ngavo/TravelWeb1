package com.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Users")
public class getUsersToFriend {
	
	@Id
	private String id;
	
	private String NicName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNicName() {
		return NicName;
	}

	public void setNicName(String nicName) {
		NicName = nicName;
	}
	
	
	

}