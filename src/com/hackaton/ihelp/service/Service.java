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
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Service {

	// JSON Node names
	public static final String TAG_NAME = "Name";
	public static final String TAG_EMAIL = "Email";
	// URL
	public static final String URL = "http://192.168.1.90:44748/AndroidDataHandler.ashx";
	public static final String URL_TEST = "https://prod.api.pvp.net/api/lol/eune/v1.4/summoner/by-name/jap0?api_key=215c4a93-6152-4e55-9e72-e434cd41dcbd";

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

	public JSONObject getJsonObject(String request)
	{
		String url_select = URL_TEST + request;

		ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();

		InputStream inputStream = null;
		try
		{
			// Set up HTTP post

			// HttpClient is more then less deprecated. Need to change to
			// URLConnection
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
			String result = sBuilder.toString();

			return new JSONObject(result);
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

	public List<User> getUsersForService(String service)
	{
		List<User> users = new ArrayList<User>();

		JSONObject json = getJsonObject(service);
		User u = null;
		try
		{
			u = JSONToUser(json);
		} catch (JSONException e)
		{
			e.printStackTrace();
		}

		users.add(u);

		return users;
	}

	public List<Card> createCardsFromUsers(List<User> users)
	{
		List<Card> cards = new ArrayList<Card>();

		for (User user : users)
		{
			Card card = null;
			// TODO Create card from user
			cards.add(card);
		}

		return cards;
	}
}
