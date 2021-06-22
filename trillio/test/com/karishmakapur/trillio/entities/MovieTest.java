package com.karishmakapur.trillio.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.karishmakapur.trillio.constants.MovieGenre;
import com.karishmakapur.trillio.managers.BookmarkManager;

class MovieTest {

	@Test
	void testIsKidFriendlyEligible() {
		Movie movie1 =  BookmarkManager.getInstance().createMovie(3000, "Citizen Kane", "", 1941,
				new String[] { "Orson Welles", "Joseph Cotten" }, new String[] { "Orson Welles" }, MovieGenre.HORROR,
				8.5);
		
		assertFalse("Movie is Horror - return false" , movie1.isKidFriendlyEligible());
		
		Movie movie2 =  BookmarkManager.getInstance().createMovie(3000, "Citizen Kane", "", 1941,
				new String[] { "Orson Welles", "Joseph Cotten" }, new String[] { "Orson Welles" }, MovieGenre.THRILLERS,
				8.5);
		
		assertFalse("Movie is Thriller - return false" , movie2.isKidFriendlyEligible());
		
		
		Movie movie3 =  BookmarkManager.getInstance().createMovie(3000, "Citizen Kane", "", 1941,
				new String[] { "Orson Welles", "Joseph Cotten" }, new String[] { "Orson Welles" }, MovieGenre.CLASSICS,
				8.5);
		
		assertTrue("Movie is Classic - return true" , movie3.isKidFriendlyEligible());
		
		
		
		
	}

}
