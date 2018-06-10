package com.reponsitory;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.entity.users;

@Repository
public interface userRepositoryImp extends MongoRepository<users, String> {
	
	List<users> findByGender(String nicname);
}
