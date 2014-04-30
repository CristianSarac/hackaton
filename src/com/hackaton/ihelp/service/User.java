package com.hackaton.ihelp.service;

import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

	private String name;
	private String surName;
	private String email;
	private int rating;
	private Date dateSigned;
	private int postalCode;
	private String city;
	private String country;
	private String fullAddress;
	private String password;

	public User() {
	}

	public User(String name, String surName, String email, int rating,
			Date dateSigned, int postalCode, String city, String country,
			String fullAddress, String password) {
		this.name = name;
		this.surName = surName;
		this.email = email;
		this.rating = rating;
		this.dateSigned = dateSigned;
		this.postalCode = postalCode;
		this.city = city;
		this.country = country;
		this.fullAddress = fullAddress;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Date getDateSigned() {
		return dateSigned;
	}

	public void setDateSigned(Date dateSigned) {
		this.dateSigned = dateSigned;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// PARCELABLE
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeString(surName);
		dest.writeString(email);
		dest.writeInt(rating);
		dest.writeSerializable(dateSigned);
		dest.writeInt(postalCode);
		dest.writeString(city);
		dest.writeString(country);
		dest.writeString(fullAddress);
		dest.writeString(password);

	}

	public User(Parcel in) {
		readFromParcel(in);

	}

	@SuppressWarnings("rawtypes")
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		public User createFromParcel(Parcel in) {
			return new User(in);
		}

		public User[] newArray(int size) {
			return new User[size];
		}

	};

	public void readFromParcel(Parcel in) {
		name = in.readString();
		surName = in.readString();
		email = in.readString();
		rating = in.readInt();
		dateSigned = (Date) in.readSerializable();
		postalCode = in.readInt();
		city = in.readString();
		country = in.readString();
		fullAddress = in.readString();
		password = in.readString();

	}

}
