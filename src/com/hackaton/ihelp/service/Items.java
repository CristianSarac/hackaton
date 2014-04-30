package com.hackaton.ihelp.service;

import android.os.Parcel;
import android.os.Parcelable;

public class Items implements Parcelable {

	private int userId;
	private String mainCategory;
	private String subCategory;
	private String name;
	private String description;
	private int rating;
	private int rentingPrice;
	private boolean available;
	private int userPostalCode;

	public Items() {

	}

	public Items(int userId, String mainCategory, String subCategory,
			String name, String description, int rating, int rentingPrice,
			boolean available, int userPostalCode) {
		this.userId = userId;
		this.mainCategory = mainCategory;
		this.subCategory = subCategory;
		this.name = name;
		this.description = description;
		this.rating = rating;
		this.rentingPrice = rentingPrice;
		this.available = true;
		this.userPostalCode = userPostalCode;

	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getMainCategory() {
		return mainCategory;
	}

	public void setMainCategory(String mainCategory) {
		this.mainCategory = mainCategory;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getRentingPrice() {
		return rentingPrice;
	}

	public void setRentingPrice(int rentingPrice) {
		this.rentingPrice = rentingPrice;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public int getUserPostalCode() {
		return userPostalCode;
	}

	public void setUserPostalCode(int userPostalCode) {
		this.userPostalCode = userPostalCode;
	}

	// PARCELABLE
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(userId);
		dest.writeString(mainCategory);
		dest.writeString(subCategory);
		dest.writeString(name);
		dest.writeString(description);
		dest.writeInt(rating);
		dest.writeInt(rentingPrice);
		dest.writeByte((byte) (available ? 1 : 0));
		dest.writeInt(userPostalCode);

	}

	public Items(Parcel in) {
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
		userId = in.readInt();
		mainCategory = in.readString();
		subCategory = in.readString();
		name = in.readString();
		description = in.readString();
		rating = in.readInt();
		rentingPrice = in.readInt();
		available = in.readByte() != 0;
		userPostalCode = in.readInt();

	}

}
