package com.chatapplication.groupchatlist;

import java.sql.Timestamp;
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

import com.chatapplication.userdetails.User;

public class GroupChatRepository {

	private static GroupChatRepository groupChatRepository;
	private Connection connection = null;// = UserDetailsRepository.getInstance().createConnection();
	private Statement st, st1, st2;
	private ResultSet result, result1, result2;
	private PreparedStatement prepareStatement;

	private GroupChatRepository() {
		createConnection();
	}

	public void closeConnection() {

		connection = null;
		groupChatRepository = null;
	}

	private Connection createConnection() {

		try {

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/task1", "root", "root");
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	public static GroupChatRepository getInstance() {

		if (groupChatRepository == null) {

			groupChatRepository = new GroupChatRepository();
		}
		return groupChatRepository;
	}

	public List<List<Map<String, List<Messages>>>> getGroupChatDetails(User user, List<String> groupNames,
			int[] friendsCount, List<List<Integer>> groupMembersCount) {

		try {

//			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/task1", "root", "root");
			st = connection.createStatement();
			List<List<Map<String, List<Messages>>>> data = new LinkedList<>();
			List<Map<String, List<Messages>>> data1 = new LinkedList<>();
			result = st.executeQuery("select * from groupchat where personmobileno = '" + user.getMobileNo() + "'");
			String groupId = "";
			while (result.next()) {

				try {
//					.out.println(result.getString("groupid"));
//					connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/task1", "root", "root");
					String personId = result.getString("personid");
					groupId = result.getString("groupId");
					st1 = connection.createStatement();
					result1 = st1.executeQuery("select * from messages where id = '" + groupId + "'");
					Map<String, List<Messages>> persons = new LinkedHashMap<>();
					List<Messages> list1 = new LinkedList<>();
					List<Messages> list2 = new LinkedList<>();
					data.add(getMessageFromOneGroup(result1, personId, list1, list2, persons, data1, groupId,
							groupNames, friendsCount, groupMembersCount, user));
				} catch (Exception e) {

					e.printStackTrace();
				}
//				finally {
//					
//				}
			}
			return data;
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			try {

//				connection.close();
//				result.close();
				st.close();
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		return null;

	}

	private List<Map<String, List<Messages>>> getMessageFromOneGroup(ResultSet result3, String personId,
			List<Messages> list1, List<Messages> list2, Map<String, List<Messages>> persons,
			List<Map<String, List<Messages>>> data1, String groupId, List<String> groupNames, int[] friendsCount,
			List<List<Integer>> groupMembersCount, User user) {

		try {

			String groupName = "";
			st2 = connection.createStatement();
			result2 = st2.executeQuery("select count(*) from personalchat where person1id = '" + personId
					+ "' or person2id = '" + personId + "'");
			result2.next();
			friendsCount[0] = result2.getInt("count(*)");
			st2 = connection.createStatement();
			result2 = st2.executeQuery("select * from groupchat where groupid = '" + groupId + "'");
			if (result2.next()) {

				groupName = result2.getString("groupname");
			}
			st1 = connection.createStatement();
			result1 = st1.executeQuery("select * from personalchat where person1mobileno = '" + user.getMobileNo()
					+ "' or person2mobileno = '" + user.getMobileNo() + "'");
			List<Integer> groupMemberCount = new LinkedList<>();
			groupMemberCount.add(0);
			while (result1.next()) {

				if (result1.getString("person1mobileno").equals(user.getMobileNo())) {

					st2 = connection.createStatement();
					result2 = st2.executeQuery("select * from groupchat where groupid = '" + groupId
							+ "' and personmobileno = '" + result1.getString("person2mobileno") + "'");
					if (!result2.next()) {

						groupMemberCount.set(0,
								groupMemberCount.get(0) + 1);
					}
				} else {

					st2 = connection.createStatement();
					result2 = st2.executeQuery("select * from groupchat where groupid = '" + groupId
							+ "' and personmobileno = '" + result1.getString("person1mobileno") + "'");
					if (!result2.next()) {

						groupMemberCount.set(0,
								groupMemberCount.get(0) + 1);
					}
				}
			}
			while (result3.next()) {

				String personName = "", number = "";
				String status = result3.getString("status");
				Timestamp date = result3.getTimestamp("dateandtime");
				String msg = result3.getString("message");
				String id = result3.getString("personid");
				st2 = connection.createStatement();
				result2 = st2.executeQuery("select * from register where id = '" + id + "'");

				if (result2.next()) {

					personName = result2.getString("name");
					number = result2.getString("mobileno");
				}
				Messages message = new Messages(msg, status, date.toString(), personId, personName, number);

				if (personId.equals(id)) {

					list1.add(message);
				} else {

					list2.add(message);
				}
			}
			persons.put(personId, list1);
			persons.put(groupId, list2);
			groupNames.add(groupName);
			data1.add(persons);
//			System.out.println(groupMemberCount);
			groupMembersCount.add(groupMemberCount);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {

//				connection.close();

			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		return data1;
	}

	public int addGroup(User user, String groupName, String newNumber) {

		try {

//			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/task1", "root", "root");
			st = connection.createStatement();
			result = st.executeQuery("select * from register where mobileno = '" + user.getMobileNo() + "'");
			if (result.next()) {

				String groupId = generateGroupId();
				String userId = result.getString("id");
				String name = result.getString("personname");
				result = st.executeQuery("select * from groupchat where personid = '" + userId + "'");
				if (result.next()) {

					return 1;
				} else {

					addUser(groupId, userId, name, user, newNumber, groupName);
					return 0;
				}
			} else {
				return 2;
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {

//				connection.close();
				result.close();
				st.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return 0;
	}

	private void addUser(String groupId, String userId, String name, User user, String newNumber, String groupName) {

		try {

//			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/task1", "root", "root");
			prepareStatement = connection.prepareStatement(
					"insert into groupchat(groupid,groupname,personid,personname,personmobileno) values(?,?,?,?,?)");
			prepareStatement.setString(1, groupId);
			prepareStatement.setString(2, groupName);
			prepareStatement.setString(3, userId);
			prepareStatement.setString(4, name);
			prepareStatement.setString(5, newNumber);
			prepareStatement.executeUpdate();
			return;
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

	public int createGroup(User user, String groupName, List<String> mobileNoList) {

		try {

			st = connection.createStatement();
			String grpId = generateGroupId();
			addPerson(grpId, groupName, user.getMobileNo());
			int notFriendsCount = 0;
			for (int i = 0; i < mobileNoList.size(); ++i) {

				try {

					st1 = connection.createStatement();
					result1 = st1.executeQuery("select * from personalchat where person1mobileno = '" + user
							+ "' and person2mobileno = '" + mobileNoList.get(i) + "'");
					if (result1.next()) {

						addPerson(grpId, groupName, mobileNoList.get(i));
					} else {

						st1 = connection.createStatement();
						result1 = st1.executeQuery("select * from personalchat where person2mobileno = '" + user
								+ "' and person1mobileno = '" + mobileNoList.get(i) + "'");
						if (result1.next()) {

							addPerson(grpId, groupName, mobileNoList.get(i));
						} else {

							notFriendsCount++;
						}
					}
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
			return notFriendsCount;
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
//				connection.close();
				prepareStatement.close();
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		return 0;
	}

	private void addPerson(String grpId, String groupName, String mobileNo) {

		try {

			st2 = connection.createStatement();
			result2 = st2.executeQuery("select * from register where mobileno ='" + mobileNo + "'");
			if (result2.next()) {

				String name = result2.getString("name");
				String id = result2.getString("id");
				prepareStatement = connection.prepareStatement(
						"insert into groupchat(groupid,groupname,personid,personname,personmobileno)values(?,?,?,?,?)");
				prepareStatement.setString(1, grpId);
				prepareStatement.setString(2, groupName);
				prepareStatement.setString(3, id);
				prepareStatement.setString(4, name);
				prepareStatement.setString(5, mobileNo);
				prepareStatement.executeUpdate();
			}
		} catch (Exception e) {

			System.out.println("\nDidn't Add person!!");
		} finally {

			try {

				result2.close();
				st2.close();
				prepareStatement.close();
			} catch (SQLException e) {

				System.out.println("\nDidn't Add person!!");
			}
		}
	}

	private String generateGroupId() {

		try {

			st = connection.createStatement();
			result = st.executeQuery("select count(distinct groupid) from groupchat");
			result.next();
			int count = result.getInt("count(distinct groupid)");
			String st = "gc";
			int length = st.length();
			++count;
			for (int i = 0; i + length + Integer.toString(count).length() < 10; ++i) {

				st += '0';

			}
			st += Integer.toString(count);
			return st;
		} catch (Exception e) {

			System.out.println("\nDidn't generate group Id!!");
		} finally {

			try {

//				connection.close();
				st.close();
				result.close();
			} catch (SQLException e) {

				System.out.println("\nDidn't Add data!!");
			}
		}

		return null;
	}

	public Map<String, String> getFriendsList(User user) {

		try {

			st = connection.createStatement();
			result = st.executeQuery("select * from personalchat where person1mobileno = '" + user.getMobileNo() + "'");
			Map<String, String> friendList = new LinkedHashMap<>();
			while (result.next()) {

				friendList.put(result.getString("person2mobileNo"), result.getString("person2name"));
			}
			st = connection.createStatement();
			result = st.executeQuery("select * from personalchat where person2mobileno = '" + user.getMobileNo() + "'");
			while (result.next()) {

				friendList.put(result.getString("person1mobileNo"), result.getString("person1name"));
			}
//			System.out.println(friendList.toString());
			return friendList;
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {

//				connection.close();
				st.close();
				result.close();
			} catch (Exception e) {

				System.out.println("\nDidn't Friends List!!");
			}
		}
		return null;
	}

	public void setMessageViewed(String status, String loginerId) {

		try {

//			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/task1", "root", "root");
			prepareStatement = connection.prepareStatement("update messages set status = ? where personid = ?");
			prepareStatement.setString(1, status);
			prepareStatement.setString(2, loginerId);
			prepareStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("\nDidn't Add data!!");
		} finally {

			try {

//				connection.close();
				prepareStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("\nDidn't Add data!!");
			}
		}
	}

	public void addMessage(User user, String id, String msg, String groupId, String status) {

		try {

//			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/task1", "root", "root");
			prepareStatement = connection
					.prepareStatement("insert into messages(id,message,personId,status) values(?,?,?,?)");
			prepareStatement.setString(1, groupId);
			prepareStatement.setString(2, msg);
			prepareStatement.setString(3, id);
			prepareStatement.setString(4, "Not Viewed");
			prepareStatement.executeUpdate();
			System.out.println("success");
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("\nDidn't Add data!!");
		} finally {

			try {

//				connection.close();
				prepareStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("\nDidn't Add data!!");
			}
		}
	}

	public List<String> addAdditionalFriendsInGroup(User user, List<String> noList, String groupId) {

		try {

			List<String> rejectedNumbers = new LinkedList<>();
			for (int i = 0; noList.size() > i; ++i) {

				boolean isFalse = false;
				st = connection.createStatement();
				String name = "", id = "";
				result = st.executeQuery(
						"select * from register where mobileno = '" + noList.get(i) + "' order by id asc");
				if (!result.next()) {

					rejectedNumbers.add(noList.get(i));
					isFalse = true;
				} else {

					name = result.getString("name");
					id = result.getString("id");
					st = connection.createStatement();
					result = st
							.executeQuery("select * from personalchat where (person1mobileno = '" + user.getMobileNo()
									+ "' and person2mobileno = '" + noList.get(i) + "') or (person1mobileno = '"
									+ noList.get(i) + "' and person2mobileno = '" + user.getMobileNo() + "')");
					if (!result.next()) {

						rejectedNumbers.add(noList.get(i));
						isFalse = true;
					} else {

						st = connection.createStatement();
						result = st.executeQuery("select * from groupchat where personmobileno = '" + noList.get(i)
								+ "' and groupid = '" + groupId + "'");
						if (result.next()) {

							rejectedNumbers.add(noList.get(i));
							isFalse = true;
						}
					}
				}
				if (isFalse == false) {

					String groupname = "";
					st = connection.createStatement();
					result = st.executeQuery("select groupname from groupchat where groupid = '" + groupId + "'");
					if (result.next()) {

						groupname = result.getString("groupname");
					}
					prepareStatement = connection.prepareStatement(
							"insert into groupchat(groupid,groupname,personid,personname,personmobileno) values(?,?,?,?,?)");
					prepareStatement.setString(1, groupId);
					prepareStatement.setString(2, groupname);
					prepareStatement.setString(3, id);
					prepareStatement.setString(4, name);
					prepareStatement.setString(5, noList.get(i));
					prepareStatement.executeUpdate();
				}
			}
			return rejectedNumbers;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("\nDidn't Add data!!");
		} finally {

			try {

				result.close();
				st.close();
				prepareStatement.close();
//				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("\nDidn't Add data!!");
			}
		}
		return null;
	}

	public void exitGroup(User user, String groupId) {

		try {

//			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/task1", "root", "root");
			prepareStatement = connection
					.prepareStatement("delete from groupchat where groupid = ? and personmobileno = ?");
			prepareStatement.setString(1, groupId);
			prepareStatement.setString(2, user.getMobileNo());
			prepareStatement.executeUpdate();
		} catch (Exception e) {

			try {

//				connection.close();
				prepareStatement.close();
			} catch (Exception w) {

				System.out.println("\nDidn't Exit group!!");
			}
		}
	}

	public Map<String, String> getFriends(User user, String groupId, String id) {

		try {

			List<String> mobileNumber = new LinkedList<>();
			Map<String, String> friends = new LinkedHashMap<>();
//			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/task1", "root", "root");
			st = connection.createStatement();
			result = st.executeQuery("select * from personalchat where person1mobileno ='" + user.getMobileNo()
					+ "' or person2mobileno = '" + user.getMobileNo() + "'");
			while (result.next()) {

				if (result.getString("person1mobileno").equals(user.getMobileNo())) {

					mobileNumber.add(result.getString("person2mobileno"));
				} else if (result.getString("person2mobileno").equals(user.getMobileNo())) {

					mobileNumber.add(result.getString("person1mobileno"));
				}
			}
			st = connection.createStatement();
			result = st.executeQuery("select * from groupchat where groupid = '" + groupId + "'");
			while (result.next()) {

				if (!user.getMobileNo().equals(result.getString("personmobileno"))) {

					for (int i = 0; i < mobileNumber.size(); ++i) {

						if (mobileNumber.get(i).equals(result.getString("personmobileno"))) {

							mobileNumber.remove(i);
							break;
						}
					}
				}
			}
			for (int i = 0; i < mobileNumber.size(); ++i) {

				st = connection.createStatement();
				result = st.executeQuery("select * from register where mobileno ='" + mobileNumber.get(i) + "'");
				if (result.next()) {

					friends.put(result.getString("mobileno"), result.getString("name"));
				}
			}
			return friends;
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {

//				connection.close();
				st.close();
				result.close();
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		return null;
	}
}