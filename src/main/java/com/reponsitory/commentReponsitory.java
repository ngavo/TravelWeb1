package com.reponsitory;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.dto.CommentWithTime;
import com.dto.GetUpdateComment;
import com.entity.comments;
import com.entity.users;

@Repository
public class commentReponsitory {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private commentReponsitoryJpa cmmJpa;
	
	public List<comments> getListComment (String id)
	{
		List<comments> cm = mongoTemplate.find(new Query(Criteria.where("id_post").is(id)), comments.class);
		
		return cm;
		
	}
	
	public CommentWithTime InsertComment(comments _comment)
	{
		_comment.setTime(new Date());
		/*mongoTemplate.insert(_comment);*/
		comments cm = cmmJpa.save(_comment);
		CommentWithTime cmtime = new CommentWithTime();
		
		
		Query query = new Query();
		
		query.addCriteria(Criteria.where("id").is(new ObjectId(cm.getId_user())));
		
		users us = mongoTemplate.findOne(query, users.class);
		
		cmtime.setId(cm.getId());
		cmtime.setId_post(cm.getId_post());
		cmtime.setId_user(cm.getId_user());
		cmtime.setContent(cm.getContent());
		cmtime.setTime(ChuyenDay(cm.getTime()));
		cmtime.setUrlUserComment(us.getUrl());
		return cmtime;
	}
	
	public void DeleteComment(String id_comment)
	{
		mongoTemplate.findAndRemove(new Query(Criteria.where("id").is(new ObjectId(id_comment))), comments.class);
	}
	
	public CommentWithTime UpdateCommnet(GetUpdateComment _comment)
	{
		 comments cm = mongoTemplate.findOne(new Query(Criteria.where("id").is(new ObjectId(_comment.getIdComment()))), comments.class);
		 cm.setContent(_comment.getContent());
		 cm.setTime(new Date());
		 comments tam = cmmJpa.save(cm);
		 
		 
		 	CommentWithTime cmtime = new CommentWithTime();
			
			
			Query query = new Query();
			
			query.addCriteria(Criteria.where("id").is(new ObjectId(tam.getId_user())));
			
			users us = mongoTemplate.findOne(query, users.class);
			
			cmtime.setId(tam.getId());
			cmtime.setId_post(tam.getId_post());
			cmtime.setId_user(tam.getId_user());
			cmtime.setContent(tam.getContent());
			cmtime.setTime(ChuyenDay(tam.getTime()));
			cmtime.setUrlUserComment(us.getUrl());
			return cmtime;
		 
		 
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
