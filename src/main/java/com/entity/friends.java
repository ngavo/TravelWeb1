package com.entity;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection= "Friends")
public class friends {
	
	@Id
	private String _id;
	
	
	private String id_user;
	
	private List<String> list_friend;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getId_user() {
		return id_user;
	}

	public void setId_user(String id_user) {
		this.id_user = id_user;
	}

	public List<String> getList_friend() {
		return list_friend;
	}

	public void setList_friend(List<String> list_friend) {
		this.list_friend = list_friend;
	}

	
	
	
	

}
