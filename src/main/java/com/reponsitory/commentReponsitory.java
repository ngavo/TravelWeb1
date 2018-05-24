package com.reponsitory;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.dto.GetUpdateComment;
import com.entity.comments;

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
	
	public comments InsertComment(comments _comment)
	{
		_comment.setTime(new Date());
		/*mongoTemplate.insert(_comment);*/
		return cmmJpa.save(_comment);
	}
	
	public void DeleteComment(String id_comment)
	{
		mongoTemplate.findAndRemove(new Query(Criteria.where("id").is(new ObjectId(id_comment))), comments.class);
	}
	
	public comments UpdateCommnet(GetUpdateComment _comment)
	{
		 comments cm = mongoTemplate.findOne(new Query(Criteria.where("id").is(new ObjectId(_comment.getIdComment()))), comments.class);
		 cm.setContent(_comment.getContent());
		 cm.setTime(new Date());
		 return cmmJpa.save(cm);
	}

}
