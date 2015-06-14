package com.mkn;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomAdapter extends BaseAdapter implements OnItemClickListener, Filterable {
	private LayoutInflater inflater;
	private Context context;
	private List<Map<String, Object>> vegetableList;
	private List<Map<String, Object>> orig;

	public CustomAdapter(Context context, int textViewResourceId, List<Map<String, Object>> vegetableList) {

		this.context = context;
		this.vegetableList = vegetableList;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	// class for caching the views in a row
	private class ViewHolder {
		ImageView vegetable_thumb;
		TextView vegetable_name, vegetable_price;

	}

	ViewHolder viewHolder;

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.vegetables_list_item, null);
			viewHolder = new ViewHolder();

			// cache the views
			viewHolder.vegetable_thumb = (ImageView) convertView.findViewById(R.id.vegetable_thumb);
			viewHolder.vegetable_name = (TextView) convertView.findViewById(R.id.tv_vegetable_name);
			viewHolder.vegetable_price = (TextView) convertView.findViewById(R.id.tv_vegetable_price);

			// link the cached views to the convertview
			convertView.setTag(viewHolder);

		} else
			viewHolder = (ViewHolder) convertView.getTag();

		// int photoId = (Integer) searchResults.get(position).get("photo");

		// set the data to be displayed
		// viewHolder.vegetable_thumb.setImageDrawable(context.getResources().getDrawable(photoId));
		viewHolder.vegetable_name.setText((CharSequence) vegetableList.get(position).get("name"));
		viewHolder.vegetable_price.setText((CharSequence) vegetableList.get(position).get("price"));

		Bitmap bm = (Bitmap) vegetableList.get(position).get("thumb");
		viewHolder.vegetable_thumb.setImageBitmap(bm);

		// return the view to be displayed
		return convertView;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Toast.makeText(context, (CharSequence) vegetableList.get(position).get("name"), Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(context, VegetableDetail.class);
		intent.putExtra("name", (String) vegetableList.get(position).get("name"));
		intent.putExtra("price", (String) vegetableList.get(position).get("price"));
		intent.putExtra("position", position);

		context.startActivity(intent);

	}

	@Override
	public Filter getFilter() {
		return new Filter() {

			@Override
			protected FilterResults performFiltering(CharSequence constraint) {
				final FilterResults oReturn = new FilterResults();
				final List<Map<String, Object>> results = new ArrayList<>();
				if (orig == null)
					orig = vegetableList;
				if (constraint != null) {
					if (orig != null && orig.size() > 0) {
						for (final Map<String, Object> g : orig) {
							if (String.valueOf(g.get("name")).toLowerCase().contains(constraint.toString()))
								results.add(g);
						}
					}
					oReturn.values = results;
				}
				return oReturn;
			}

			@SuppressWarnings("unchecked")
			@Override
			protected void publishResults(CharSequence constraint, FilterResults results) {
				vegetableList = (ArrayList<Map<String, Object>>) results.values;
				notifyDataSetChanged();
			}
		};
	}

	public void notifyDataSetChanged() {
		super.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return vegetableList.size();
	}

	@Override
	public Object getItem(int position) {
		return vegetableList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

}