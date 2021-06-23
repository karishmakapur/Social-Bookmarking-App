package com.karishmakapur.trillio;

import java.util.ArrayList;
import java.util.List;

import com.karishmakapur.trillio.constants.BookGenre;
import com.karishmakapur.trillio.constants.Gender;
import com.karishmakapur.trillio.constants.MovieGenre;
import com.karishmakapur.trillio.constants.UserType;
import com.karishmakapur.trillio.entities.Bookmark;
import com.karishmakapur.trillio.entities.User;
import com.karishmakapur.trillio.entities.UserBookmark;
import com.karishmakapur.trillio.managers.*;
import com.karishmakapur.trillio.util.IOUtil;

public class DataStore {

	private static List<User> users = new ArrayList<>();

	public static List<User> getUsers() {
		return users;
	}

	private static List<List<Bookmark>> bookmarks = new ArrayList<>();

	public static List<List<Bookmark>> getBookmarks() {
		return bookmarks;
	}

	private static List<UserBookmark> userBookmarks = new ArrayList<>();
	//private static int bookmarkIndex;

	public static void loadData() {
		loadUsers();
		loadWebLinks();
		loadMovies();
		loadBooks();
	}

	private static void loadUsers() {
		List<String> data = new ArrayList<>();
		IOUtil.read(data, "User.txt");
		for (String row : data) {
			String[] values = row.split("\t");

			Gender gender = Gender.MALE;
			if (values[5].equals("f")) {
				gender = Gender.FEMALE;
			} else if (values[5].equals("t")) {
				gender = Gender.TRANSGENDER;
			}

			users.add(UserManager.getInstance().createUser(Long.parseLong(values[0]), values[1], values[2],
					values[3], values[4], gender, UserType.valueOf(values[6].toUpperCase())));
		}
	}

	private static void loadWebLinks() {
		List<String> data = new ArrayList<>();
		IOUtil.read(data, "WebLink.txt");
		List<Bookmark> bookmarkList = new ArrayList<>();
		for (String row : data) {
			String[] values = row.split("\t");
			Bookmark bookmark = BookmarkManager.getInstance().createWebLink(Long.parseLong(values[0]), values[1],
					values[2], values[3]/* , values[4] */);
			bookmarkList.add(bookmark);
		}
		bookmarks.add(bookmarkList);
	}

	private static void loadMovies() {
		List<String> data = new ArrayList<>();
		IOUtil.read(data, "Movie.txt");
		List<Bookmark> bookmarkList = new ArrayList<>();
		for (String row : data) {
			String[] values = row.split("\t");
			String[] cast = values[3].split(",");
			String[] directors = values[4].split(",");
			Bookmark bookmark = BookmarkManager.getInstance().createMovie(Long.parseLong(values[0]), values[1], "",
					Integer.parseInt(values[2]), cast, directors, MovieGenre.valueOf(values[5].toUpperCase()),
					Double.parseDouble(values[6])/* , values[7] */);
			bookmarkList.add(bookmark);
		}
		bookmarks.add(bookmarkList);	}

	private static void loadBooks() {
		List<String> data = new ArrayList<>();
		IOUtil.read(data, "Book.txt");
		List<Bookmark> bookmarkList = new ArrayList<>();
		for (String row : data) {
			String[] values = row.split("\t");
			String[] authors = values[4].split(",");
			Bookmark bookmark = BookmarkManager.getInstance().createBook(Long.parseLong(values[0]), values[1],
					Integer.parseInt(values[2]), values[3], authors, BookGenre.valueOf(values[5].toUpperCase()),
					Double.parseDouble(values[6])/* , values[7] */);
			bookmarkList.add(bookmark);
		}
		bookmarks.add(bookmarkList);
	}

	public static void add(UserBookmark userBookmark) {
		userBookmarks.add(userBookmark);
		//bookmarkIndex++;
	}
}
