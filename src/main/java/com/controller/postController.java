package com.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dto.GerUpdateImageForPost;
import com.dto.GetInsertPost;
import com.dto.GetUpdateInformationPostNotImage;
import com.entity.posts;
import com.reponsitory.postReponsitory;

@Controller
@RequestMapping("/post")
public class postController {
	
	@Autowired
	private postReponsitory pos;
	
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<posts>> getPosts(@PathVariable("id") String id)
	{
		List<posts> posk = pos.findPost(id);
		
		return new ResponseEntity<List<posts>>(posk,HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Void> insertPost(@ModelAttribute GetInsertPost _getPostToInsertDto) throws IOException
	{
		pos.insertPost(_getPostToInsertDto);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/postHome/{id}", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<posts>> findPostHome(@PathVariable("id") String id)
	{
		/*pos.findPostHome(id);*/
		
		return new ResponseEntity<List<posts>>(pos.findPostHome(id),HttpStatus.OK);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Void> UpdateImagePost(@ModelAttribute GerUpdateImageForPost k) throws IOException
	{
		pos.UpdateImagePost(k);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateinformation", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Void> UpdateInformationPostNotImage(@RequestBody GetUpdateInformationPostNotImage udpost )
	{
		pos.UpdateInformationPostNotImage(udpost);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}


}
