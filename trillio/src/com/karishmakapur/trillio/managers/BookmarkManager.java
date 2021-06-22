package com.karishmakapur.trillio.managers;

import com.karishmakapur.trillio.daos.BookmarkDao;
import com.karishmakapur.trillio.entities.Book;
import com.karishmakapur.trillio.entities.Bookmark;
import com.karishmakapur.trillio.entities.Movie;
import com.karishmakapur.trillio.entities.User;
import com.karishmakapur.trillio.entities.UserBookmark;
import com.karishmakapur.trillio.entities.WebLink;

public class BookmarkManager {
	private static BookmarkManager instance = null;

	private static BookmarkDao dao = new BookmarkDao();
	
	private BookmarkManager() {
	}

	public static BookmarkManager getInstance() {
		if (instance == null) {
			instance = new BookmarkManager();
		}
		return instance;
	}

	public Movie createMovie(long id, String title, String profileUrl, int releaseYear, String[] cast,
			String[] directors, String genre, double imdbRating) {
		Movie movie = new Movie();
		movie.setCast(cast);
		movie.setDirectors(directors);
		movie.setGenre(genre);
		movie.setId(id);
		movie.setImdbRating(imdbRating);
		movie.setProfileUrl(profileUrl);
		movie.setReleasedYear(releaseYear);
		movie.setTitle(title);
		return movie;
	}

	public Book createBook(long id, String title, int publicationYear, String publisher, String[] authors, String genre,
			double amazonRating) {
		
		Book book = new Book();
		book.setAmazonRating(amazonRating);
		book.setAuthors(authors);
		book.setGenre(genre);
		book.setPublicationYear(publicationYear);
		book.setPublisher(publisher);
		book.setId(id);
		book.setTitle(title);
		return book;
		
	}
	
	public WebLink createWebLink(long id, String title, String url, String host) {
		WebLink weblink = new WebLink();
		weblink.setHost(host);
		weblink.setUrl(url);
		weblink.setId(id);
		weblink.setTitle(title);
		return weblink;
	}
	
	public Bookmark[][] getBookmarks(){
		return dao.getBookmarks();
	}

	public void saveUserBookmark(User user, Bookmark bookmark) {
		UserBookmark userBookmark = new UserBookmark();
		userBookmark.setUser(user);
		userBookmark.setBookmark(bookmark);
		
		dao.saveUserBookmark(userBookmark);
	}
}
