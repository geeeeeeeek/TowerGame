package com.xqs.mypaoku.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.xqs.mypaoku.MyPaokuGame;

public class DesktopLauncher {


	public static final float PIX_WIDTH = 854;

	public static final float PIX_HEIGHT = 480;



	/** 适当改变缩放比以适应自己的电脑屏幕 */
	public static final float SCALE = 1.0F;


	public static void main (String[] args) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = (int) (PIX_WIDTH * SCALE);         		// 窗口宽度
		config.height = (int) (PIX_HEIGHT * SCALE);	            // 窗口高度

		config.resizable = false;				                 // 窗口设置为大小不可改变

		new LwjglApplication(new MyPaokuGame(), config);
	}
}
