package com.hackaton.ihelp;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.CardThumbnail;
import it.gmariotti.cardslib.library.view.CardListView;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CardsList extends BaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		View rootView = null;
		CardListView c = new CardListView(getActivity());

		// inflater.inflate(R.id.myList, container, false);

		int position = getArguments().getInt("position");

		final ArrayList<Card> cards = new ArrayList<Card>();
		// Create a Card
		// for (int i = 0; i < 4; i++) {

		Card card = new Card(getActivity());
		Card card2 = new Card(getActivity());

		CardHeader header = new CardHeader(getActivity());
		CardHeader header2 = new CardHeader(getActivity());

		header.setTitle("titluuuuuuuu" + position);
		header2.setTitle("cevaaaaaaaaaaa");
		// Create thumbnail
		CardThumbnail thumb = new CardThumbnail(getActivity());

		// Set resource
		thumb.setDrawableResource(R.drawable.ic_launcher);

		// Add thumbnail to a card
		card.addCardThumbnail(thumb);

		card.addCardHeader(header);
		card2.addCardHeader(header2);

		cards.add(card);
		cards.add(card2);

		// }
		CardArrayAdapter mCardAdapter = new CardArrayAdapter(getActivity(),
				cards);

		// c = (CardListView) getActivity().findViewById(R.id.myList);
		if (c != null) {
			c.setAdapter(mCardAdapter);
		}

		return c;
	}

	@Override
	public int getTitleResourceId() {
		return R.id.myList;
	}

}
