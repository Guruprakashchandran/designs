package com.chatapplication.loginorregisterpage;

import com.chatapplication.userdetails.User;

public interface LoginOrRegisterPageControllerToViewCall {

	void addedSuccessfully();

	void userMobileNoExist();

	void userEmailExist();

	void loginFailed();

	void loginSuccess(User user);

}
