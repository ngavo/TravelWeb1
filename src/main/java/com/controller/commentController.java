package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.comments;
import com.reponsitory.commentReponsitory;

@Controller
@RequestMapping("/comment")
public class commentController {
	
	@Autowired
	private commentReponsitory com;
	
	@RequestMapping(value="/{id_post}", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<comments>> getListComment(@PathVariable("id_post") String id_post)
	{
		return  new ResponseEntity<List<comments>>(com.getListComment(id_post),HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Void> insertComment(@RequestBody comments _comment)
	{
		com.InsertComment(_comment);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
