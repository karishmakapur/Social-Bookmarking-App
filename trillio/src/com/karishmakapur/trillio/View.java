package com.karishmakapur.trillio;

import java.util.List;

import com.karishmakapur.trillio.constants.KidFriendlyStatus;
import com.karishmakapur.trillio.constants.UserType;
import com.karishmakapur.trillio.controllers.BookmarkController;
import com.karishmakapur.trillio.entities.Bookmark;
import com.karishmakapur.trillio.entities.User;
import com.karishmakapur.trillio.partner.Shareable;

public class View {
	public static void browse(User user, List<List<Bookmark>> bookmarks) {

		System.out.println("\n" + user.getEmail() + " is browsing items ...");

		//int bookmarkCount = 0;

		for (List<Bookmark> bookmarkList : bookmarks) {
			for (Bookmark bookmark : bookmarkList) {
				// Bookmarking
				//if (bookmarkCount < DataStore.USER_BOOKMARK_LIMIT) {
					boolean isBookmarked = getBookmarkDecision(bookmark);

					if (isBookmarked) {
						//bookmarkCount++;
						BookmarkController.getIntance().saveUserBookmark(user, bookmark);
						System.out.println("Bookmarked " + bookmark);

					}
				//}

				if (user.getUserType().equals(UserType.EDITOR) || user.getUserType().equals(UserType.CHIEF_EDITOR)) {

					// Mark as kid-friendly
					if (bookmark.isKidFriendlyEligible()
							&& bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.UNKNOWN)) {
						KidFriendlyStatus kidFriendlyStatus = getKidFriendlyStatusDecision(bookmark);

						if (!kidFriendlyStatus.equals(KidFriendlyStatus.UNKNOWN)) {
							BookmarkController.getIntance().setKidFriendlyStatus(user, kidFriendlyStatus, bookmark);
						}

					}

					// Sharing
					if (bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.APPROVED)
							&& bookmark instanceof Shareable) {
						boolean isShared = getShareDecision();
						if(isShared) {
							BookmarkController.getIntance().share(user, bookmark);
						}
					}
				}

			}
		}

	}

	private static boolean getShareDecision() {
		return Math.random() < 0.5 ? true : false;
	}

	private static KidFriendlyStatus getKidFriendlyStatusDecision(Bookmark bookmark) {
		return Math.random() < 0.4 ? KidFriendlyStatus.APPROVED
				: (Math.random() >= 0.4 & Math.random() < 0.8) ? KidFriendlyStatus.REJECTED : KidFriendlyStatus.UNKNOWN;

	}

	private static boolean getBookmarkDecision(Bookmark bookmark) {
		return Math.random() < 0.5 ? true : false;
	}

	/*
	 * public static void bookmark(User user, Bookmark[][] bookmarks) {
	 * System.out.println("\n" + user.getEmail() + " is bookmarking.");
	 * 
	 * for(int i = 0; i < DataStore.USER_BOOKMARK_LIMIT; i++) { int typeOffset =
	 * (int) (Math.random() * DataStore.BOOKMARK_TYPES_COUNT); int bookmarkOffset =
	 * (int) Math.random() * DataStore.BOOKMARK_COUNT_PER_TYPE;
	 * 
	 * Bookmark bookmark = bookmarks[typeOffset][bookmarkOffset];
	 * BookmarkController.getIntance().saveUserBookmark(user, bookmark);
	 * 
	 * System.out.println(bookmark);
	 * 
	 * } }
	 */
}
