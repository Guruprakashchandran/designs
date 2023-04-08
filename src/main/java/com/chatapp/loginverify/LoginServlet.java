package com.chatapp.loginverify;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import com.chatapplication.loginorregisterpage.*;
import com.chatapplication.userdetails.User;

@WebServlet(urlPatterns = "/login/loginverify")
public class LoginServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String mobileNumber = req.getParameter("mobileNumber");
		String password = req.getParameter("password");
		LoginOrRegisterPageController controller = new LoginOrRegisterPageController(this);
		User user = new User(null,mobileNumber,null,0,null ,password,null);
		controller.checkLogin(user,res,req);
//		super.service(req, res);
//		System.out.println(mobileNumber+" "+password);
//		res.setContentType("application/json");
//		System.out.println("reach");
//		json.put("message", "Loginsuccess");
//		System.out.println(json.get("message"));
//		out.print(json.toString());
//		out.write(mobileNumber);
//		out.close();
	}

	public void loginFailed(String msg,HttpServletResponse res) {

		PrintWriter out = null;
		try {
			out = res.getWriter();			
			res.setContentType("application/json");
			JSONObject json = new JSONObject();
//			json.put("mobile",mobileNumber);
//			json.put("password",password);
			json.put("message", msg);
			out.append(json.toString());
		}
		catch(Exception e) {
			
			System.out.println("wrong Input");
		}
		finally {
			
			try {
				
				out.close();
			}
			catch(Exception e) {
				
				System.out.println("wrong Input");
			}
		}
	}

	public void loginSuccess(User user,HttpServletResponse res,HttpServletRequest req) {

		PrintWriter out = null;
		try {
			out = res.getWriter();			
			res.setContentType("application/json");
			JSONObject json = new JSONObject();
			json.put("message", "Login Success");
			HttpSession ses = req.getSession();
			ses.setAttribute("user", user);
			out.append(json.toString());
//			json.put("mobile",user.getMobileNo());
//			json.put("password",user.getPassword());
//			res.sendRedirect("ChatMainPage.html");
		}
		catch(Exception e) {
			
			System.out.println("wrong Input");
		}
		finally {
			
			try {
				
				out.close();
			}
			catch(Exception e) {
				
				System.out.println("wrong Input");
			}
		}
	}
}
