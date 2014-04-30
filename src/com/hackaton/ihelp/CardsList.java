package com.hackaton.ihelp;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hackaton.ihelp.service.Service;

public class CardsList extends BaseFragment {

	Service service = Service.getInstace();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		CardListView c = new CardListView(getActivity());

		int position = getArguments().getInt("position");
		List<Card> cards = new ArrayList<Card>();

		if (position == 1)
		{
			cards = service.getUserCardsForService("", getActivity());
		}
		Card card = new CustomCard(getActivity());
		cards.add(card);

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
