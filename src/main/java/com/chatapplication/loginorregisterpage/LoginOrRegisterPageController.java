package com.chatapplication.loginorregisterpage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chatapp.loginverify.LoginServlet;
//import com.chatapp.loginverify.LoginServlet;
import com.chatapplication.userdetails.User;

public class LoginOrRegisterPageController implements LoginOrRegisterPageModelToControllerCall{

	private LoginOrRegisterPageControllerToViewCall loginOrRegisterPageControllerToViewCall;
	private LoginOrRegisterPageControllerToModelCall loginOrRegisterPageControllerToModelCall;
	private LoginServlet login;
	public LoginOrRegisterPageController(LoginServlet loginServlet) {
		
		login = loginServlet;
		loginOrRegisterPageControllerToModelCall = new LoginOrRegisterPageModel(this);
	}
//	@Override
//	public void setUserDetails(User user) {
//		
//		loginOrRegisterPageControllerToModelCall.setUserDetails(user);
//	}
	@Override
	public void userMobileNoExist() {
		
		loginOrRegisterPageControllerToViewCall.userMobileNoExist();
	}
	@Override
	public void addedSuccessfully() {
		
		loginOrRegisterPageControllerToViewCall.addedSuccessfully();
	}
	@Override
	public void userEmailExist() {
		
		loginOrRegisterPageControllerToViewCall.userEmailExist();
	}
	public void checkLogin(User user, HttpServletResponse res, HttpServletRequest req) {
	
		loginOrRegisterPageControllerToModelCall.checkLogin(user,res,req);
	}
	@Override
	public void loginFailed(String msg,HttpServletResponse res) {
		
		login.loginFailed(msg,res);
	}
	@Override
	public void loginSuccess(User user,HttpServletResponse res,HttpServletRequest req) {
		
		login.loginSuccess(user,res,req);
	}
//	@Override
//	public void closeConnection() {
//	
//		loginOrRegisterPageControllerToModelCall.closeConnection();
//	}
}
