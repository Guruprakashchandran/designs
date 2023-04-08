package com.chatapplication.loginorregisterpage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chatapplication.userdetails.User;

public interface LoginOrRegisterPageModelToControllerCall {

	void addedSuccessfully();

	void userMobileNoExist();

	void userEmailExist();

	void loginFailed(String msg, HttpServletResponse res);

	void loginSuccess(User userDetail, HttpServletResponse res, HttpServletRequest req);

}
