package com.toychat.prj.web;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.WebSocketSession;

import com.toychat.prj.entity.Chat;
import com.toychat.prj.entity.Chatroom;
import com.toychat.prj.entity.ChatroomInfo;
import com.toychat.prj.entity.User;
import com.toychat.prj.entity.UserDetailsImpl;
import com.toychat.prj.handler.WebSocketChatHandler;
import com.toychat.prj.service.ChatService;
import com.toychat.prj.service.ChatroomService;
 
@RestController
@RequestMapping("/api/admin/chat")
public class AdminChatConroller {
	
	@Autowired
	private WebSocketChatHandler webSocketChatHandler;
	
    @Autowired
    private ChatService chatService;
    
    @Autowired
    private ChatroomService chatroomService;
    
    @PostMapping("/mnglist")
    public List<ChatroomInfo> getChatRoomsMngList(@RequestBody HashMap<String,Object> searchMap) {
        // id
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String id = userDetails.getUsername();
        List<ChatroomInfo> resultList = chatroomService.getChatRoomsMngList(searchMap);
        return resultList;
    }    
    
    @PostMapping("/liveChatWaitingList")
    public List<ChatroomInfo> getLiveChatWaitingList(@RequestBody HashMap<String,Object> searchMap) {
    	return chatroomService.getLiveChatWaitingList(searchMap);
    }     

    @PostMapping("/chatManageInfo")
    public Chatroom getChatManageInfo(@RequestBody Chatroom chatroom) {
    	return chatroomService.getChatManageInfo(chatroom);
    }     

    @PostMapping("/saveChatManageInfo")
    public void saveChatManageInfo(@RequestBody Chatroom chatroom) {
    	chatroomService.saveChatManageInfo(chatroom);
    }     

    @PostMapping("/mylist")
    public List<ChatroomInfo> getChatRoomsByUserId(@RequestBody User user) {
        return chatroomService.getChatRoomsByUserId(user);
    }       
    

}
