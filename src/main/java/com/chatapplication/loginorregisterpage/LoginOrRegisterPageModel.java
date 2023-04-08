package com.chatapplication.loginorregisterpage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chatapplication.userdetails.User;
import com.chatapplication.userdetails.UserDetailsRepository;

public class LoginOrRegisterPageModel implements LoginOrRegisterPageControllerToModelCall {

	private LoginOrRegisterPageModelToControllerCall loginOrRegisterPageModelToControllerCall;

	public LoginOrRegisterPageModel(LoginOrRegisterPageController loginOrRegisterPageController) {

		loginOrRegisterPageModelToControllerCall = loginOrRegisterPageController;
	}

	@Override
	public void setUserDetails(User user) {

		boolean register = UserDetailsRepository.getInstance().addDetails(user);
		if (register) {

			loginOrRegisterPageModelToControllerCall.addedSuccessfully();
		} else {

			loginOrRegisterPageModelToControllerCall.userMobileNoExist();
		}
	}

	@Override
	public void checkLogin(User user,HttpServletResponse res,HttpServletRequest req) {

		String[] msg=new String[1];
		User userDetail = UserDetailsRepository.getInstance().checkLogin(user,msg);

		if (userDetail != null) {

			loginOrRegisterPageModelToControllerCall.loginSuccess(userDetail,res,req);
		} else {

			loginOrRegisterPageModelToControllerCall.loginFailed(msg[0],res);
		}
	}

	@Override
	public void closeConnection() {
		
		UserDetailsRepository.getInstance().closeConnection();
	}
}