package com.toychat.prj.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.toychat.prj.entity.FcmKey;

public interface FcmKeyRepository extends MongoRepository<FcmKey, String>{
	List<FcmKey> findByUserId(String target);

	List<FcmKey> findByUserIdIn(List<String> targets);
}
