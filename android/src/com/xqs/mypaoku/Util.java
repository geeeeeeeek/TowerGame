package com.xqs.mypaoku;

import android.content.Context;
import android.util.Log;

import java.util.Locale;

/**
 * Created by Administrator on 2017/5/7 0007.
 */

public class Util {

    public static int getLanguage(Context context){

        String country = Locale.getDefault().getCountry();
        String language = Locale.getDefault().getLanguage();

        Log.w("Util", "country-->"+country);
        Log.w("Util", "language-->"+language);

        if(country.equals("US")){
            return 0;
        }

        if(country.equals("CN")){
            return 1;
        }


        return 0; // 默认英文

    }
}
