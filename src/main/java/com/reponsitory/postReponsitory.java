package com.reponsitory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


import com.ConfigApp.CloudinaryConfig;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.dto.GerUpdateImageForPost;
import com.dto.GetUpdateInformationPostNotImage;
import com.entity.friends;
import com.entity.posts;


@Repository
public class postReponsitory {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	CloudinaryConfig cloudConfig = new CloudinaryConfig();
	
	//day la list bai post cua 1 user
	public List<posts> findPost(String id_user)
	{
		List<posts> pos = mongoTemplate.find(new Query(Criteria.where("id_user").is(id_user)),posts.class);
		
		return pos;
	}
	
	public void insertPost(posts _post)
	{
			_post.setTime(new Date());
			mongoTemplate.insert(_post);
		
	}
	
	public List<posts> findPostHome(String id_user)
	{
		/*users listIdFriend = mongoTemplate.findOne(new Query(Criteria.where("id").is(new ObjectId(id_user))), users.class);*/
		
		
		Query query = new Query(Criteria.where("id_user").is(id_user));
		query.fields().include("list_friend");
		friends john = mongoTemplate.findOne(query, friends.class);
		List<String> friend = john.getList_friend();
		
		List<posts> ps = new ArrayList<posts>(); 
		
		for (String string : friend) {
			
			//lít bai post cua 1 friend
			Query querypost = new Query();
			querypost.addCriteria(Criteria.where("id_user").is(string));
			/*querypost.with(new Sort(Sort.Direction.DESC,"time"));*/
			List<posts> postOfFriend = mongoTemplate.find(querypost, posts.class);
			
			for (posts posts : postOfFriend) {
				ps.add(posts);
			}
			
		}
		
		
		Collections.sort(ps, new Comparator<posts>() {
			@Override
			public int compare(posts ps1, posts ps2) {
				if(ps1.getTime().compareTo(ps2.getTime()) < 0)
				{
					return 1;
				}
				else
				{
					if(ps1.getTime().compareTo(ps2.getTime()) ==0)
					{
						return 0;
					}
					else
						return -1;
				}
			}
		});
		
		return ps;
		
	}
	
	public void UpdateImagePost(GerUpdateImageForPost k) throws IOException
	{
		Cloudinary c = new  Cloudinary(ObjectUtils.asMap(
				"cloud_name", "drpjudkfr",
				  "api_key", "661119547315821",
				  "api_secret", "mmp9IUQgnBMmYPEXlLgpN93SYgw"
				));
		
		File f =  Files.createTempFile("temp", k.getFile().getOriginalFilename()).toFile();
		k.getFile().transferTo(f);
		Map response = c.uploader().upload(f, ObjectUtils.emptyMap());
		String ur=  (String)response.get("url");
		
		posts ps = mongoTemplate.findOne(new Query(Criteria.where("id").is(k.getId())), posts.class);
		
		ps.setUrl(ur);
		mongoTemplate.save(ps);
		
	}
	
	public void UpdateInformationPostNotImage(GetUpdateInformationPostNotImage udpost)
	{
		posts ps = mongoTemplate.findOne(new Query(Criteria.where("id").is(udpost.getId())), posts.class);
		ps.setContent(udpost.getContent());
		ps.setLocation(udpost.getLocation());
		ps.setTime(new Date());
		mongoTemplate.save(ps);
	}

}
