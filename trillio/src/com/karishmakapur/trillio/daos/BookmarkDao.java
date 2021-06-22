package com.karishmakapur.trillio.daos;

import com.karishmakapur.trillio.DataStore;
import com.karishmakapur.trillio.entities.Bookmark;
import com.karishmakapur.trillio.entities.UserBookmark;

public class BookmarkDao {
	public Bookmark[][] getBookmarks() {
		return DataStore.getBookmarks();
	}

	public void saveUserBookmark(UserBookmark userBookmark) {
		DataStore.add(userBookmark);
		
	}
}
