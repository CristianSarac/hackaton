package com.hackaton.ihelp;

import it.gmariotti.cardslib.library.internal.Card;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.hackaton.ihelp.service.Service;
import com.hackaton.ihelp.service.Services;
import com.hackaton.ihelp.service.User;

public class CustomCard extends Card {

	protected TextView mTitle;
	protected TextView mSecondaryTitle, postal, price, userName;
	protected RatingBar mRatingBar;
	private Services services;
	private Context context;

	// protected ImageView mImageView;

	/**
	 * Constructor with a custom inner layout
	 * 
	 * @param context
	 */
	public CustomCard(Context context)
	{

		this(context, R.layout.carddemo_mycard_inner_content);
	}

	public void setServices(Services services)
	{
		this.services = services;
	}

	public void setContext(Context context)
	{
		this.context = context;
	}

	/**
	 * 
	 * @param context
	 * @param innerLayout
	 */
	public CustomCard(Context context, int innerLayout)
	{
		super(context, innerLayout);
		init();
	}

	/**
	 * Init
	 */
	private void init()
	{

		// No Header

		// Set a OnClickListener listener
		setOnClickListener(new OnCardClickListener() {
			@Override
			public void onClick(Card card, View view)
			{

			}
		});
	}

	@Override
	public void setupInnerViewElements(ViewGroup parent, View view)
	{

		// Retrieve elements
		mTitle = (TextView) parent
				.findViewById(R.id.carddemo_myapps_main_inner_title);
		mSecondaryTitle = (TextView) parent
				.findViewById(R.id.carddemo_myapps_main_inner_secondaryTitle);
		postal = (TextView) parent
				.findViewById(R.id.carddemo_myapps_main_inner_postal);
		price = (TextView) parent
				.findViewById(R.id.carddemo_myapps_main_inner_price);
		userName = (TextView) parent
				.findViewById(R.id.carddemo_myapps_main_inner_user_name);
		mRatingBar = (RatingBar) parent
				.findViewById(R.id.carddemo_myapps_main_inner_ratingBar);
		// mImageView = (ImageView)
		// parent.findViewById(R.id.card_thumbnail_image);

		if (mTitle != null)

			mTitle.setText(services.getServiceName());

		if (mSecondaryTitle != null)
			mSecondaryTitle.setText(services.getServiceDescription());
		if (postal != null)
			postal.setText("Postal Code: " + services.getUserPostalCode());
		if (price != null)
			price.setText("Price: " + services.getPrice());

		if (mRatingBar != null)
			mRatingBar.setNumStars(5);
		mRatingBar.setMax(5);
		mRatingBar.setRating(services.getUserRating());
		if (userName != null)
		{
			Service s = Service.getInstace();
			User u = s.getUserFromId(services.getUserId());
			userName.setText("By: " + u.getName());
		}
	}
}