package com.hackaton.ihelp.service;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class Category implements Parcelable {

	private String name;
	private int id;
	private Category mainCategory;
	private List<Category> subCategories;

	public Category(String name, int id, Category mainCategory)
	{
		super();
		this.name = name;
		this.id = id;
		this.mainCategory = mainCategory;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public Category getMainCategory()
	{
		return mainCategory;
	}

	public void setMainCategory(Category MainCategory)
	{
		this.mainCategory = MainCategory;
	}

	public List<Category> getSubCategories()
	{
		return subCategories;
	}

	public void setSubCategories(List<Category> subCategories)
	{
		this.subCategories = subCategories;
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
		dest.writeInt(id);
	}

	public Category(Parcel in)
	{
		readFromParcel(in);

	}

	@SuppressWarnings("rawtypes")
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		public Category createFromParcel(Parcel in)
		{
			return new Category(in);
		}

		public Category[] newArray(int size)
		{
			return new Category[size];
		}

	};

	public void readFromParcel(Parcel in)
	{
		name = in.readString();
		id = in.readInt();

	}

}
