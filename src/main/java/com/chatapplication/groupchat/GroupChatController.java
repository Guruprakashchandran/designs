package com.chatapplication.groupchat;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chatapp.chatlists.GroupChatListsServlet;
import com.chatapplication.groupchatlist.Messages;
import com.chatapplication.userdetails.User;

public class GroupChatController implements GroupChatModelToControllerCall{

	private GroupChatControllerToViewCall groupChatControllerToViewCall;
	private GroupChatListsServlet groupChatLists;
	private GroupChatControllerToModelCall groupChatControllerToModelCall;
	public GroupChatController(GroupChatListsServlet groupChatListsServlet) {
		
		groupChatLists = groupChatListsServlet;
//		groupChatControllerToViewCall = groupChatView;
		groupChatControllerToModelCall = new GroupChatModel(this);
	}
	public void getGroupChatDetails(User user, HttpServletRequest req, HttpServletResponse res) {
		
		groupChatControllerToModelCall.getGroupChatDetails(user,req,res);
	}
	@Override
	public void showDetails(List<List<Map<String, List<Messages>>>> data,User user,List<String> groupNames,int[] friendsCount,List<List<Integer>> groupMembersCount,HttpServletRequest req,HttpServletResponse res) {
		
		groupChatLists.showDetails(data,user,groupNames,friendsCount,groupMembersCount,req,res);
	}
//	@Override
//	public void setMessagesViewed(String status, String loginerId) {
//		
//		groupChatControllerToModelCall.setMessagesViewed(status,loginerId);
//	}
	
	public void addMessage(User user, String id, String msg, String groupId, String status) {
		
		groupChatControllerToModelCall.addMessage(user,id,msg,groupId,status);
	}
//	@Override
//	public Map<String,String> getFriendsList(User user) {
//		
//		Map<String,String> friendList = groupChatControllerToModelCall.getFriendsList(user);
//		return friendList;
//	}
	@Override
	public void friendsList(Map<String,String> friendsList) {
		
		groupChatControllerToViewCall.friendsList(friendsList);
	}
	@Override
	public void noFriends() {
	
		groupChatControllerToViewCall.noFreinds();
	}
//	@Override
//	public void createGroup(List<String> mobileNoList, String grpName, User user) {
//		
//		groupChatControllerToModelCall.createGroup(mobileNoList,grpName,user);
//	}
	@Override
	public void notAddedFriends(int notFriendsCount, User user) {
		
		groupChatControllerToViewCall.notAddedFriends(notFriendsCount,user);
	}
	@Override
	public void friendsAdded(User user) {
		
		groupChatControllerToViewCall.friendsAdded(user);
	}
//	@Override
//	public void addAdditionalFriendsInGroup(User user, String groupId, List<String> noList) {
//		
//		groupChatControllerToModelCall.addAdditionalFriendsInGroup(user,groupId,noList);
//	}
	@Override
	public void friendsRejectedList(List<String> rejectedNumbers, User user) {
		
		groupChatControllerToViewCall.friendsRejectedList(rejectedNumbers,user);
	}
//	@Override
//	public void exitGroup(String groupId, User user) {
//		
//		groupChatControllerToModelCall.exitGroup(groupId,user);
//	}
	@Override
	public void successExit(User user) {
		
		groupChatControllerToViewCall.successExit(user);
	}
//	@Override
//	public void getFriends(User user, String id, String groupId) {
//		
//		groupChatControllerToModelCall.getFriends(user,id,groupId);
//	}
	@Override
	public void availableFriends(Map<String, String> friends,User user,String groupId,String id) {
		
		groupChatControllerToViewCall.availableFriends(friends,user,groupId,id);
	}

}
