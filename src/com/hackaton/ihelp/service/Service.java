package com.hackaton.ihelp.service;

import it.gmariotti.cardslib.library.internal.Card;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.hackaton.ihelp.CustomCard;

public class Service {

	// JSON Node names
	public static final String TAG_NAME = "Name";
	public static final String TAG_EMAIL = "Email";
	// IP
	public static final String IP = "192.168.87.113:44748";

	// URL
	public static final String URL_CATEGORY = "http://" + IP
			+ "/AndroidCategoriesHandler.ashx";
	public static final String URL_USER = "http://" + IP
			+ "/AndroidUserHandler.ashx";

	private static Service service = null;

	public static Service getInstace()
	{
		if (service == null)
			service = new Service();
		return service;
	}

	private Service()
	{
	}

	public JSONArray getJsonObjects(String url, String request)
	{
		String url_select = url + request;
		ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();

		InputStream inputStream = null;
		try
		{

			HttpClient httpClient = new DefaultHttpClient();

			HttpPost httpPost = new HttpPost(url_select);
			httpPost.setEntity(new UrlEncodedFormEntity(param));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();

			// Read content & Log
			inputStream = httpEntity.getContent();
		} catch (UnsupportedEncodingException e1)
		{
			Log.e("UnsupportedEncodingException", e1.toString());
			e1.printStackTrace();
		} catch (ClientProtocolException e2)
		{
			Log.e("ClientProtocolException", e2.toString());
			e2.printStackTrace();
		} catch (IllegalStateException e3)
		{
			Log.e("IllegalStateException", e3.toString());
			e3.printStackTrace();
		} catch (IOException e4)
		{
			Log.e("IOException", e4.toString());
			e4.printStackTrace();
		}
		// Convert response to string using String Builder
		try
		{
			BufferedReader bReader = new BufferedReader(new InputStreamReader(
					inputStream, "iso-8859-1"), 8);
			StringBuilder sBuilder = new StringBuilder();

			String line = null;
			while ((line = bReader.readLine()) != null)
			{
				sBuilder.append(line + "\n");
			}

			inputStream.close();
			String jsonString = sBuilder.toString();

			return new JSONArray(jsonString);

		} catch (Exception e)
		{
			Log.e("StringBuilding & BufferedReader", "Error converting result "
					+ e.toString());
		}

		return null;
	}

	public User JSONToUser(JSONObject json) throws JSONException
	{
		User u = new User();
		u.setName(json.getString("name"));
		return u;
	}

	public ArrayList<User> getUsersForService(int serviceID)
	{
		ArrayList<User> users = new ArrayList<User>();
		JSONArray jsonArray = getJsonObjects(URL_USER, serviceID + "");
		User u = null;

		try
		{
			for (int i = 0; i < jsonArray.length(); i++)
			{
				JSONObject json = jsonArray.getJSONObject(i);
				u = JSONToUser(json);
				users.add(u);

			}
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
		return users;
	}

	public List<Card> getUserCardsForService(int serviceID, Context context)
	{
		List<Card> cards = new ArrayList<Card>();
		ArrayList<User> users = getUsersForService(serviceID);
		for (User user : users)
		{
			CustomCard card = new CustomCard(context);
			card.setUser(user);
			cards.add(card);
		}
		return cards;
	}

	public ArrayList<Category> getCategories(Category mainCategory)
	{
		ArrayList<Category> categories = new ArrayList<Category>();
		String request = "";
		if (mainCategory == null)
			request = "?DataType=MainCategories";
		else
			request = "?DataType=SubCategory&MainCategoryId="
					+ mainCategory.getId();

		JSONArray jsonArray = getJsonObjects(URL_CATEGORY, request);
		if (mainCategory == null)
		{
			Category myProfile = new Category("My Profile", 0, null);
			categories.add(0, myProfile);
			try
			{
				for (int i = 0; i < jsonArray.length(); i++)
				{
					JSONObject json;
					json = jsonArray.getJSONObject(i);
					Category c = new Category(json.getString("Name"),
							json.getInt("Id"), null);
					categories.add(c);
				}
			} catch (JSONException e)
			{
				e.printStackTrace();
			}
		} else
		{

			try
			{
				for (int i = 0; i < jsonArray.length(); i++)
				{
					JSONObject json;
					json = jsonArray.getJSONObject(i);
					Category c = new Category(json.getString("Name"),
							json.getInt("Id"), mainCategory);
					categories.add(c);

				}
			} catch (JSONException e)
			{
				e.printStackTrace();
			}
			Category end = new Category("Back", -1, null);
			categories.add(end);
		}

		return categories;

	}
}
