package com.chatapplication.groupchat;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chatapplication.userdetails.User;

public interface GroupChatControllerToModelCall {

	void getGroupChatDetails(User user, HttpServletRequest req, HttpServletResponse res);

	Map<String, String> getFriendsList(User user);

	void createGroup(List<String> mobileNoList, String grpName, User user);

	void addMessage(User user, String id, String msg, String groupId, String status);

	void setMessagesViewed(String status, String loginerId);

	void addAdditionalFriendsInGroup(User user, String groupId, List<String> noList);

	void exitGroup(String groupId, User user);

	void getFriends(User user, String id, String groupId);

}
