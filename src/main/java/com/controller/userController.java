package com.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ConfigApp.CloudinaryConfig;
import com.cloudinary.Cloudinary;
import com.cloudinary.Singleton;
import com.dto.DataTokenAndIdUser;
import com.dto.GetInsertUser;
import com.dto.GetUpdateImageForUser;
import com.dto.GetUpdateUser;
import com.dto.getUser;
import com.entity.users;
import com.reponsitory.userReponsitory;
import com.cloudinary.utils.ObjectUtils;


@Controller
@RequestMapping("/user")
public class userController {
	
	@Autowired
	private userReponsitory us;
	
	CloudinaryConfig cloudConfig = new CloudinaryConfig();
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<users>> getList()
	{
		return new ResponseEntity<List<users>>(us.listUser(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<users> getInformationUser(@PathVariable("id") String id)
	{
		return new ResponseEntity<users>(us.geInformationUser(id),HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/register" ,method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> InserUser(@ModelAttribute GetInsertUser user) throws IOException{
		
		
		
		return new ResponseEntity<String>(us.InserUser(user),HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/updateUser",method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Void> UpdateUser(@RequestBody GetUpdateUser user ) throws IOException
	{
		us.UpdateUser(user);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateImage", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Void> UpdateImage(@ModelAttribute GetUpdateImageForUser userImage) throws IOException
	{
		us.UpdateImage(userImage);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<DataTokenAndIdUser> Login(@RequestBody users use)
	{
		if(us.Login(use)==null)
		{
			return new ResponseEntity<DataTokenAndIdUser>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<DataTokenAndIdUser>(us.Login(use),HttpStatus.OK);
	}
	
	
	

}
