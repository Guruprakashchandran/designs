package com.chatapplication.personalchat;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chatapplication.groupchatlist.GroupChatRepository;
import com.chatapplication.personalchatlist.Message;
import com.chatapplication.personalchatlist.PersonalChatRepository;
import com.chatapplication.userdetails.User;

public class PersonalChatModel implements PersonalChatControllerToModelCall {

	private PersonalChatModelToControllerCall personalChatModelToControllerCall;

	public PersonalChatModel(PersonalChatController personalChatController) {

		personalChatModelToControllerCall = personalChatController;
	}

	@Override
	public void getPersonalChats(User user,HttpServletRequest req,HttpServletResponse res) {

		List<String> friendsName = new LinkedList<>();
		int totalRegisterCount[] = new int[1];
		List<List<Map<String, List<Message>>>> data = PersonalChatRepository.getInstance().getPeresonalChats(user,friendsName,totalRegisterCount);
		personalChatModelToControllerCall.showContacts(data,user,friendsName,totalRegisterCount,req,res);
	}

	@Override
	public void addFriend(User user, String newNumber) {

		int category = PersonalChatRepository.getInstance().addFriend(user, newNumber);
		if (category == 1) {

			personalChatModelToControllerCall.friendNotInapp(user);
		} else if (category == 2) {

			personalChatModelToControllerCall.alreadyFriend(user);
		} else if (category == 3) {

			personalChatModelToControllerCall.addedFailed(user);
		} else if (category == 0) {

			personalChatModelToControllerCall.addedSuccessfully(user);
		}
	}

	@Override
	public void addMessage(User user, String personId, String msg,String loginerId,String status) {
		
		PersonalChatRepository.getInstance().addMessage(user,personId,msg,loginerId,status);
//			
//			personalChatModelToControllerCall.msgAddedFailed(mobileNo);
//		}
	}

	@Override
	public void setMessageViewed(String status, String loginerId) {
		
		PersonalChatRepository.getInstance().setMessageViewed(status,loginerId);
	}

	@Override
	public void closeConnection() {
		
		PersonalChatRepository.getInstance().closeConnection();
		GroupChatRepository.getInstance().closeConnection();
	}
}
