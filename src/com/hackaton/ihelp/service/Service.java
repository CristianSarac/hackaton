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
	// URL
	public static final String URL_CATEGORY = "http://192.168.1.93:44748/AndroidCategoriesHandler.ashx";
	public static final String URL_USER = "http://192.168.1.93:44748/AndroidUserHandler.ashx";

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

	public ArrayList<User> getUsersForService(String service)
	{
		ArrayList<User> users = new ArrayList<User>();
		JSONArray jsonArray = getJsonObjects(URL_USER, service);
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

	public List<Card> getUserCardsForService(String service, Context context)
	{
		List<Card> cards = new ArrayList<Card>();
		ArrayList<User> users = getUsersForService(service);
		for (User user : users)
		{
			CustomCard card = new CustomCard(context);
			card.setUser(user);
			cards.add(card);
		}
		return cards;
	}

	public String[] getMainCategories()
	{
		String request = "?DataType=MainCategories";
		JSONArray jsonArray = getJsonObjects(URL_CATEGORY, request);
		String[] mainCategories = new String[jsonArray.length()];
		mainCategories[0] = "My Profile";
		try
		{
			for (int i = 1; i < jsonArray.length() + 1; i++)
			{
				JSONObject json;
				json = jsonArray.getJSONObject(i);
				mainCategories[i] = json.getString("Name");

			}
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
		return mainCategories;

	}

}
