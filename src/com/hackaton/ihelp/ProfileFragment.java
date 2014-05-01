package com.hackaton.ihelp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.hackaton.ihelp.service.Service;
import com.hackaton.ihelp.service.User;

public class ProfileFragment extends Fragment {
	Service service = Service.getInstace();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_profile, container,
				false);

		User u = service.getUserFromId(2);

		String userName = u.getName();
		String surname = u.getSurName();
		String email = u.getEmail();
		String citi = u.getCity();
		int rating = u.getRating();

		TextView name = (TextView) rootView.findViewById(R.id.textViewName);
		TextView emaill = (TextView) rootView.findViewById(R.id.textViewEmail);
		TextView city = (TextView) rootView.findViewById(R.id.textViewCity);
		RatingBar ratingbar = (RatingBar) rootView.findViewById(R.id.ratingBar);

		name.setText(userName + "  " + surname);
		emaill.setText(email);
		city.setText(citi);
		ratingbar.setNumStars(rating);

		return rootView;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

	}
}
