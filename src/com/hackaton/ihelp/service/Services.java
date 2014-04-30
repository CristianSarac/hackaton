package com.hackaton.ihelp.service;

import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

public class Services implements Parcelable {

	private int userId;
	private String mainCategory;
	private String subCategory;
	private int userRating;
	private String serviceName;
	private String serviceDescription;
	private String email;
	private Date dateAdded;
	private int price;
	private int userPostalCode;

	public Services() {

	}

	public Services(int userId, String mainCategory, String subCategory,
			int userRating, String serviceName, String serviceDescription,
			String email, Date dateAdded, int price, int userPostalCode) {

		this.userId = userId;
		this.mainCategory = mainCategory;
		this.subCategory = subCategory;
		this.userRating = userRating;
		this.serviceName = serviceName;
		this.serviceDescription = serviceDescription;
		this.email = email;
		this.dateAdded = dateAdded;
		this.price = price;
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

	public int getUserRating() {
		return userRating;
	}

	public void setUserRating(int userRating) {
		this.userRating = userRating;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceDescription() {
		return serviceDescription;
	}

	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
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
		dest.writeInt(userRating);
		dest.writeString(serviceName);
		dest.writeString(serviceDescription);
		dest.writeString(email);
		dest.writeSerializable(dateAdded);
		dest.writeInt(price);
		dest.writeInt(userPostalCode);

	}

	public Services(Parcel in) {
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
		userRating = in.readInt();
		serviceName = in.readString();
		serviceDescription = in.readString();
		email = in.readString();
		dateAdded = (Date) in.readSerializable();
		price = in.readInt();
		userPostalCode = in.readInt();

	}

}
