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

import com.dto.GetInsertFriend;
import com.dto.getUsersToFriend;
import com.reponsitory.friendReponsitory;

@Controller
@RequestMapping("/friend")
public class friendController {
	
	@Autowired
	private friendReponsitory fr;
	
	@RequestMapping(value="/{id}",  method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<getUsersToFriend>> getFriend(@PathVariable("id") String id)
	{
		List<getUsersToFriend> _users = fr.listfriend(id);
		return new ResponseEntity<List<getUsersToFriend>>(_users,HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Void> insertFriend(@RequestBody GetInsertFriend newFriend)
	{
		fr.InsertFriend(newFriend);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
