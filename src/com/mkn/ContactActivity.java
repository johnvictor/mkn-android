package com.mkn;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class ContactActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.activity_contact);

		ImageView contact = (ImageView) findViewById(R.id.imgCall);
		ImageView email = (ImageView) findViewById(R.id.imgEmail);

		contact.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent callIntent = new Intent(Intent.ACTION_CALL);
				callIntent.setData(Uri.parse("tel:9629670097"));
				startActivity(callIntent);
			}
		});

		email.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// Intent i = new Intent(Intent.ACTION_SEND);
				// i.setType("message/rfc822");
				// i.putExtra(Intent.EXTRA_EMAIL, new String[] {
				// "vjohnvictor@hotmail.com" });
				// i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
				// i.putExtra(Intent.EXTRA_TEXT, "body of email");
				// try {
				// startActivity(Intent.createChooser(i, "Send mail..."));
				// } catch (android.content.ActivityNotFoundException ex) {
				// Toast.makeText(ContactActivity.this,
				// "There are no email clients installed.",
				// Toast.LENGTH_SHORT).show();
				// }

				// Intent intent = new Intent(Intent.ACTION_SEND);
				// intent.setType("text/plain");
				// intent.putExtra(Intent.EXTRA_EMAIL,
				// "vjohnvictor@hotmail.com");
				// startActivity(Intent.createChooser(intent, "Send Email"));
				Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "vjohnvictor@hotmail.com", null));
				startActivity(Intent.createChooser(emailIntent, "Send email..."));
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.contact, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		}
		return false;
	}
}
