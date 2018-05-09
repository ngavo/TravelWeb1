package com.reponsitory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.dto.GetInsertFriend;
import com.dto.getUsersToFriend;
import com.entity.friends;

@Repository
public class friendReponsitory {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public void InsertFriend(GetInsertFriend insert_friend)
	{
		/*khi insert friend thi phai insert ca hai ben*/
		
		
		friends f_user = mongoTemplate.findOne(new Query(Criteria.where("id_user").is(insert_friend.getIduser())), friends.class);
		friends f_friend = mongoTemplate.findOne(new Query(Criteria.where("id_user").is(insert_friend.getIdfriend())), friends.class);
		
		
		if(f_user!=null)
		{
			mongoTemplate.updateFirst(new Query(Criteria.where("id_user").is(insert_friend.getIduser())),new Update().push("list_friend").value(insert_friend.getIdfriend()),friends.class);
		}
		else
		{
			List<String> lsFriend = new ArrayList<String>();
			lsFriend.add(insert_friend.getIdfriend());
			friends friend = new friends();
			friend.setId_user(insert_friend.getIduser());
			friend.setList_friend(lsFriend);
			mongoTemplate.insert(friend);
		}
		
		if(f_friend!=null)
		{
			mongoTemplate.updateFirst(new Query(Criteria.where("id_user").is(insert_friend.getIdfriend())),new Update().push("list_friend").value(insert_friend.getIduser()),friends.class);
		}
		else
		{
			List<String> lsFriend = new ArrayList<String>();
			lsFriend.add(insert_friend.getIduser());
			friends friend = new friends();
			friend.setId_user(insert_friend.getIdfriend());
			friend.setList_friend(lsFriend);
			mongoTemplate.insert(friend);
			
		}
		
	}
	
	
	public List<getUsersToFriend> listfriend(String id)
	{
	
		friends id_friend = mongoTemplate.findOne(new Query(Criteria.where("id_user").is(id)), friends.class);
		
		List<String> list_id_friend = id_friend.getList_friend();
		List<getUsersToFriend> _users = new ArrayList<getUsersToFriend>() ;
		for (String friend : list_id_friend) {
			getUsersToFriend us = mongoTemplate.findOne(new Query(Criteria.where("id").is(friend)), getUsersToFriend.class);
			_users.add(us);
		}
		
		return _users;
	}

}
