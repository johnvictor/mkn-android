package com.mkn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.TextView;

import com.mkn.service.BitMapService;

public class Splash extends Activity {

	private TextView tv;
	private String[] mStrings;
	public static Integer[] thumbs = { R.drawable.artichoke, R.drawable.baby_carrots, R.drawable.babycorn, R.drawable.baby_potato, R.drawable.basil,
			R.drawable.beetroot, R.drawable.beetroot_with_leaf, R.drawable.broad_beans, R.drawable.broccoli, R.drawable.beans, R.drawable.brussell_sprouts,
			R.drawable.carrots, R.drawable.carrots, R.drawable.cauliflower, R.drawable.celery, R.drawable.chinese_cabbage, R.drawable.chow_chow,
			R.drawable.beans, R.drawable.dill, R.drawable.green_zucchini, R.drawable.green_cabbage, R.drawable.green_capsicum, R.drawable.green_lettuce,
			R.drawable.garlic, R.drawable.iceberg, R.drawable.jalapeno_chili, R.drawable.knolkhol, R.drawable.leeks, R.drawable.lemon_grass, R.drawable.methi,
			R.drawable.buttonmushroom, R.drawable.oregano, R.drawable.parsley, R.drawable.pink_lettuce, R.drawable.potato, R.drawable.red_cabbage,
			R.drawable.red_capsicum, R.drawable.radish, R.drawable.rosemary, R.drawable.spinach, R.drawable.spring_onion, R.drawable.sweet_corn,
			R.drawable.sweet_peas, R.drawable.radish, R.drawable.thyme, R.drawable.turnip, R.drawable.beans, R.drawable.white_radish,
			R.drawable.yellow_capsicum, R.drawable.yellow_zucchini

	};
	private ArrayList<Map<String, Object>> vegetable_list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spash);

		mStrings = getResources().getStringArray(R.array.vegetables_list);
		Arrays.sort(mStrings);
		tv = (TextView) findViewById(R.id.tv);

		vegetable_list = new ArrayList<>();

	}

	private final Handler handler = new Handler();

	private final Runnable startActivityRunnable = new Runnable() {

		@Override
		public void run() {
			tv.setVisibility(View.INVISIBLE);
			Intent intent = new Intent(Splash.this, MKN.class);
			finish();
			startActivity(intent);
		}
	};

	@Override
	protected void onResume() {
		super.onResume();

		AnimationSet set = new AnimationSet(true);

		Animation fadeIn = FadeIn(1000);
		fadeIn.setStartOffset(0);
		set.addAnimation(fadeIn);

		Animation fadeOut = FadeOut(1000);
		fadeOut.setStartOffset(2000);
		set.addAnimation(fadeOut);

		tv.startAnimation(set);

		handler.postDelayed(startActivityRunnable, 3000);

		for (int i = 0; i < mStrings.length; i++) {
			Map<String, Object> item = new HashMap<>();

			Bitmap bm = BitMapService.decodeFile(this, thumbs[i]);
			item.put("name", mStrings[i]);
			item.put("price", "Rs 50/kg");
			item.put("thumb", bm);

			vegetable_list.add(item);
		}
		MKN.vegetable_list = vegetable_list;

	}

	public void onPause() {
		super.onPause();
		handler.removeCallbacks(startActivityRunnable);
	}

	private Animation FadeIn(int t) {
		Animation fade;
		fade = new AlphaAnimation(0.0f, 1.0f);
		fade.setDuration(t);
		fade.setInterpolator(new AccelerateInterpolator());
		return fade;
	}

	private Animation FadeOut(int t) {
		Animation fade;
		fade = new AlphaAnimation(1.0f, 0.0f);
		fade.setDuration(t);
		fade.setInterpolator(new AccelerateInterpolator());
		return fade;
	}
}
