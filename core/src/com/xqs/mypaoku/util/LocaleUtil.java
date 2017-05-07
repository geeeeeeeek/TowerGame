package com.xqs.mypaoku.util;

import com.xqs.mypaoku.res.Res;

/**
 * Created by Administrator on 2017/5/7 0007.
 */

public class LocaleUtil {

    // 0:english  1:chinese  2:tw
    public static void initText(int locale){

        if(locale==0){
            Res.text_startgame = "Play";
            Res.text_help = "Help";
            Res.text_quit = "Quit";
            Res.text_no_bullet = "No bullets";
        } else if(locale == 1){
            Res.text_startgame = "开始游戏";
            Res.text_help = "帮助";
            Res.text_quit = "退出";
            Res.text_no_bullet = "子弹不够了！";
        }
    }
}
