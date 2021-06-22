package com.karishmakapur.trillio.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.karishmakapur.trillio.managers.BookmarkManager;

class WebLinkTest {

	
	@Test
	void testIsKidFriendlyEligible() {
		//Test 1: adultWord in url -- false
		WebLink weblink1 = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-adultWord--part-2.html",
				"http://www.javaworld.com");
		
		
		boolean isKidFriendlyEligible1 = weblink1.isKidFriendlyEligible();
		
		assertFalse("For adultWord in url - isKidFriendlyEligible must return false", isKidFriendlyEligible1);
		
		//Test 2: adultWord in title -- false
		WebLink weblink2 = BookmarkManager.getInstance().createWebLink(2000, "Taming adultWord Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.javaworld.com");
		
		
		boolean isKidFriendlyEligible2 = weblink2.isKidFriendlyEligible();
		
		assertFalse("For adultWord in title - isKidFriendlyEligible must return false", isKidFriendlyEligible2);
		
		
		//Test 3: adult in host -- false
		WebLink weblink3 = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.adult.com");
		
		
		boolean isKidFriendlyEligible3 = weblink3.isKidFriendlyEligible();
		
		assertFalse("For adult in host - isKidFriendlyEligible must return false", isKidFriendlyEligible3);
		
		
		//Test 4: adult in url, but not in host part -- true
		WebLink weblink4 = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--adult-2.html",
				"http://www.javaworld.com");
		
		
		boolean isKidFriendlyEligible4 = weblink4.isKidFriendlyEligible();
		
		assertTrue("For adult in url but not in host - isKidFriendlyEligible must return false", isKidFriendlyEligible4);
		
		
		//Test 5: adult in title only -- true
		WebLink weblink5 = BookmarkManager.getInstance().createWebLink(2000, "Taming Adult Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.javaworld.com");
		
		
		boolean isKidFriendlyEligible5 = weblink5.isKidFriendlyEligible();
		
		assertTrue("For adult in url but not in host - isKidFriendlyEligible must return false", isKidFriendlyEligible5);
		
		
	}

}
