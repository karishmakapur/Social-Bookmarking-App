package com.karishmakapur.trillio.controllers;

import com.karishmakapur.trillio.entities.Bookmark;
import com.karishmakapur.trillio.entities.User;
import com.karishmakapur.trillio.managers.BookmarkManager;

public class BookmarkController {
	private static BookmarkController instance = null;
	
	private BookmarkController() {}
	
	public static BookmarkController getIntance() {
		if(instance == null) {
			instance = new BookmarkController();
		}
		return instance;
	}
	
	public void saveUserBookmark(User user, Bookmark bookmark) {
		BookmarkManager.getInstance().saveUserBookmark(user,bookmark);
		
	}
	
}
