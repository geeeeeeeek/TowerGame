package com.xqs.mypaoku.util;

import com.badlogic.gdx.Gdx;

/**
 * Created by Administrator on 2017/2/12 0012.
 */

public class Util {

    public static boolean debug=true;


    /**
     * 日志打印
     */
    public static void log(String tag,String message){
        if(debug) {
            Gdx.app.log(tag, message);
        }
    }
}
