package com.chatapp.chatlists;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.chatapplication.personalchat.PersonalChatController;
import com.chatapplication.personalchatlist.Message;
import com.chatapplication.userdetails.User;

public class PrivateChatListServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		try {
		
			HttpSession ses = req.getSession();
			User user = (User) ses.getAttribute("user");
			new PersonalChatController().getPersonalChats(user,req,res);
		}
		catch(Exception e) {}
	}

	public void showContacts(List<List<Map<String, List<Message>>>> data, User user, List<String> friendsName,
			int[] totalRegisterCount, HttpServletRequest req, HttpServletResponse res) {
		
		PrintWriter out = null;
		try {
		
			res.setContentType("application/json");
			JSONObject obj = new JSONObject();
			obj.put("friendsName", friendsName);
			JSONObject userDetails = new JSONObject();
			userDetails.put("name", user.getName());
			userDetails.put("mobileno", user.getMobileNo());
			userDetails.put("emailid", user.getEmailId());
			obj.put("userDetails", userDetails);
			out = res.getWriter();
			out.print(obj);
		}
		catch(Exception e) {
			
			System.out.println("Wrong Input!!!");
		}
		finally {
			 
			out.close();
		}
	}
}
