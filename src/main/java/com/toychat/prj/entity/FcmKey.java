package com.toychat.prj.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "fcmKey")
@CompoundIndex(name = "user_token_unique", def = "{'userId': 1, 'token': 1}", unique = true)
public class FcmKey {
	
	public static final String SEQUENCE_NAME = "fcmKey_sequence";
	
    @Id
	private String seq;
	private String userId;
	private String token;
	
}	
