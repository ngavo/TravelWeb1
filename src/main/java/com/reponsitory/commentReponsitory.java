package com.reponsitory;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.entity.comments;

@Repository
public class commentReponsitory {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public List<comments> getListComment (String id)
	{
		List<comments> cm = mongoTemplate.find(new Query(Criteria.where("id_post").is(id)), comments.class);
		
		return cm;
		
	}
	
	public void InsertComment(comments _comment)
	{
		_comment.setTime(new Date());
		mongoTemplate.insert(_comment);
	}

}
