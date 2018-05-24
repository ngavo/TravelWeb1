package com.reponsitory;

import org.springframework.data.repository.CrudRepository;

import com.entity.posts;

public interface postReponsitoryJpa extends CrudRepository<posts, String> {

}
