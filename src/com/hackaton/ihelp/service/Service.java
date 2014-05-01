package com.hackaton.ihelp.service;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.Card.OnCardClickListener;

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

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hackaton.ihelp.CustomCard;
import com.hackaton.ihelp.ProfileFragment;
import com.hackaton.ihelp.R;

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
	public static final String URL_SERVICES = "http://" + IP
			+ "/AndroidDataHandler.ashx";

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

	public JSONObject getJsonSingleObject(String url, String request)
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

			return new JSONObject(jsonString);

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
		u.setName(json.getString("Name"));
		u.setSurName(json.getString("Surname"));
		u.setEmail(json.getString("Email"));
		return u;
	}

	public Services JSONToService(JSONObject json) throws JSONException
	{

		Services s = new Services();
		s.setUserId(json.getInt("UserId"));
		s.setServiceName(json.getString("ServiceName"));
		s.setServiceDescription(json.getString("ServiceDescription"));
		s.setUserRating(json.getInt("UserRating"));
		// TO DO date s.setDateAdded("DateAdded")
		s.setUserPostalCode(json.getInt("UserPostalCode"));
		return s;
	}

	public ArrayList<Services> getServicesNearbyByID(int serviceID)
	{
		ArrayList<Services> services = new ArrayList<Services>();
		JSONArray jsonArray = getJsonObjects(URL_SERVICES,
				"?DataType=GetServicesFiltered&UserPostalCode=8000&SubCategory="
						+ serviceID);
		Services s = null;

		try
		{
			for (int i = 0; i < jsonArray.length(); i++)
			{
				JSONObject json = jsonArray.getJSONObject(i);
				s = JSONToService(json);
				services.add(s);

			}
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
		return services;
	}

	public List<Card> getUserCardsForService(int serviceID, Activity activity)
	{
		List<Card> cards = new ArrayList<Card>();
		ArrayList<Services> services = getServicesNearbyByID(serviceID);
		for (Services s : services)
		{

			CustomCard card = new CustomCard(activity);
			final Services x = s;
			final Activity a = activity;
			card.setServices(s);
			card.setContext(activity);
			card.setOnClickListener(new OnCardClickListener() {

				@Override
				public void onClick(Card arg0, View arg1)
				{
					Fragment cardsFragment = new ProfileFragment();
					Bundle args = new Bundle();
					args.putInt("id", x.getUserId());
					cardsFragment.setArguments(args);
					a.getFragmentManager().beginTransaction()
							.replace(R.id.container, cardsFragment).commit();

				}
			});
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

	public User getUserFromId(int Id)
	{
		String request = "?DataType=getUserById&UserId=" + Id;
		JSONObject json = getJsonSingleObject(URL_USER, request);

		User u = new User();
		try
		{

			u.setName(json.getString("Name"));
			u.setSurName(json.getString("Surname"));
			u.setEmail(json.getString("Email"));
			u.setCity(json.getString("City"));
			u.setRating(json.getInt("Rating"));
			return u;
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
		return u;

	}

	public ArrayList<Services> getServicessForUser(int userID)
	{
		ArrayList<Services> services = new ArrayList<Services>();

		JSONArray jsonArray = getJsonObjects(URL_SERVICES,
				"?DataType=GetServicesByUserId&UserId=" + userID);
		Services u = null;

		try
		{
			for (int i = 0; i < jsonArray.length(); i++)
			{
				JSONObject json = jsonArray.getJSONObject(i);
				u = JSONToService(json);
				services.add(u);

			}
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
		return services;
	}

}
