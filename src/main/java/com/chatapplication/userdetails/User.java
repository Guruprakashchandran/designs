package com.chatapplication.userdetails;

public class User {

	private String name;
	private String mobileNo;
	private String emailId;
	private int age;
	private String dateOfBirth;
	private String password;
	private String about;
	
	public User(String name, String mobileNo, String emailId, int age, String dateOfBirth, String password,
			String about) {
		super();
		this.name = name;
		this.mobileNo = mobileNo;
		this.emailId = emailId;
		this.age = age;
		this.dateOfBirth = dateOfBirth;
		this.password = password;
		this.about = about;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
}
