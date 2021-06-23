package com.karishmakapur.trillio.constants;

public enum Gender {
	
	MALE(0),
	FEMALE(1),
	TRANSGENDER(2);
	
	private int gender;
	
	private Gender(int gender) {
		this.gender = gender;
	}
	
	private int getGender() {
		return gender;
	}
}
