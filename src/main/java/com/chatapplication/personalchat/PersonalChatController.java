package com.chatapplication.personalchat;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chatapp.chatlists.PrivateChatListServlet;
import com.chatapplication.personalchatlist.Message;
import com.chatapplication.userdetails.User;

public class PersonalChatController implements PersonalChatModelToControllerCall{

//	private PersonalChatControllerToViewCall personalChatControllerToViewCall;
	private PersonalChatControllerToModelCall personalChatControllerToModelCall;
	private PrivateChatListServlet privateChatListServlet;
	public PersonalChatController() {
		
//		personalChatControllerToViewCall = personalChatView;
		privateChatListServlet = new PrivateChatListServlet();
		personalChatControllerToModelCall = new PersonalChatModel(this);
	}
//	@Override
//	public void addFriend(User user, String newNumber) {
//		
//		personalChatControllerToModelCall.addFriend(user,newNumber);
//	}
//	@Override
//	public void friendNotInapp(User user) {
//		
//		personalChatControllerToViewCall.friendNotInapp(user);
//	}
//	@Override
//	public void alreadyFriend(User user) {
//		
//		personalChatControllerToViewCall.alreadyFriend(user);
//	}
//	@Override
//	public void addedFailed(User user) {
//		
//		personalChatControllerToViewCall.addedFailed(user);
//	}
//	@Override
//	public void addedSuccessfully(User user) {
//		
//		personalChatControllerToViewCall.addedSuccessfully(user);
//	}
//	@Override
	public void getPersonalChats(User user, HttpServletRequest req, HttpServletResponse res) {

		personalChatControllerToModelCall.getPersonalChats(user,req,res);
	}
	
	@Override
	public void showContacts(List<List<Map<String, List<Message>>>> data,User user,List<String> friendsName,int[] totalRegisterCount,HttpServletRequest req,HttpServletResponse res) {
		
		privateChatListServlet.showContacts(data,user,friendsName,totalRegisterCount,req,res);
	}
//	@Override
//	public void addMessage(User user, String personId, String msg,String loginerId,String status) {
//		
//		personalChatControllerToModelCall.addMessage(user,personId,msg,loginerId,status);
//	}
//	@Override
//	public void msgAddedFailed(String mobileNo) {
//		
//		personalChatControllerToViewCall.msgAddedFailed(mobileNo);
//	}
//	@Override
//	public void setMessagesViewed(String status, String loginerId) {
//		
//		personalChatControllerToModelCall.setMessageViewed(status,loginerId);
//	}
//	@Override
//	public void closeConnection() {
//		
//		personalChatControllerToModelCall.closeConnection();
//	}
	@Override
	public void friendNotInapp(User user) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void alreadyFriend(User user) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addedFailed(User user) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addedSuccessfully(User user) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void msgAddedFailed(String mobileNo) {
		// TODO Auto-generated method stub
		
	}
}
