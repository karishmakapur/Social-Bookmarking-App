package com.karishmakapur.trillio.managers;

import java.util.List;

import com.karishmakapur.trillio.constants.Gender;
import com.karishmakapur.trillio.constants.UserType;
import com.karishmakapur.trillio.daos.UserDao;
import com.karishmakapur.trillio.entities.User;

//Singleton class
public class UserManager {
	private static UserManager instance = null;
	private static UserDao dao = new UserDao();

	private UserManager() {
	}

	public static UserManager getInstance() {
		if (instance == null) {
			instance = new UserManager();
		}
		return instance;
	}

	public User createUser(long id, String email, String password, String firstName, String lastName, Gender gender,
			UserType userType) {
		User user = new User();
		user.setEmail(email);
		user.setFirstName(firstName);
		user.setGender(gender);
		user.setId(id);
		user.setLastName(lastName);
		user.setPassword(password);
		user.setUserType(userType);
		return user;

	}
	
	public List<User> getUsers() {
		return dao.getUsers();
	}
}
