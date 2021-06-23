package com.karishmakapur.trillio;

import java.util.List;

import com.karishmakapur.trillio.bgjobs.WebpageDownloaderTask;
import com.karishmakapur.trillio.entities.Bookmark;
import com.karishmakapur.trillio.entities.User;
import com.karishmakapur.trillio.managers.BookmarkManager;
import com.karishmakapur.trillio.managers.UserManager;

public class Launch {

	private static List<User> users;
	private static List<List<Bookmark>> bookmarks;

	private static void loadData() {
		System.out.println("1. Loading data ...");
		DataStore.loadData();

		users = UserManager.getInstance().getUsers();
		bookmarks = BookmarkManager.getInstance().getBookmarks();

		//System.out.println("Printing data ...");
		//printUserData();
		//printBookmarkData();
	}

	/*private static void printBookmarkData() {
		for (Bookmark[] bookmarkList : bookmarks) {
			for (Bookmark bookmark : bookmarkList) {
				System.out.println(bookmark);
			}
		}
	}

	private static void printUserData() {
		for (User user : users) {
			System.out.println(user);
		}
	}*/


	private static void start() {
		//System.out.println("\n2. Bookmarking ...");
		for(User user: users) {
			View.browse(user, bookmarks);
		}
	}

	public static void runDownloaderJob() {
		WebpageDownloaderTask task = new WebpageDownloaderTask(true);
		(new Thread(task)).start();
	}
	public static void main(String[] args) {
		loadData();
		start();
		runDownloaderJob();

	}
}
