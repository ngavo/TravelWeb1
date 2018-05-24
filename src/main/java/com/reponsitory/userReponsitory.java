package com.reponsitory;



import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.ConfigApp.CloudinaryConfig;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.dto.DataTokenAndIdUser;
import com.dto.GetInsertUser;
import com.dto.GetUpdateImageForUser;
import com.dto.GetUpdateUser;
import com.dto.getUser;
import com.entity.users;

@Repository
public class userReponsitory {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private userRepositoryImp usImp;
	
	@Autowired
	TokenReponsitory tokenReponsitory;
	
	CloudinaryConfig cloudConfig = new CloudinaryConfig();
	
	public List<users> listUser(){
		List<users> us = mongoTemplate.findAll(users.class);
		return us;
	}
	
	public getUser geInformationUser(String id) 
	{
		getUser us = mongoTemplate.findById(id, getUser.class);
		
		return us;
	}
	
	public String InserUser(GetInsertUser user) throws IOException
	{
		Cloudinary c = new  Cloudinary(ObjectUtils.asMap(
				"cloud_name", "drpjudkfr",
				  "api_key", "661119547315821",
				  "api_secret", "mmp9IUQgnBMmYPEXlLgpN93SYgw"
				));
		
		
		
		File f =  Files.createTempFile("temp", user.getFile().getOriginalFilename()).toFile();
		user.getFile().transferTo(f);
		Map response = c.uploader().upload(f, ObjectUtils.emptyMap());
		String k=  (String)response.get("url");
		
		users us = new users();
		us.setNicName(user.getNicName());
		us.setUser_Name(user.getUser_Name());
		us.setPassword(user.getPassword());
		us.setGender(user.getGender());
		us.setAddress(user.getAddress());
		us.setSdt(user.getSdt());
		us.setEmail(user.getEmail());
		us.setUrl(k);
		
		 users userImp =  usImp.save(us);
		return tokenReponsitory.createToken(userImp.getId());
	}
	
	public void UpdateUser(GetUpdateUser user) throws IOException
	{
		
		Query query = new Query();
		
		query.addCriteria(Criteria.where("id").is(new ObjectId(user.getId())));
		
		users us = mongoTemplate.findOne(query, users.class);
		
		
		us.setUser_Name(user.getUser_Name());
		us.setPassword(user.getPassword());
		us.setNicName(user.getNicName());
		us.setGender(user.getGender());
		us.setAddress(user.getAddress());
		us.setSdt(user.getSdt());
		us.setEmail(user.getEmail());
		
		
		mongoTemplate.save(us);
		
	}
	
	public void UpdateImage(GetUpdateImageForUser userImage) throws IOException
	{
		Query query = new Query();
		
		query.addCriteria(Criteria.where("id").is(new ObjectId(userImage.getId())));
		users us = mongoTemplate.findOne(query, users.class);
		
		Cloudinary c = new  Cloudinary(ObjectUtils.asMap(
				"cloud_name", "drpjudkfr",
				  "api_key", "661119547315821",
				  "api_secret", "mmp9IUQgnBMmYPEXlLgpN93SYgw"
				));
		
		
		
		File f =  Files.createTempFile("temp", userImage.getFile().getOriginalFilename()).toFile();
		userImage.getFile().transferTo(f);
		Map response = c.uploader().upload(f, ObjectUtils.emptyMap());
		String k=  (String)response.get("url");
		
		us.setUrl(k);
		
		mongoTemplate.save(us);
		
	}
	
	public DataTokenAndIdUser Login(users us)
	{
		users u = mongoTemplate.findOne(new Query(Criteria.where("User_Name").is(us.getUser_Name())), users.class);
		
		
		
		if(u!=null)
		{
			if(us.getPassword()==us.getPassword())
			{
				DataTokenAndIdUser result = new DataTokenAndIdUser();
				result.setToken(tokenReponsitory.createToken(u.getId()));
				result.setIdUser(u.getId());
				return result ;
			}
				
		}
		
		return null;
	}
	
	

}
