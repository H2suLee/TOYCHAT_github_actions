package com.toychat.prj.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Component("Util")
public class Util {

	public static String getNowDttm() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String credt = now.format(formatter);
		return credt;
	}
	
	public static String getRedirectBaseUrl(HttpServletRequest request) {
	    String scheme = request.getScheme();             // http 또는 https
	    String serverName = request.getServerName();     // 예: localhost
	    int serverPort = request.getServerPort();        // 예: 9090
	    String baseUrl = scheme + "://" + serverName + ":" + serverPort;
	    
	    return baseUrl;
	}
}

