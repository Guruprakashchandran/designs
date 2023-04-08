package com.chatapplication.groupchat;

import java.util.List;
import java.util.Map;

import com.chatapplication.groupchatlist.Messages;
import com.chatapplication.userdetails.User;

public interface GroupChatControllerToViewCall {

	void showDetails(List<List<Map<String, List<Messages>>>> data, User user, List<String> groupNames, int[] friendsCount, List<List<Integer>> groupMembersCount);

	void noFreinds();

	void friendsList(Map<String, String> friendsList);

	void notAddedFriends(int notFriendsCount, User user);

	void friendsAdded(User user);

	void friendsRejectedList(List<String> rejectedNumbers, User user);

	void successExit(User user);

	void availableFriends(Map<String, String> friends, User user, String groupId, String id);


}
