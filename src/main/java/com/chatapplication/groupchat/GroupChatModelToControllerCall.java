package com.chatapplication.groupchat;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chatapplication.groupchatlist.Messages;
import com.chatapplication.userdetails.User;

public interface GroupChatModelToControllerCall {

	void showDetails(List<List<Map<String, List<Messages>>>> data, User user, List<String> groupNames, int[] friendsCount, List<List<Integer>> groupMembersCount, HttpServletRequest req, HttpServletResponse res);

	void friendsList(Map<String, String> friendsList);

	void noFriends();

	void notAddedFriends(int notFriendsCount, User user);

	void friendsAdded(User user);

	void friendsRejectedList(List<String> rejectedNumbers, User user);

	void successExit(User user);

	void availableFriends(Map<String, String> friends, User user, String groupId, String id);

}
