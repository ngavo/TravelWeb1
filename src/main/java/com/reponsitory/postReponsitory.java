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

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


import com.ConfigApp.CloudinaryConfig;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.dto.GerUpdateImageForPost;
import com.dto.GetDetailAPost;
import com.dto.GetInsertPost;
import com.dto.GetPostForHome;
import com.dto.GetUpdateInformationPostNotImage;
import com.dto.ListInformationCommentOfAPost;
import com.entity.comments;
import com.entity.friends;
import com.entity.posts;
import com.entity.users;


@Repository
public class postReponsitory {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private postReponsitoryJpa postJpa;
	
	CloudinaryConfig cloudConfig = new CloudinaryConfig();
	
	//day la list bai post cua 1 user
	public List<GetPostForHome> findPost(String id_user)
	{
		List<posts> poss = mongoTemplate.find(new Query(Criteria.where("id_user").is(id_user)),posts.class);
		
		
		List<GetPostForHome> listPost = new ArrayList<GetPostForHome>();
		
		for (posts getPostForHome : poss) {
			
			users us = mongoTemplate.findOne(new Query(Criteria.where("id").is(new ObjectId(id_user))), users.class);
			
			GetPostForHome ps = new GetPostForHome();
			ps.setId(getPostForHome.getId());
			ps.setId_user(us.getId());
			ps.setAvatar(us.getUrl());
			ps.setNameUser(us.getNicName());
			ps.setLocation(getPostForHome.getLocation());
			ps.setContent(getPostForHome.getContent());
			ps.setUrlPost(getPostForHome.getUrl());
			
			
			long time = new Date().getTime()/1000 - getPostForHome.getTime().getTime()/1000;
			
			String timepost = "";
			if(time<60)
			{
				timepost = " vừa xong";
			}
			if( 60<=time && time < 3600 )
			{
				int tamp = (int)time/60;
				timepost = tamp + " phút trước"; 
			}
			
			if( 3600<=time && time < 86400)
			{
				int tamp = (int)time/3600;
				timepost = tamp + " h trước";
			}
			
			if(time>= 86400)
			{
				int tamp = (int)time/86400;
				timepost = tamp + " ngày trước";
			}
			
			ps.setTime(timepost);
			
			listPost.add(ps);
			
			
			
		}
		
		
		
		return listPost;
	}
	
	public posts insertPost(GetInsertPost _post) throws IOException
	{
		Cloudinary c = new  Cloudinary(ObjectUtils.asMap(
				"cloud_name", "drpjudkfr",
				  "api_key", "661119547315821",
				  "api_secret", "mmp9IUQgnBMmYPEXlLgpN93SYgw"
				));
		
		File f =  Files.createTempFile("temp", _post.getFile().getOriginalFilename()).toFile();
		_post.getFile().transferTo(f);
		Map response = c.uploader().upload(f, ObjectUtils.emptyMap());
		String url=  (String)response.get("url");
		
		posts ps = new posts();
		ps.setId_user(_post.getId_user());
		ps.setContent(_post.getContent());
		ps.setLocation(_post.getLocation());
		ps.setUrl(url);
		
			ps.setTime(new Date());
			/*mongoTemplate.insert(ps);*/
			return postJpa.save(ps);
		
	}
	
	public List<GetPostForHome> findPostHome(String id_user)
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
		
		List<GetPostForHome> postHome = new ArrayList<GetPostForHome>();
		
		
		for(posts psD : ps)
		{
			GetPostForHome psH = new GetPostForHome();
			psH.setId(psD.getId());
			psH.setId_user(psD.getId_user());
			psH.setContent(psD.getContent());
			psH.setUrlPost(psD.getUrl());
			psH.setLocation(psD.getLocation());
			@SuppressWarnings("deprecation")
			long time = new Date().getTime()/1000 - psD.getTime().getTime()/1000;
			
			String timepost = "";
			if(time<60)
			{
				timepost = " vừa xong";
			}
			if( 60<=time && time < 3600 )
			{
				int tamp = (int)time/60;
				timepost = tamp + " phút trước"; 
			}
			
			if( 3600<=time && time < 86400)
			{
				int tamp = (int)time/3600;
				timepost = tamp + " h trước";
			}
			
			if(time>= 86400)
			{
				int tamp = (int)time/86400;
				timepost = tamp + " ngày trước";
			}
			
				
					
				
			
			
			psH.setTime(timepost);
			
			
			users us = mongoTemplate.findOne(new Query(Criteria.where("id").is(psD.getId_user())), users.class);
			
			psH.setAvatar(us.getUrl());
			psH.setNameUser(us.getNicName());
			postHome.add(psH);
			
		}
		
		return postHome;
		
	}
	
	public posts UpdateImagePost(GerUpdateImageForPost k) throws IOException
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
		
		posts ps = mongoTemplate.findOne(new Query(Criteria.where("id").is(new ObjectId(k.getId()))), posts.class);
		
		ps.setUrl(ur);
		return postJpa.save(ps);
		
	}
	
	public posts UpdateInformationPostNotImage(GetUpdateInformationPostNotImage udpost)
	{
		posts ps = mongoTemplate.findOne(new Query(Criteria.where("id").is(new ObjectId(udpost.getId()) )), posts.class);
		ps.setContent(udpost.getContent());
		ps.setLocation(udpost.getLocation());
		ps.setTime(new Date());
		/*mongoTemplate.save(ps);*/
		
		return postJpa.save(ps);
	}
	
	public GetDetailAPost getDetailAPost(String idPost)
	{
		GetDetailAPost detailPost = new GetDetailAPost();
		
		posts ps = mongoTemplate.findOne(new Query(Criteria.where("id").is(new ObjectId(idPost))), posts.class);
		
		users us = mongoTemplate.findOne(new Query(Criteria.where("id").is(new ObjectId(ps.getId_user()))), users.class);
		
		detailPost.setIdPost(ps.getId());
		detailPost.setIdUserPost(ps.getId_user());
		detailPost.setLocation(ps.getLocation());
		detailPost.setContent(ps.getContent());
		detailPost.setUrlPost(ps.getUrl());
		detailPost.setUrlUserPost(us.getUrl());
		detailPost.setTimePost(ChuyenDay(ps.getTime()));
		detailPost.setNicNameUserPost(us.getNicName());
		
		List<comments> listComment = mongoTemplate.find(new Query(Criteria.where("id_post").is(idPost)), comments.class);
		List<ListInformationCommentOfAPost> listInfo = new ArrayList<ListInformationCommentOfAPost>();
		for (comments comment : listComment) {
			
			ListInformationCommentOfAPost ifocomment = new ListInformationCommentOfAPost();
			ifocomment.setIdComment(comment.getId());
			ifocomment.setIdUserComment(comment.getId_user());
			users usComment = mongoTemplate.findOne(new Query(Criteria.where("id").is(new ObjectId(comment.getId_user()))), users.class);
			ifocomment.setUrlUserComment(usComment.getUrl());
			ifocomment.setContentComment(comment.getContent());
			ifocomment.setTimeComment(ChuyenDay(comment.getTime()));
			ifocomment.setNicNameComment(usComment.getNicName());
			listInfo.add(ifocomment);
		}
		
		Collections.reverse(listInfo);
		detailPost.setListComment(listInfo);
		
		return detailPost;
		
	}
	
	public String ChuyenDay(Date timeConvert)
	{
		long time = new Date().getTime()/1000 - timeConvert.getTime()/1000;
		
		String timepost = "";
		if(time<60)
		{
			timepost = " vừa xong";
		}
		if( 60<=time && time < 3600 )
		{
			int tamp = (int)time/60;
			timepost = tamp + " phút trước"; 
		}
		
		if( 3600<=time && time < 86400)
		{
			int tamp = (int)time/3600;
			timepost = tamp + " h trước";
		}
		
		if(time>= 86400)
		{
			int tamp = (int)time/86400;
			timepost = tamp + " ngày trước";
		}
		
		return timepost;
		
	}
	
	

}
