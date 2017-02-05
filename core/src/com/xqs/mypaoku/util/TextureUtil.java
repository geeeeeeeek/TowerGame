package com.xqs.mypaoku.util;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Administrator on 2017/1/19 0019.
 */

public class TextureUtil {


    /**
     * 转一维数组
     */
    public static TextureRegion[] getTextureRegions(TextureRegion region,int rows,int cols){

        int perCellWidth =  region.getRegionWidth() / cols;     // 单元格的宽度
        int perCellHeight =  region.getRegionHeight() / rows;   // 单元格的高度

        TextureRegion[][] cellRegions = region.split(perCellWidth,perCellHeight);

        // 把二维数组变为一维数组
        TextureRegion[] walkFrames = new TextureRegion[rows * cols];
        int index = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                walkFrames[index++] = cellRegions[row][col];
            }
        }
        return walkFrames;
    }
}
