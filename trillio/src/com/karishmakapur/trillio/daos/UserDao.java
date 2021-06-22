package com.karishmakapur.trillio.daos;

import com.karishmakapur.trillio.DataStore;
import com.karishmakapur.trillio.entities.User;

public class UserDao {
	public User[] getUsers() {
		return DataStore.getUsers();
	}
}
