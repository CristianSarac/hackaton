package com.hackaton.ihelp.service;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

	private String name;

	public User()
	{
	}

	public User(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	// PARCELABLE
	@Override
	public int describeContents()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags)
	{
		dest.writeString(name);

	}

	public User(Parcel in)
	{
		readFromParcel(in);

	}

	@SuppressWarnings("rawtypes")
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		public User createFromParcel(Parcel in)
		{
			return new User(in);
		}

		public User[] newArray(int size)
		{
			return new User[size];
		}

	};

	public void readFromParcel(Parcel in)
	{
		name = in.readString();

	}

}
