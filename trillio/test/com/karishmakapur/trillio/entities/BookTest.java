package com.karishmakapur.trillio.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.karishmakapur.trillio.constants.BookGenre;
import com.karishmakapur.trillio.managers.BookmarkManager;

class BookTest {

	@Test
	void testIsKidFriendlyEligible() {
		Book book1 = BookmarkManager.getInstance().createBook(4000, "Walden", 1854, "Wilder Publications",
				new String[] { "Henry David Thoreau" }, BookGenre.PHILOSOPHY, 4.3);
		
		assertFalse("Book is Philosophy - return false", book1.isKidFriendlyEligible());
		
		Book book2 = BookmarkManager.getInstance().createBook(4000, "Walden", 1854, "Wilder Publications",
				new String[] { "Henry David Thoreau" }, BookGenre.SELF_HELP, 4.3);
		
		assertFalse("Book is Self Help - return false", book2.isKidFriendlyEligible());
		
		Book book3 = BookmarkManager.getInstance().createBook(4000, "Walden", 1854, "Wilder Publications",
				new String[] { "Henry David Thoreau" }, BookGenre.ART, 4.3);
		
		assertTrue("Book is Art - return true", book3.isKidFriendlyEligible());
		
		
	}

}
