package com.chatapplication.editprofile;

import com.chatapplication.userdetails.User;

public class ProfileController implements ProfileViewToControllerCall,ProfileModelToControllerCall{

	private ProfileControllerToViewCall profileControllerToViewCall;
	private ProfileControllerToModelCall profileControllerToModelCall;
	public ProfileController(ProfileView ProfileView) {
		
		profileControllerToViewCall = ProfileView;
		profileControllerToModelCall = new ProfileModel(this);
	}
	@Override
	public void changeAbout(User user,String newAbout) {
		
		profileControllerToModelCall.changeAbout(user,newAbout);
	}
	@Override
	public void changePassword(User user, String newPassword) {
	
		profileControllerToModelCall.changePassword(user,newPassword);
	}
	@Override
	public void changeName(User user, String newName) {
		
		profileControllerToModelCall.changeName(user,newName);
	}
	@Override
	public void changeDob(User user, String dob) {
		
		profileControllerToModelCall.changeDob(user,dob);
	}
	@Override
	public void closeConnection() {
		
		profileControllerToModelCall.closeConnection();
	}
}
