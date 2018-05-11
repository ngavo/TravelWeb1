package com.dto;

import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;

public class GetPostForHome {
	
	private String id;
	private String id_user;
	
	private String avatar;
	private String nameUser;
	

	private String location;
	private String content;
	
	private String urlPost;
	
	/*@JsonFormat(pattern="dd.MM.yyyy HH:mm:ss.SSSZ")*/
	private String time;
	
	
	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId_user() {
		return id_user;
	}

	public void setId_user(String id_user) {
		this.id_user = id_user;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrlPost() {
		return urlPost;
	}

	public void setUrlPost(String urlPost) {
		this.urlPost = urlPost;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	

	
	
	
	

}
