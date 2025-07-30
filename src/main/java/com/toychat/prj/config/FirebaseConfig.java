package com.toychat.prj.config;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Configuration
public class FirebaseConfig {
	
	@Value("${firebase.secret}")
	private String secret;
	
    @PostConstruct
    public void initializeFirebase() {
        try {
        	
        	byte[] decodedByte = Base64.getDecoder().decode(secret);
        	//String decodedStr = new String(decodedByte);
        	//System.out.println(decodedStr);
        	
        	InputStream serviceAccount =
                new ByteArrayInputStream(decodedByte);

            FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

            FirebaseApp.initializeApp(options);
            System.out.println("Firebase Admin SDK initialized");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
