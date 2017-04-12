package com.xqs.mypaoku;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.xqs.mypaoku.MyPaokuGame;

public class AndroidLauncher extends AndroidApplication {
	public static final String TAG = "AndroidLaucher";
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.w(TAG,"-->onCreate");
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new MyPaokuGame(), config);
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.w(TAG,"-->onResume");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.w(TAG,"-->onPause");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.w(TAG,"-->onDestroy");
		System.exit(0);
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			return true;
		}
		return false;
	}
}
