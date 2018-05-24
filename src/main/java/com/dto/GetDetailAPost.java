package com.dto;


import java.util.ArrayList;
import java.util.List;

import com.entity.posts;

public class GetDetailAPost {
	
	private String idPost;
	private String idUserPost;
	private String location;
	private String content;
	private String urlPost;
	private String urlUserPost;
	private String timePost;
	private String nicNameUserPost;
	
	private List<ListInformationCommentOfAPost> listComment = new ArrayList<ListInformationCommentOfAPost>();
	
	

	

	public String getNicNameUserPost() {
		return nicNameUserPost;
	}

	public void setNicNameUserPost(String nicNameUserPost) {
		this.nicNameUserPost = nicNameUserPost;
	}

	public String getIdPost() {
		return idPost;
	}

	public void setIdPost(String idPost) {
		this.idPost = idPost;
	}

	public String getIdUserPost() {
		return idUserPost;
	}

	public void setIdUserPost(String idUserPost) {
		this.idUserPost = idUserPost;
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

	public String getUrlUserPost() {
		return urlUserPost;
	}

	public void setUrlUserPost(String urlUserPost) {
		this.urlUserPost = urlUserPost;
	}

	public String getTimePost() {
		return timePost;
	}

	public void setTimePost(String timePost) {
		this.timePost = timePost;
	}

	public List<ListInformationCommentOfAPost> getListComment() {
		return listComment;
	}

	public void setListComment(List<ListInformationCommentOfAPost> listComment) {
		this.listComment = listComment;
	}
	
	

}
