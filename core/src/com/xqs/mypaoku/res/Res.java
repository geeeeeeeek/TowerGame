package com.xqs.mypaoku.res;

/**
 * 资源常量
 */
public class Res {


    public static final float FIX_WORLD_HEIGHT = 720;
    public static final float FIX_WORLD_WIDTH = 1280;

    // 字体
    public static final String FPS_BITMAP_FONT_PATH = "font/my.fnt";

    // sounds
    public static class Audios{
        public static final String AUDIO_BASE_DIR	= "audio/";
        public static final String AUDIO_SCORE = AUDIO_BASE_DIR + "score.ogg";
        public static final String AUDIO_BG = AUDIO_BASE_DIR + "bg.mp3";
        public static final String AUDIO_PASS = AUDIO_BASE_DIR + "pass.mp3";
        public static final String AUDIO_GAMEOVER = AUDIO_BASE_DIR + "gameover.mp3";
    }

    // colors
    public static class Color{
        public static final String FONT_MAIN = "#97e021";
        public static final String FONT_LEVEL = "#328928";
    }


    // 图资源
    public static class Atlas {

        /**
         * 纹理图集 文件路径
         */
        public static final String ATLAS_PATH = "atlas/images.atlas";


        /* 主角 */
        public static final String IMAGE_PLAYER_STANDING = "player_standing";
        public static final String IMAGE_PLAYER_SHOOT = "player_shoot";


        /**
         * 暂停按钮
         **/
        public static final String IMAGE_NPC_BTN_PAUSE = "npc_btn_pause";

        // 生命
        public static final String IMAGE_PLAYER_LIFE = "player_life";


        // player子弹
        public static final String IMAGE_BULLET_ONE_FLY = "bullet_one_fly";
        public static final String IMAGE_BULLET_ONE_EXPLODE = "bullet_one_explode";

        public static final String IMAGE_BULLET_PLAYER_TWO_FLY = "bullet_four_fly";


        // 敌人子弹
        public static final String IMAGE_BULLET_TWO_FLY = "bullet_two_fly";
        public static final String IMAGE_BULLET_TWO_EXPLODE = "bullet_two_explode";

        public static final String IMAGE_BULLET_THREE_FlY = "bullet_three_fly";
        public static final String IMAGE_BULLET_THREE_EXPLODE = "bullet_three_explode";

        /* 大葱 */
        public static final String IMAGE_ENEMY_DACONG_WALK = "dacong_walk";
        public static final String IMAGE_ENEMY_DACONG_DEAD = "dacong_dead";

        /* 黄瓜 */
        public static final String IMAGE_ENEMY_HUANGGUA_WALK = "huanggua_walk";
        public static final String IMAGE_ENEMY_HUANGGUA_DEAD = "huanggua_dead";

        /* 菜花 */
        public static final String IMAGE_ENEMY_CAIHUA_WALK = "caihua_walk";
        public static final String IMAGE_ENEMY_CAIHUA_DEAD = "caihua_dead";

        /* 芋头 */
        public static final String IMAGE_ENEMY_YUTOU_WALK = "yutou_walk";
        public static final String IMAGE_ENEMY_YUTOU_DEAD = "yutou_dead";

        /* 辣椒 */
        public static final String IMAGE_ENEMY_LAJIAO_WALK = "lajiao_walk";
        public static final String IMAGE_ENEMY_LAJIAO_DEAD = "lajiao_dead";

        /* 木薯 */
        public static final String IMAGE_ENEMY_MUSHU_WALK = "mushu_walk";
        public static final String IMAGE_ENEMY_MUSHU_FIRE = "mushu_fire";
        public static final String IMAGE_ENEMY_MUSHU_DEAD = "mushu_dead";

        /* 蜗牛 */
        public static final String IMAGE_ENEMY_WONIU_WALK = "woniu_walk";
        public static final String IMAGE_ENEMY_WONIU_DEAD = "woniu_dead";

        /* 飞机 */
        public static final String IMAGE_ENEMY_PLANE_WALK = "plane_walk";
        public static final String IMAGE_ENEMY_PLANE_FIRE = "plane_fire";
        public static final String IMAGE_ENEMY_PLANE_DEAD = "plane_dead";

        public static final String IMAGE_ENEMY_DEAD = "enemy_dead";

        public static final String IMAGE_BULLET = "bullet";

        public static final String IMAGE_BULLET_2 = "bullet2";
    }


}




















