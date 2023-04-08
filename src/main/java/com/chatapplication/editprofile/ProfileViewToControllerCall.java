package com.chatapplication.editprofile;

import com.chatapplication.userdetails.User;

public interface ProfileViewToControllerCall {

	void changeAbout(User user, String newAbout);

	void changePassword(User user, String newPassword);

	void changeName(User user, String newName);

	void changeDob(User user, String dob);

	void closeConnection();

}
