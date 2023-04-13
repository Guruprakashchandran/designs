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

import com.chatapplication.groupchat.GroupChatController;
import com.chatapplication.groupchatlist.Messages;
import com.chatapplication.userdetails.User;

public class GroupChatListsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		GroupChatController groupChatController = new GroupChatController(this);
		if (req.getServletPath().equals("/groupchatlist")) {

			HttpSession ses = req.getSession();
			User user = (User) ses.getAttribute("user");
//			System.out.println(user.getMobileNo());
			groupChatController.getGroupChatDetails(user, req, res);
		} else if (req.getServletPath().equals("/messages")) {

			try {

				String groupId = req.getParameter("groupId");
				String msg = req.getParameter("message");
				System.out.println(groupId+" "+msg);
				HttpSession ses = req.getSession();
				User user = (User) ses.getAttribute("user");
				groupChatController.addMessage(user, user.getId(), msg, groupId, "NotViewed");
			} catch (Exception e) {

				System.out.println("Didn't Reached!!!");
			} finally {

//				out.close();
			}
		}
	}

	public void showDetails(List<List<Map<String, List<Messages>>>> data, User user, List<String> groupNames,
			int[] friendsCount, List<List<Integer>> groupMembersCount, HttpServletRequest req,
			HttpServletResponse res) {

		PrintWriter out = null;
		try {

			out = res.getWriter();
			res.setContentType("application/json");
			JSONObject obj = new JSONObject();
//			JSONObject obj1 = new JSONObject();
//			System.out.println(obj1.put("guru",data).toString());
			obj.put("groupNames", groupNames);
			JSONArray jsonArray = new JSONArray();
			JSONArray groupIds = new JSONArray();
			String userId = "";
			for (int i = 0; i < data.size(); ++i) {

				JSONArray jsonArr = new JSONArray();
				for (int j = 0; j < data.get(i).size(); ++j) {

					JSONObject jsonObject = new JSONObject();
					int k = 0;
					for (Entry<String, List<Messages>> entry : data.get(i).get(j).entrySet()) {

						JSONArray arr = new JSONArray();
//						System.out.println("start---");
						if (entry.getKey().startsWith("gc")) {

							if (!groupIds.contains(entry.getKey())) {

								groupIds.add(entry.getKey());
							}
						} else {

							userId = entry.getKey();
						}
						for (int l = 0; l < entry.getValue().size(); ++l) {

							JSONObject details = new JSONObject();

//							System.out.println(entry.getValue().toString());
//							System.out.println();
							details.put("message", entry.getValue().get(l).getMessage());
							details.put("status", entry.getValue().get(l).getStatus());
							details.put("personId", entry.getValue().get(l).getPersonId());
							details.put("mobileno", entry.getValue().get(l).getMobileNo());
							details.put("date", entry.getValue().get(l).getDate().substring(0,19).toString());
							details.put("name", entry.getValue().get(l).getName());

							arr.add(details);
//							System.out.println(details);
							// System.out.println(entry.getValue().get(l).getMessage());
						}
						if (arr.size() != 0) {

							jsonObject.put((k == 0 ? "userdetail" : "group"), arr);
						}
						k++;
					}
					jsonArr.add(jsonObject);
				}
				jsonArray.add(jsonArr);
			}
//			System.out.println(jsonArray.toJSONString());
//			System.out.println(groupIds.toJSONString());
			obj.put("messages", jsonArray);
			obj.put("groupIds", groupIds);
			obj.put("userId", userId);
			out.print(obj);
		} catch (Exception e) {

			System.out.println("Response Didn't send to Client!!!");
		} finally {

			out.close();
		}
	}
}
