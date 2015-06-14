package com.mkn;

import java.util.List;
import java.util.Map;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

public class MKN extends ActionBarActivity implements SearchView.OnQueryTextListener {

	public static String LOG_TAG = "-------------->";

	private SearchView searchView;
	private ListView mListView;
	public static List<Map<String, Object>> vegetable_list;
	private CustomAdapter customAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_mkn);

		mListView = (ListView) findViewById(android.R.id.list);

		customAdapter = new CustomAdapter(this, R.layout.vegetables_list_item, vegetable_list);
		mListView.setAdapter(customAdapter);
		mListView.setOnItemClickListener(customAdapter);
		mListView.setTextFilterEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.options_menu, menu);

		// Associate searchable configuration with the SearchView
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		searchView = (SearchView) menu.findItem(R.id.search).getActionView();
		searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
		searchView.setIconifiedByDefault(false);
		searchView.setOnQueryTextListener(this);
		searchView.setSubmitButtonEnabled(true);

		return true;
	}

	@Override
	public boolean onQueryTextChange(String newText) {
		if (TextUtils.isEmpty(newText)) {
			mListView.clearTextFilter();
		} else {
			mListView.setFilterText(newText);
		}
		return true;
	}

	@Override
	public boolean onQueryTextSubmit(String query) {
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
		}
		return false;
	}

}
