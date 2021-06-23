package com.karishmakapur.trillio.entities;

import java.util.Arrays;

import com.karishmakapur.trillio.constants.MovieGenre;

public class Movie extends Bookmark {
	private int releasedYear;
	private String[] cast;
	private String[] directors;
	private MovieGenre genre;
	private double imdbRating;

	public int getReleasedYear() {
		return releasedYear;
	}

	public void setReleasedYear(int releasedYear) {
		this.releasedYear = releasedYear;
	}

	public String[] getCast() {
		return cast;
	}

	public void setCast(String[] cast) {
		this.cast = cast;
	}

	public String[] getDirectors() {
		return directors;
	}

	public void setDirectors(String[] directors) {
		this.directors = directors;
	}

	public MovieGenre getGenre() {
		return genre;
	}

	public void setGenre(MovieGenre movieGenre) {
		this.genre = movieGenre;
	}

	public double getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(double imdbRating) {
		this.imdbRating = imdbRating;
	}

	@Override
	public String toString() {
		return "Movie [releasedYear=" + releasedYear + ", cast=" + Arrays.toString(cast) + ", directors="
				+ Arrays.toString(directors) + ", genre=" + genre + ", imdbRating=" + imdbRating + "]";
	}

	@Override
	public boolean isKidFriendlyEligible() {
		if (genre.equals(MovieGenre.HORROR) || genre.equals(MovieGenre.THRILLERS)) {
			return false;
		}
		return true;
	}

}
