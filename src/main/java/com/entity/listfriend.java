package com.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Users")
public class listfriend {
	
	@Id
	private String _id;
	private String NicName;
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getNicName() {
		return NicName;
	}
	public void setNicName(String nicName) {
		NicName = nicName;
	}
	
	

	
}