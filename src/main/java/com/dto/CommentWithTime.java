package com.dto;



public class CommentWithTime {
	
	private String id;
	
	private String id_post;
	private String id_user;
	private String urlUserComment;
	private String content;
	
	private String time;
	
	

	public String getUrlUserComment() {
		return urlUserComment;
	}

	public void setUrlUserComment(String urlUserComment) {
		this.urlUserComment = urlUserComment;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId_post() {
		return id_post;
	}

	public void setId_post(String id_post) {
		this.id_post = id_post;
	}

	public String getId_user() {
		return id_user;
	}

	public void setId_user(String id_user) {
		this.id_user = id_user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	

}
