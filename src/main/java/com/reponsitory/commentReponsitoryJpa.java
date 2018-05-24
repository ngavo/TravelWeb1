package com.reponsitory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.entity.comments;

@Repository
public interface commentReponsitoryJpa extends CrudRepository<comments, String> {

}
