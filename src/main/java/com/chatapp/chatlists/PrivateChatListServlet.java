package com.chatapp.chatlists;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
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
		
		PersonalChatController personalChatController= new PersonalChatController(this);
		try {
			if(req.getServletPath().equals("/personalchat")) {
				
				HttpSession ses = req.getSession();
				User user = (User) ses.getAttribute("user");
				personalChatController.getPersonalChats(user,req,res);
			}
			else if(req.getServletPath().equals("/message")) {
				
				try {

					String personId = req.getParameter("personId");
					String msg = req.getParameter("message");
//					System.out.println(personId+" "+msg);
					HttpSession ses = req.getSession();
					User user = (User) ses.getAttribute("user");
					personalChatController.addMessage(user, personId, msg, user.getId(), "NotViewed");
				} catch (Exception e) {

					System.out.println("Didn't Reached!!!");
				} finally {

//					out.close();
				}

			}
		}
		catch(Exception e) {
			
			System.out.println();
		}
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
			userDetails.put("dob", user.getDateOfBirth());
			userDetails.put("about", user.getAbout());
			obj.put("userDetails", userDetails);
			JSONArray parentArray = new JSONArray();
			JSONArray personId = new JSONArray();
			for(int i = 0;i<data.size();++i) {
			
				JSONArray secParent = new JSONArray();
				for(int j = 0;j<data.get(i).size();++j) {
					
					JSONObject jsonObj = new JSONObject();
					int l = 0;
					for(Entry<String, List<Message>> entry : data.get(i).get(j).entrySet()) {
					
						JSONArray child = new JSONArray();
						if(entry.getKey().startsWith("pn")) {
							
							if(!personId.contains(entry.getKey())) {
								
								personId.add(entry.getKey());
							}
						}
						for(int k = 0;k<entry.getValue().size();++k) {
							
							JSONObject details = new JSONObject();
							details.put("date", entry.getValue().get(k).getDate().substring(0,19));
							details.put("msg", entry.getValue().get(k).getMessage());
							details.put("status", entry.getValue().get(k).getStatus());
							child.add(details);
						}
						jsonObj.put((l++ == 0? "userdetail":"friend"),child);
					}
					secParent.add(jsonObj);
				}
				parentArray.add(secParent);
			}
			obj.put("messages", parentArray);
			obj.put("personId", personId);
//			System.out.println(obj);
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
