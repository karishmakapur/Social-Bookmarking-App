package com.karishmakapur.trillio.daos;

import java.util.ArrayList;
import java.util.List;

import com.karishmakapur.trillio.DataStore;
import com.karishmakapur.trillio.entities.Bookmark;
import com.karishmakapur.trillio.entities.UserBookmark;
import com.karishmakapur.trillio.entities.WebLink;

public class BookmarkDao {
	public List<List<Bookmark>> getBookmarks() {
		return DataStore.getBookmarks();
	}

	public void saveUserBookmark(UserBookmark userBookmark) {
		DataStore.add(userBookmark);

	}

	public List<WebLink> getAllWebLinks() {
		List<WebLink> result = new ArrayList<>();
		List<List<Bookmark>> bookmarks = DataStore.getBookmarks();
		List<Bookmark> allWebLinks = bookmarks.get(0);
		for (Bookmark bookmark : allWebLinks) {
			result.add((WebLink) bookmark);
		}
		return result;
	}

	public List<WebLink> getWebLinks(WebLink.DownloadStatus downloadStatus) {
		List<WebLink> result = new ArrayList<>();
		List<WebLink> webLinks = getAllWebLinks();
		
		for (WebLink weblink: webLinks) {
			if(weblink.getDownloadStatus().equals(downloadStatus)) {
				result.add(weblink);
			}
		}
		return result;
	}

}
