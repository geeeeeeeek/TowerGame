package com.xqs.mypaoku;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.xqs.mypaoku.MyPaokuGame;
import com.facebook.FacebookSdk;

public class AndroidLauncher extends AndroidApplication implements ActionService {
	public static final String TAG = "AndroidLaucher";
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.w(TAG,"-->onCreate");

		int language = Util.getLanguage(this);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new MyPaokuGame(this,language), config);
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
		// https://developers.facebook.com/docs/sharing/android

		ShareDialog shareDialog = new ShareDialog(this);
		ShareLinkContent linkContent = new ShareLinkContent.Builder()
				.setContentUrl(Uri.parse("https://play.google.com/store/apps/details?id=com.xqs.mypaoku")).build();
		shareDialog.show(linkContent);
	}
}
