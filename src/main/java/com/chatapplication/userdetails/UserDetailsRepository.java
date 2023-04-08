package com.chatapplication.userdetails;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class UserDetailsRepository {

	private static UserDetailsRepository userDetailsRepository;
	private Map<String, User> userDetails = new LinkedHashMap<>();
	private static Connection connection = null;// = createConnection();
	private Statement st = null;
	private PreparedStatement prepareStatement = null;
	private ResultSet result = null;

	private UserDetailsRepository() {

//		try {
//
//			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/task1", "root", "root");
//		} catch (Exception e) {
//		}
	}

	public void closeConnection() {

		connection = null;
	}

	private static Connection createConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/task1", "root", "root");
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	public static UserDetailsRepository getInstance() {

		if (userDetailsRepository == null) {

			userDetailsRepository = new UserDetailsRepository();
		}
		if (connection == null) {

			connection = createConnection();
		}
		return userDetailsRepository;
	}

	public List<User> getDetails() {

		List<User> users = new LinkedList<>();
		for (Map.Entry<String, User> entry : userDetails.entrySet()) {

			users.add(entry.getValue());
		}
		return users;
	}

	public boolean addDetails(User user) {

		try {

//			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/task1", "root", "root");
//			Class.forName("com.mysql.jdbc.Driver");
			System.out.println(user.getMobileNo());
			st = connection.createStatement();
			result = st.executeQuery("select * from register where mobileno = '" + user.getMobileNo()
					+ "' or emailId = '" + user.getEmailId() + "'");
			if (result.next()) {

				return false;
			} else {
				result.close();
				st.close();
				st = connection.createStatement();
				result = st.executeQuery("select count(*) from register");
				result.next();
				int rowcount = result.getInt("count(*)");
				String id = "pn";
				int length = id.length();
				++rowcount;
				for (int i = 0; i + length + Integer.toString(rowcount).length() < 10; ++i) {

					id += '0';

				}
				id += Integer.toString(rowcount);
				prepareStatement = connection.prepareStatement(
						"insert into register(mobileno,emailid,name,dateofbirth,age,about,password,id) values(?,?,?,?,?,?,?,?)");
				prepareStatement.setString(1, user.getMobileNo());
				prepareStatement.setString(2, user.getEmailId());
				prepareStatement.setString(3, user.getName());
				prepareStatement.setString(4, user.getDateOfBirth());
				prepareStatement.setInt(5, user.getAge());
				prepareStatement.setString(6, user.getAbout());
				prepareStatement.setString(7, user.getPassword());
				prepareStatement.setString(8, id);
				if (prepareStatement.executeUpdate() > 0) {

					return true;
				} else {

					return false;
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {
//				connection.close();
				st.close();
				result.close();
//				prepareStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public User checkLogin(User user, String[] msg) {

		User userDetails = null;
		try {

//			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/task1", "root", "root");
			st = connection.createStatement();
			result = st.executeQuery("select * from register where mobileno = '" + user.getMobileNo() + "'");
			if (result.next()) {

				String pass = result.getString("password");
				if (pass.equals(user.getPassword())) {

					userDetails = new User(result.getString("name"), result.getString("mobileno"),
							result.getString("emailid"), result.getInt("age"), result.getString("dateofbirth"),
							result.getString("password"), result.getString("about"));
					return userDetails;

				}
					msg[0] = "Password Incorrect";
					return userDetails;
			}	
			msg[0] = "Mobile Number Incorrect";
//			if(connection != null) {
//				
//				connection.close();
//			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {

//				connection.close();
				st.close();
				result.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return userDetails;
	}

	public void changeAbout(User user, String newAbout) {

		try {

			prepareStatement = connection.prepareStatement("update register set about = ? where mobileno = ?");
			prepareStatement.setString(1, newAbout);
			prepareStatement.setString(2, user.getMobileNo());
			prepareStatement.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {

				prepareStatement.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

	}

	public void changePassword(User user, String newPassword) {

		try {

			prepareStatement = connection.prepareStatement("update register set password = ? where mobileno = ?");
			prepareStatement.setString(1, newPassword);
			prepareStatement.setString(2, user.getMobileNo());
			prepareStatement.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {

				prepareStatement.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

	public void changeName(User user, String newName) {

		try {

			prepareStatement = connection.prepareStatement("update register set name = ? where mobileno = ?");
			prepareStatement.setString(1, newName);
			prepareStatement.setString(2, user.getMobileNo());
			prepareStatement.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {

				prepareStatement.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

	public void changeDob(User user, String dob) {

		try {

			prepareStatement = connection.prepareStatement("update register set dateofbirth = ? where mobileno = ?");
			prepareStatement.setString(1, dob);
			prepareStatement.setString(2, user.getMobileNo());
			prepareStatement.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {

				prepareStatement.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}
}
