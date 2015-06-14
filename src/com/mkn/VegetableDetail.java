package com.mkn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class VegetableDetail extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.activity_vegetable_detail);
		Intent intent = getIntent();

		setTitle(intent.getStringExtra("name"));

		TextView price = (TextView) findViewById(R.id.tvVegetablePrice);
		TextView description = (TextView) findViewById(R.id.tvVegetableDescription);
		ImageView thumb = (ImageView) findViewById(R.id.imgVegetable);

		// name.setText(intent.getStringExtra("name"));
		thumb.setImageResource(Splash.thumbs[intent.getIntExtra("position", R.drawable.sample_veg)]);
		price.setText(intent.getStringExtra("price"));
		description
				.setText("Vegetable are very good for health.  In everyday usage, a vegetable is any part of a plant that is consumed by humans as food as part of a savoury course or meal. The term \"vegetable\" is somewhat arbitrary, and largely defined through culinary and cultural tradition. It normally excludes other main types of plant food, fruits, nuts and cereal grains but includes seeds such as pulses. The original meaning of the word vegetable, still used in biology, was to describe all types of plant, as in the terms \"vegetable kingdom\" and \"vegetable matter\".");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vegetable_detail, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_contact_us:
			Intent intent = new Intent();
			intent.setClass(this, ContactActivity.class);
			startActivity(intent);
			break;
		case android.R.id.home:
			finish();
			break;
		}
		return false;
	}

}
