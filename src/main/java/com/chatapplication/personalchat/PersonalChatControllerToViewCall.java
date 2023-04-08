package com.chatapplication.personalchat;

import java.util.List;
import java.util.Map;

import com.chatapplication.personalchatlist.Message;
import com.chatapplication.userdetails.User;

public interface PersonalChatControllerToViewCall {

	void friendNotInapp(User user);

	void alreadyFriend(User user);

	void addedFailed(User user);

	void addedSuccessfully(User user);

	void showContacts(List<List<Map<String, List<Message>>>> data, User user, List<String> friendsName, int[] totalRegisterCount);

	void msgAddedFailed(String mobileNo);

}
