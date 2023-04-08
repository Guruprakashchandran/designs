package com.chatapplication.personalchatlist;

public class Message {

	private String message;
	private String date;
	private String status;

	public Message(String message, String date, String status) {
		super();
		this.message = message;
		this.date = date;
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDate() {
		return date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
