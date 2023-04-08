package com.chatapplication.editprofile;

import java.util.Scanner;

import com.chatapplication.userdetails.User;
import com.chatapplication.util.ChatUtil;

public class ProfileView implements ProfileControllerToViewCall{

	private ProfileViewToControllerCall profileViewToControllerCall;
	private Scanner scan = new Scanner(System.in);
	public ProfileView() {
		
		profileViewToControllerCall = new ProfileController(this);
	}
	public void profilePage(User user) {
		
		System.out.println("----------Profile Page-------------");
		System.out.println("\nName - "+user.getName());
		System.out.println("Mobile No - "+user.getMobileNo());
		System.out.println("Email Id - "+user.getEmailId());
		System.out.println("Date Of Birth - "+user.getDateOfBirth());
		System.out.println("Age - "+user.getAge());
		System.out.println("About - "+user.getAbout());
		System.out.println("Password - "+user.getPassword());
		editProfile(user);
	}

	private void editProfile(User user) {
		
		try {
			
			boolean isIterate = true;
			while(isIterate) {
				
				System.out.println("\n-------Edit Profile---------");
				System.out.println("\n1) Change Name");
				System.out.println("2) Change Date Of Birth");
				System.out.println("3) Change Password");
				System.out.println("4) Change About");
				System.out.println("5) Back\n");
				
				System.out.print("Choose Category : ");
				int input = scan.nextInt();
				switch(input) {
				
					case 1:
						changeName(user);
						break;
					case 2:
						changeDob(user);
						break;
					case 3:
						changePassword(user);
						break;
					case 4:
						changeAbout(user);
						break;
					case 5: 
						profileViewToControllerCall.closeConnection();
						isIterate = false;
						break;
					default:
						System.out.println("Wrong Input!!!");
				}
			}

		}
		catch(Exception e) {
			
			scan.nextLine();
			System.out.println("WrongInput!!!");
		}
	}
	private void changeDob(User user) {

		scan.nextLine();
		System.out.print("\nEnter New Date Of Birth (dd/MM/yyyy): ");
		String dob = scan.next();
		if(ChatUtil.checkDateOfBirth(dob)) {
			
			profileViewToControllerCall.changeDob(user,dob);
			user.setDateOfBirth(dob);
			System.out.println("\nDate Of Birth Changed Successfully");
		}
		else {
			
			System.out.println("Wrong Date Of Birth!!!");
		}
	}
	private void changeName(User user) {
		
		scan.nextLine();
		System.out.print("\nEnter New Name : ");
		String newName = scan.nextLine();
		if(ChatUtil.checkName(newName)) {
			
			profileViewToControllerCall.changeName(user,newName);
			user.setName(newName);
			System.out.println("\nName Changed Successfully!!!");
		}
		else {
			
			System.out.println("Wrong Name!!!");
		}
	}
	private void changePassword(User user) {
		
		scan.nextLine();
		System.out.print("\nEnter Old Password : ");
		String oldPassword = scan.next();
		if(ChatUtil.checkPasswordLength(oldPassword)) {
			
			if(user.getPassword().equals(oldPassword)) {
				
				String newPassword = getNewPassword(user);
				profileViewToControllerCall.changePassword(user,newPassword);
//				profileViewToControllerCall.changePassword(user,newPassword);
				user.setPassword(newPassword);
				System.out.println("\nPassword Changed Successfully!!!");
			}
		}
		else {
			
			System.out.println("Wrong Password!!!");
		}
	}
	private String getNewPassword(User user) {
		
		System.out.print("\nEnter New Password (Minimum 8 Characters): ");
		String newPassword = scan.next();
		if(ChatUtil.checkPasswordLength(newPassword)) {
			
			
			newPassword = (getConfirmPassword(user,newPassword)) ? getNewPassword(user) : newPassword;
			return newPassword;
		}
		else {
			
			System.out.println("Minimum 8 Characters!!!");
			newPassword = getNewPassword(user);
		}
		return newPassword;
	}
	private boolean getConfirmPassword(User user, String newPassword) {

		System.out.print("\nEnter Confirm Password : ");
		String conPassword = scan.next();
		if(ChatUtil.checkPassword(newPassword, conPassword)) {
			
			return false;
		}
		else {
			
			System.out.println("Input MisMatch!!!");
			return true;
		}
	}
	private void changeAbout(User user) {
		
		scan.nextLine();
		System.out.print("\nEnter New About : ");
		String newAbout = scan.nextLine();
		profileViewToControllerCall.changeAbout(user,newAbout);
		user.setAbout(newAbout);
		System.out.println("\nAbout Changed Successfully!!!");
	}
}
