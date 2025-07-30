package com.toychat.prj.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.toychat.prj.entity.FcmPush;

public interface FcmPushRepository extends MongoRepository<FcmPush, String>{

	List<FcmPush> findByTarget(String target);

}
