package com.karishmakapur.trillio.daos;

import java.util.List;

import com.karishmakapur.trillio.DataStore;
import com.karishmakapur.trillio.entities.User;

public class UserDao {
	public List<User> getUsers() {
		return DataStore.getUsers();
	}
}
