package com.chatapplication.groupchatlist;

public class Messages {

	private String message;
	private String status;
	private String date;
	private String personId;
	private String name;
	private String mobileNo;

	public Messages(String message, String status, String date, String personId, String name, String mobileNo) {
		super();
		this.message = message;
		this.status = status;
		this.date = date;
		this.personId = personId;
		this.name = name;
		this.mobileNo = mobileNo;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

}
