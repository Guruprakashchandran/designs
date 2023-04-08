package com.chatapplication.editprofile;

import com.chatapplication.userdetails.User;
import com.chatapplication.userdetails.UserDetailsRepository;

public class ProfileModel implements ProfileControllerToModelCall{

	private ProfileModelToControllerCall profileModelToControllerCall;
	public ProfileModel(ProfileController profileController) {

		profileModelToControllerCall  = profileController;
	}
	@Override
	public void changeAbout(User user, String newAbout) {
		
		UserDetailsRepository.getInstance().changeAbout(user,newAbout);
	}
	@Override
	public void changePassword(User user, String newPassword) {
		
		UserDetailsRepository.getInstance().changePassword(user,newPassword);
	}
	@Override
	public void changeName(User user, String newName) {
		
		UserDetailsRepository.getInstance().changeName(user,newName);
	}
	@Override
	public void changeDob(User user, String dob) {
		
		UserDetailsRepository.getInstance().changeDob(user,dob);
	}
	@Override
	public void closeConnection() {
		
		UserDetailsRepository.getInstance().closeConnection();
	}
}
