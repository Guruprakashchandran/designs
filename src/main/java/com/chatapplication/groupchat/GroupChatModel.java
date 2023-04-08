package com.chatapplication.groupchat;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chatapplication.groupchatlist.GroupChatRepository;
import com.chatapplication.groupchatlist.Messages;
import com.chatapplication.userdetails.User;

public class GroupChatModel implements GroupChatControllerToModelCall {

	private GroupChatModelToControllerCall groupChatModelToControllerCall;

	public GroupChatModel(GroupChatController groupChatController) {

		groupChatModelToControllerCall = groupChatController;
	}

	@Override
	public void getGroupChatDetails(User user,HttpServletRequest req,HttpServletResponse res) {

		List<String> groupNames = new LinkedList<>();
		List<List<Integer>> groupMembersCount = new LinkedList<>();
		int[] friendsCount = new int[1];
		List<List<Map<String, List<Messages>>>> data = GroupChatRepository.getInstance().getGroupChatDetails(user,
				groupNames, friendsCount, groupMembersCount);
		groupChatModelToControllerCall.showDetails(data, user, groupNames, friendsCount, groupMembersCount,req,res);
	}

	@Override
	public Map<String,String> getFriendsList(User user) {

		Map<String, String> friendsList = GroupChatRepository.getInstance().getFriendsList(user);
		if (friendsList.size() == 0) {

			groupChatModelToControllerCall.noFriends();
//			groupChatModelToControllerCall.friendsList(friendsList);
//		} else {
//
//			groupChatModelToControllerCall.noFriends();
		}
		return friendsList;
	}

	@Override
	public void createGroup(List<String> mobileNoList, String grpName, User user) {

		int notFriendsCount = GroupChatRepository.getInstance().createGroup(user, grpName, mobileNoList);
		if (notFriendsCount == 0) {

			groupChatModelToControllerCall.friendsAdded(user);
		} else {

			groupChatModelToControllerCall.notAddedFriends(notFriendsCount, user);
		}
	}

	@Override
	public void addMessage(User user, String id, String msg, String groupId, String status) {

		GroupChatRepository.getInstance().addMessage(user, id, msg, groupId, status);
	}

	@Override
	public void setMessagesViewed(String status, String loginerId) {

		GroupChatRepository.getInstance().setMessageViewed(status, loginerId);
	}

	@Override
	public void addAdditionalFriendsInGroup(User user, String groupId, List<String> noList) {

		List<String> rejectedNumbers = GroupChatRepository.getInstance().addAdditionalFriendsInGroup(user, noList,
				groupId);
		if (rejectedNumbers.size() > 0) {

			groupChatModelToControllerCall.friendsRejectedList(rejectedNumbers, user);
		} else {

			groupChatModelToControllerCall.friendsAdded(user);
		}
	}

	@Override
	public void exitGroup(String groupId, User user) {

		GroupChatRepository.getInstance().exitGroup(user, groupId);
		groupChatModelToControllerCall.successExit(user);
	}

	@Override
	public void getFriends(User user, String id, String groupId) {

		Map<String, String> friends = GroupChatRepository.getInstance().getFriends(user, groupId, id);
		groupChatModelToControllerCall.availableFriends(friends, user, groupId, id);
	}
}
