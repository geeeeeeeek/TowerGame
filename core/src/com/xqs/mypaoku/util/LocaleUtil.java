package com.xqs.mypaoku.util;

import com.xqs.mypaoku.res.Res;

/**
 * Created by Administrator on 2017/5/7 0007.
 */

public class LocaleUtil {

    // 0:english  1:chinese
    public static void initText(int locale){

        if(locale==0){
            Res.text_startgame = "Play";
            Res.text_help = "Help";
            Res.text_quit = "Quit";
            Res.text_no_bullet = "No bullets";
            Res.text_help_content = "Mengcai Tower Defender is the lastest version of the tower \n" +
                                    "defense Mobile Games. The game has a variety of different \n" +
                                    "functions of the turret and shells,dozens of beautiful monsters.\n" +
                                    "The game to tower defense as the core, composite war strategy,\n" +
                                    "simulation of mobile games, picture tide explosion Meng Q,\n"+
                                    "achievements to share new social, level design easy puzzle,\n"+
                                    "strategy play freely controllable. In order to get a super \n" +
                                    "great experience, and partners together set foot on the \n" +
                                    "mysterious journey. Let's go.\n";
        } else if(locale == 1){
            Res.text_startgame = "开始游戏";
            Res.text_help = "关于";
            Res.text_quit = "退出";
            Res.text_no_bullet = "子弹不够了！";
            Res.text_help_content = "萌菜塔防是萌菜系列最新推出的横版闯关塔防手游。\n"+
                                    "游戏拥有多种不同功能的炮塔和炮弹、几十种精美的\n"+
                                    "怪物。该游戏以塔防为核心，复合战争策略，模拟经营\n"+
                                    "的手机游戏，画面潮爆萌Q，成就分享新社交，关卡设计\n"+
                                    "轻松益智，策略玩法自由可控。给玩家带来不一样的塔防\n"+
                                    "闯关体验，快和小伙伴一起踏上";

        }
    }
}
