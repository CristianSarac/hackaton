package com.hackaton.ihelp;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hackaton.ihelp.service.Category;
import com.hackaton.ihelp.service.Service;

public class CardsList extends BaseFragment {

	Service service = Service.getInstace();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		CardListView c = new CardListView(getActivity());

		// This category only has name and id
		Category cat = (Category) getArguments().getParcelable("category");

		Log.w("IDD", "id:" + cat.getId());

		// set action bar title
		getActivity().getActionBar().setTitle(cat.getName());

		List<Card> cards = new ArrayList<Card>();
		int id = cat.getId();
		cards = service.getUserCardsForService(id, getActivity());

		CardArrayAdapter mCardAdapter = new CardArrayAdapter(getActivity(),
				cards);

		if (c != null)
		{
			c.setAdapter(mCardAdapter);
		}

		return c;
	}

	@Override
	public int getTitleResourceId()
	{
		return R.id.myList;
	}

}
