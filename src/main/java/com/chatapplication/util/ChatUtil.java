package com.chatapplication.util;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatUtil {

	public static boolean checkName(String name) {
		
		for(int i = 0;i<name.length();++i) {
			
			if(name.charAt(i) < 'A' || (name.charAt(i) > 'z' &&name.charAt(i)<'a') || name.charAt(i)>'z' ) {
				
				return false;
			}
		}
		return true;
	}
//	public static void main(String[] args) {
//		
//		System.out.println("hiii");
//		checkAge("12/02/2000");
//	}
	public static boolean checkMobileNumber(String mobileNo) {
		
		Pattern p = Pattern.compile("^\\d{10}$");
		Matcher m = p.matcher(mobileNo);
		return (m.matches());
	}

	public static boolean checkEmailId(String emailId) {
		
		Pattern p = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
		Matcher m = p.matcher(emailId);
		return (m.matches());
	}

	public static int checkAge(String dateOfBirth) {
		
		String[] userDetails = dateOfBirth.split("/");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
		LocalDateTime now = LocalDateTime.now();  
		String currDate = dtf.format(now);
		String[] details = currDate.split("/");
		if(userDetails.length != 3) {
			
			return 0;
		}
		if(Integer.parseInt(details[2]) < Integer.parseInt(userDetails[2])+18) {
			
			return 0;
		}
		else if(Integer.parseInt(details[2]) > Integer.parseInt(userDetails[2])+18){
			
			return Integer.parseInt(details[2]) - Integer.parseInt(userDetails[2]);
		}
		else {
			
			if(Integer.parseInt(details[1]) < Integer.parseInt(userDetails[1])) {
				
				return 0;
			}
			else if(Integer.parseInt(details[1]) > Integer.parseInt(userDetails[1])){
				
				return 18;
			}
			else {
				
				if(Integer.parseInt(details[0]) < Integer.parseInt(userDetails[0])) {
					
					return 0;
				}
				else if(Integer.parseInt(details[0]) >= Integer.parseInt(userDetails[0])) {
					
					return 18;
				}
				else {
					
					return 18;
				}
			}
		}
	}

	public static boolean checkPasswordLength(String password) {
		
//		if(password.length() < 8) {
//			
//			return false;
//		}
//		return true;
		return password.length() < 8 ? false: true;
	}
	public static boolean checkPassword(String password, String conPassword) {
		
//		if(password.length() < 8 || !password.equals(conPassword)) {
//			
//			return false;
//		}
//		return true;
		return password.equals(conPassword) ? true : false;
	}

	public static boolean checkDate(String date) {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
		LocalDateTime now = LocalDateTime.now();  
		String currDate = dtf.format(now);
//		System.out.println(currDate);
		String[] date1 = date.split("/");
		String[] currDate1 = currDate.split("/");
		boolean isTrue = true;
		for(int i = 2;i>=0;--i) {
			
			if(Integer.parseInt(date1[i])< Integer.parseInt(currDate1[i])) {
				
					isTrue = false;
					break;
			}
			else if(Integer.parseInt(date1[i]) > Integer.parseInt(currDate1[i])){
				
				break;
			}
		}	
		return isTrue;
	}

	public static boolean checkDateOfBirth(String dateOfBirth) {
		
		String[] userDetails = dateOfBirth.split("/");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
		LocalDateTime now = LocalDateTime.now();  
		String currDate = dtf.format(now);
		String[] details = currDate.split("/");
		if(userDetails.length != 3) {
			
			return false;
		}
		if(Integer.parseInt(details[2]) < Integer.parseInt(userDetails[2])+18) {
			
			return false;
		}
		else if(Integer.parseInt(details[2]) > Integer.parseInt(userDetails[2])+18){
			
			return true;
		}
		else {
			
			if(Integer.parseInt(details[1]) < Integer.parseInt(userDetails[1])) {
				
				return false;
			}
			else if(Integer.parseInt(details[1]) > Integer.parseInt(userDetails[1])){
				
				return true;
			}
			else {
				
				if(Integer.parseInt(details[0]) < Integer.parseInt(userDetails[0])) {
					
					return false;
				}
				else if(Integer.parseInt(details[0]) >= Integer.parseInt(userDetails[0])) {
					
					return true;
				}
			}
		}
		return true;
	}

	public static boolean checkPassword(String password) {
		
		if(password.length() < 8 ) {
			
			return false;
		}
		return true;
	}
}
