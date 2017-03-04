package com.xqs.mypaoku.res;

/**
 * 资源常量
 *
 */
public class Res {

    /** 世界宽高 */
    public static final float FIX_WORLD_HEIGHT=720;
    public static final float FIX_WORLD_WIDTH=1280;

    /** 帧率显示所需要的位图字体 文件路径 */
    public static final String FPS_BITMAP_FONT_PATH = "fps/fps24px.fnt";

    /**
     * 相关物理参数（调节物理参数可改变游戏难度）
     */
    public static class Physics {

        /** 水管和地板的移动速度, 单位: px/s */
        public static final float MOVE_VELOCITY = -150.0F;
    }

    /**
     * 纹理图集
     */
    public static class Atlas {

        /** 纹理图集 文件路径 */
        public static final String ATLAS_PATH = "atlas/images.atlas";

        /* 纹理图集中的小图名称 */


        public static final String IMAGE_PLAYER_SHOOT="player_shoot";

        public static final String IMAGE_ENEMY = "enemy";

        public static final String IMAGE_ENEMY_DACONG_WALK="dacong_walk";

        public static final String IMAGE_ENEMY_DACONG_DEAD="dacong_dead";


        public static final String IMAGE_ENEMY_DEAD="enemy_dead";

        public static final String IMAGE_BULLET="bullet";

        public static final String IMAGE_BULLET_2="bullet2";
    }


}




















