package com.hackaton.ihelp;

import it.gmariotti.cardslib.library.internal.Card;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class CustomCard extends Card {

	protected TextView mTitle;
	protected TextView mSecondaryTitle;
	protected RatingBar mRatingBar;

	// protected ImageView mImageView;

	/**
	 * Constructor with a custom inner layout
	 * 
	 * @param context
	 */
	public CustomCard(Context context) {
		this(context, R.layout.carddemo_mycard_inner_content);
	}

	/**
	 * 
	 * @param context
	 * @param innerLayout
	 */
	public CustomCard(Context context, int innerLayout) {
		super(context, innerLayout);
		init();
	}

	/**
	 * Init
	 */
	private void init() {

		// No Header

		// Set a OnClickListener listener
		setOnClickListener(new OnCardClickListener() {
			@Override
			public void onClick(Card card, View view) {
				Toast.makeText(getContext(), "You clicked the card",
						Toast.LENGTH_LONG).show();
			}
		});
	}

	@Override
	public void setupInnerViewElements(ViewGroup parent, View view) {

		// Retrieve elements
		mTitle = (TextView) parent
				.findViewById(R.id.carddemo_myapps_main_inner_title);
		mSecondaryTitle = (TextView) parent
				.findViewById(R.id.carddemo_myapps_main_inner_secondaryTitle);
		mRatingBar = (RatingBar) parent
				.findViewById(R.id.carddemo_myapps_main_inner_ratingBar);
		// mImageView = (ImageView)
		// parent.findViewById(R.id.card_thumbnail_image);

		if (mTitle != null)
			mTitle.setText("Title");

		if (mSecondaryTitle != null)
			mSecondaryTitle.setText("Secondary title");

		if (mRatingBar != null)
			mRatingBar.setNumStars(5);
		mRatingBar.setMax(5);
		mRatingBar.setRating(4);

	}
}