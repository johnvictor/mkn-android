package com.mkn.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitMapService {
	// decodes image and scales it to reduce memory consumption
	public static Bitmap decodeFile(Context context, int id) {
		// Bitmap original =
		// BitmapFactory.decodeResource(context.getResources(), id);
		// ByteArrayOutputStream out = new ByteArrayOutputStream();
		// original.compress(Bitmap.CompressFormat.PNG, 100, out);
		// Bitmap decoded = BitmapFactory.decodeStream(new
		// ByteArrayInputStream(out.toByteArray()));
		//
		// final BitmapFactory.Options options = new BitmapFactory.Options();
		// options.inSampleSize = 8;
		//
		// return decoded;
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 6;

		Bitmap bm = BitmapFactory.decodeResource(context.getResources(), id, options);
		return bm;
	}
}
