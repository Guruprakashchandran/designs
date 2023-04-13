package com.chatapplication.personalchatlist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.chatapplication.userdetails.User;

public class PersonalChatRepository{

	private static PersonalChatRepository normalChatRepository;
	private static Connection connection;
	private Statement st, st1;
	private ResultSet result, result1;
	private PreparedStatement prepareStatement;

	private PersonalChatRepository() {
		createConnection();
	}
	public void closeConnection() {
		
		connection = null;
		normalChatRepository = null;
	}
	private static Connection createConnection() {
	
		try {
			
			if(connection == null) {
				
				return DriverManager.getConnection("jdbc:mysql://localhost:3306/task1", "root", "root");
			}
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		return connection;
	}

	public static PersonalChatRepository getInstance() {

		if (normalChatRepository == null) {

			normalChatRepository = new PersonalChatRepository();
		}
		if(connection == null) {
			
			connection = createConnection();
		}
		return normalChatRepository;
	}

	public List<List<Map<String, List<Message>>>> getPeresonalChats(User user, List<String> friendsName,int[] count) {

		try {

//			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/task1", "root", "root");
			st = connection.createStatement();
			System.out.println("reacheddddd");
			result = st.executeQuery("select * from personalchat where person1mobileno ='" + user.getMobileNo()
					+ "' or person2mobileno = '" + user.getMobileNo() + "'");
			List<List<Map<String, List<Message>>>> data = new LinkedList<>();
			while (result.next()) {

				List<Map<String, List<Message>>> data1 = new LinkedList<>(); 
				String mobile1 = result.getString("person1mobileno");
				String mobile2 = result.getString("person2mobileno");
				Map<String, List<Message>> personsMap = null;

				if (mobile1.equals(user.getMobileNo())) {
					friendsName.add(result.getString("person2name"));
					data1.add(process(user, data1, personsMap, result));
				} else if (mobile2.equals(user.getMobileNo())) {
					friendsName.add(result.getString("person1name"));
					data1.add(process1(user, data1, personsMap, result));
				}
				data.add(data1);
			}
			st = connection.createStatement();
			result = st.executeQuery("select count(*) from register");
			result.next();
			count[0] = result.getInt("count(*)");
			return data;

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {

//				connection.close();
				st.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return null;
	}

	private Map<String, List<Message>> process1(User user, List<Map<String, List<Message>>> data,
			Map<String, List<Message>> persons, ResultSet result2) {

		try {

			List<Message> list = new LinkedList<>();
			List<Message> list1 = new LinkedList<>();
			String loginerId = result.getString("person2id");
			String personId = result.getString("person1id");

			st1 = connection.createStatement();
			result1 = st1.executeQuery(
					"select * from messages where id ='" + loginerId + "' and personid = '" + personId + "'");
			while (result1.next()) {

				String msg = result1.getString("message");
				Timestamp date = result1.getTimestamp("dateandtime");
				String s = date.toString().substring(0,19);
				String status = result1.getString("status");
				Message message = new Message(msg, s,status);
				list.add(message);
			}
			result1.close();
			st1.close();
			st1 = connection.createStatement();
			result1 = st1.executeQuery(
					"select * from messages where id ='" + personId + "' and personid = '" + loginerId + "'");
			while (result1.next()) {

				String msg = result1.getString("message");
				Timestamp date = result1.getTimestamp("dateandtime");
				String s = date.toString().substring(0,19);
				String status = result1.getString("status");
				Message message = new Message(msg, s,status);
				list1.add(message);
			}
			persons = new LinkedHashMap<>();
			persons.put(loginerId, list);
			persons.put(personId, list1);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {
//				connection.close();
				st1.close();
				result1.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return persons;
	}

	private Map<String, List<Message>> process(User user, List<Map<String, List<Message>>> data,
			Map<String, List<Message>> persons, ResultSet result2) {
		try {
			
			List<Message> list = new LinkedList<>();
			List<Message> list1 = new LinkedList<>();
			String loginerId = result.getString("person1id");
			String personId = result.getString("person2id");
			st1 = connection.createStatement();
			result1 = st1.executeQuery(
					"select * from messages where id ='" + loginerId + "' and personid = '" + personId + "'");
			while (result1.next()) {

				String msg = result1.getString("message");
				Timestamp date = result1.getTimestamp("dateandtime");
				String s = date.toString().substring(0,19);
				String status = result1.getString("status");
				Message message = new Message(msg, s,status);
				list.add(message);
			}
			st1 = connection.createStatement();
			result1 = st1.executeQuery(
					"select * from messages where id ='" + personId + "' and personid = '" + loginerId + "'");
			while (result1.next()) {

				String msg = result1.getString("message");
				Timestamp date = result1.getTimestamp("dateandtime");
				String s = date.toString().substring(0,19);
				String status = result1.getString("status");
				st1 = connection.createStatement();
				Message message = new Message(msg, s,status);
				list1.add(message);
			}
			persons = new LinkedHashMap<>();
			persons.put(loginerId, list);
			persons.put(personId, list1);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {
//				connection.close();
				st1.close();
				result1.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return persons;
	}

	public int addFriend(User user, String newNumber) {

		try {

//			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/task1", "root", "root");
			st = connection.createStatement();
			result = st.executeQuery("select * from register where mobileno = '"+ newNumber+"'");
			if(result.next()) {
				
				st1 = connection.createStatement();
				result1 = st1.executeQuery("select * from personalchat where person1mobileno = '"+user.getMobileNo()+"' and person2mobileno = '"+newNumber+"'");
				if(result1.next()) {
					
					return 2;
				}
				else {
					
					st1 = connection.createStatement();
					result1 = st1.executeQuery("select * from personalchat where person2mobileno = '"+user.getMobileNo()+"' and person1mobileno = '"+newNumber+"'");
					if(result1.next()) {
						
						return 2;
					}
					else {
						

						st1 = connection.createStatement();
						result1= st1.executeQuery("select * from register where mobileno = '"+user.getMobileNo()+"'");
						prepareStatement = connection.prepareStatement("insert into personalchat (person1id,person1mobileno,person1name,person2id,person2mobileno,person2name) values(?,?,?,?,?,?)");
						result1.next();
						prepareStatement.setString(3, result1.getString("name"));
						prepareStatement.setString(1, result1.getString("id"));
						prepareStatement.setString(2, user.getMobileNo());
						st1 = connection.createStatement();
						result1= st1.executeQuery("select * from register where mobileno = '"+newNumber+"'");
						result1.next();
						prepareStatement.setString(6, result1.getString("name"));
						prepareStatement.setString(4, result1.getString("id"));
						prepareStatement.setString(5, newNumber);
						if(prepareStatement.executeUpdate()>0) {
						
							prepareStatement.close();
							return 0;
						}
						else {
							prepareStatement.close();
							return 3;
						}
					}
				}
			}
			else {
				
				return 1;
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {
//				connection.close();
				st1.close();
				result1.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return 0;
	}

	public void addMessage(User user, String personId, String msg,String loginerId, String status) {
		
		try {
			
//			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/task1", "root", "root");
			prepareStatement = connection.prepareStatement("insert into messages(id,message,personId,status) values(?,?,?,?)");
			prepareStatement.setString(1, loginerId);
			prepareStatement.setString(2, msg);
			prepareStatement.setString(3, personId);
			prepareStatement.setString(4, "Not Viewed");
			prepareStatement.executeUpdate();
		}catch(Exception e) {
			
			e.printStackTrace();
		}finally {
			try{	
	
//				connection.close();
				prepareStatement.close();
			}catch(SQLException e) {
				
				e.printStackTrace();
			}
		}
	}

	public void setMessageViewed(String status, String loginerId) {
		
		try {
			
//			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/task1", "root", "root");
			prepareStatement = connection.prepareStatement("update messages set status = ? where personid = ?");
			prepareStatement.setString(1,status);
			prepareStatement.setString(2, loginerId);
			prepareStatement.executeUpdate();

		}catch(Exception e) {
			
			e.printStackTrace();
		}finally {
			
			try{
			
//				connection.close();
				prepareStatement.close();
			}catch(SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
}
