package com.yekong.exercise_1.entity;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * Created by xigua on 2017/9/5.
 */

public class MyItem {
    private Rect srcRect;//图像大小
    private Rect destRect;//绘制位置
    private Rect moveRect;//移动后点击时计算用的
    private float[] point = new float[4];//坐标点记录
    private int index;//下标
    private Bitmap bitmap;//绘制图像
    private float[] offsetX;//偏移量,X轴的
    private int offsetY;//偏移量,Y轴的,判断为第几根线区
    private int bitmapHeight,bitmapWidth;//绘制的高度宽度
    private int left,right,top,bottom;
    private String textDest;
    private RectF destBgRectf;//背景矩阵
    private int spanLine = 1;//跨行
    private int drawType;//默认为文字 1为图片
    private int resouceId;//图像 R id
    public MyItem() {
    }

    public MyItem(float[] offsetX, int offsetY) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public MyItem(int index, float[] offsetX, int offsetY) {
        this.index = index;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public int getDrawType() {
        return drawType;
    }

    public void setDrawType(int drawType) {
        this.drawType = drawType;
    }

    public int getResouceId() {
        return resouceId;
    }

    public void setResouceId(int resouceId) {
        this.resouceId = resouceId;
    }

    public int getSpanLine() {
        return spanLine;
    }

    public void setSpanLine(int spanLine) {
        this.spanLine = spanLine;
    }

    public RectF getDestBgRectf() {
        return destBgRectf;
    }

    public void setDestBgRectf(RectF destBgRectf) {
        this.destBgRectf = destBgRectf;
    }

    public String getTextDest() {
        return textDest;
    }

    public void setTextDest(String textDest) {
        this.textDest = textDest;
    }

    public Rect getMoveRect() {
        return moveRect;
    }

    public void setMoveRect(Rect moveRect) {
        this.moveRect = moveRect;
    }

    public float[] getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(float[] offsetX) {
        this.offsetX = offsetX;
    }

    public void _setOffsetX(float x){
        moveRect.left = left + (int) x;
        moveRect.right = right + (int) x;
//        Log.e("MyItem", "move left :   " +  moveRect.left  +   "   move right :   " +  moveRect.right );
    }
    public void _setOffsetY(float y){
        moveRect.top = top + (int) y;
        moveRect.bottom = bottom + (int) y;
//        Log.e("MyItem", "move top :   " +  moveRect.top  +   "   move bottom :   " +  moveRect.bottom );
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public int getBitmapHeight() {
        return bitmapHeight;
    }

    public void setBitmapHeight(int bitmapHeight) {
        this.bitmapHeight = bitmapHeight;
    }

    public int getBitmapWidth() {
        return bitmapWidth;
    }

    public void setBitmapWidth(int bitmapWidth) {
        this.bitmapWidth = bitmapWidth;
    }

    public Rect getSrcRect() {
        return srcRect;
    }

    public void setSrcRect(Rect srcRect) {
        this.srcRect = srcRect;
    }

    public Rect getDestRect() {
        return destRect;
    }

    public void setDestRect(Rect destRect) {
        this.destRect = destRect;
        if (left == 0 && right == 0) {
            left = destRect.left;
            right = destRect.right;
            top = destRect.top;
            bottom = destRect.bottom;
        }
        if (moveRect == null) {
            moveRect = new Rect(destRect);
        }
    }

    public float[] getPoint() {
        return point;
    }

    public void setPoint(float[] point) {
        this.point = point;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }


    public String getDestRectString() {
        return "left : " + destRect.left +
                "   top  :  " + destRect.top  +
                "   right   :  " + destRect.right +
                "   bottom  :  " + destRect.bottom;
    }
}
