package com.xqs.mypaoku;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.xqs.mypaoku.MyPaokuGame;

public class AndroidLauncher extends AndroidApplication implements ActionService {
	public static final String TAG = "AndroidLaucher";
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.w(TAG,"-->onCreate");
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new MyPaokuGame(this), config);
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
		android.os.Process.killProcess(android.os.Process.myPid());
		System.exit(0);
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			return true;
		}
		return false;
	}

	@Override
	public void dosomething() {
		Intent intent=new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
		intent.putExtra(Intent.EXTRA_TEXT, "It's a good game");
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(Intent.createChooser(intent, "Share"));
	}
}
