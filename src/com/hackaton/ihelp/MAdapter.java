package com.hackaton.ihelp;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MAdapter extends CardArrayAdapter {

	private Context context;
	private List<Card> cards;

	public MAdapter(Context context, List<Card> cards) {
		super(context, cards);
		this.context = context;
		this.cards = cards;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater
				.inflate(R.layout.row_card_layout, parent, false);

		return rowView;
	}
}
