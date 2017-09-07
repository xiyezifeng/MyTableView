package com.yekong.exercise_1.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.widget.Scroller;

import com.yekong.exercise_1.entity.MyItem;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by xigua on 2017/9/4.
 */

public class MyView extends View implements View.OnTouchListener {

    private int winWidth , winHeight;
    private int leftMargin,rightMargin;
    private int timeValue = 4;
    private int hourWidth = 60*timeValue;//单小时长度
    private int minWidth = hourWidth / 60;//单分钟长度
    private int offsetY = 100; //第一条线据顶部距离
    private int offsetX = 100;//整体线据左边的距离
    private int timeCount = 5;//时间片段
    private int textSize = 35; //描述文字尺寸
    private int minScrollSize ; //最小滑动尺寸
    private boolean zeroX,zeroY;

    private boolean isShowTabLine = true;

    public interface OnItemClickListener{
        void onItemClick(MyItem item);
    }
    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    /**
     * 线间距
     */
    private int setp = 85;
    /**
     * 行数
     */
    private int lineCount = 11;
    /**
     * 描述文字的中线
     */
    private float[] dt_center = new float[2];

    /**
     * 文字中心线
     */
    private float[] t_center = new float[2];

    /**
     * 图像宽高
     */
    private int bitmapHeight,bitmapWidth;

    /**
     * 位图矩阵位置
     */
    private float[] c_bitmap = new float[4];
    /**
     * 图像
     */
    private Bitmap bitmap;
    /**
     * 位图位置
     */
    private Rect srcRect ,destRect;

    /**
     * 图像矩阵
     */
    private Matrix bitmapMatrix = new Matrix();


    /**
     * 画笔 画线的
     */
    private Paint linePaint;
    /**
     * 画笔 画图的
     */
    private Paint bitmapPaint;
    /**
     * 画笔 画文字的
     */
    private Paint textPaint;
    /**
     * 画笔 画描述文字的
     */
    private Paint textDescPaint;
    /**
     * 画笔 画文字背景的
     */
    private Paint textBgPaint;

    /**
     * 画笔 画行数标记数字
     */
    private Paint numberTextPaint;
    /**
     * 画笔 画行数标记背景
     */
    private Paint numberTextBgPaint;

    /**
     * 水平线的参数
     */
    private float[] c_line = new float[4];
    /**
     * 竖直线的参数
     */
    private float[] v_line = new float[4];
    /**
     * 滑动type
     * 0 未滑动
     * 1 横向
     * 2 竖直
     */
    private int scrollType = 0;

    /**
     * 记录最开始移动的位置，用于判断结束时是左/右移动
     */
    private float startX,startY;

    /**
     * 当前手指按下坐标/滑动位置
     */
    private float current_x,current_y;
    /**
     * 下一次移动后坐标
     */
    private float next_x,next_y;

    private boolean isClick = false;
//    private boolean isMove = false;
    private Scroller scroller;

    /**
     * 用于记录列表上的item
     */
    private List<MyItem> myItem = new ArrayList<>();

    /**
     * 用于记录哪行需要绘制标记
     */
    private List<Integer> lineItem = new ArrayList<>();

    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setShowTabLine(boolean showTabLine) {
        isShowTabLine = showTabLine;
        postInvalidate();
    }

    private void init() {
        setOnTouchListener(this);
        setClickable(true);
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        winWidth = wm.getDefaultDisplay().getWidth();
        winHeight = wm.getDefaultDisplay().getHeight();
        minScrollSize = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        scroller = new Scroller(getContext());
        if (linePaint == null) {
            linePaint = new Paint();
            linePaint.setColor(Color.parseColor("#FE4365"));
            linePaint.setAntiAlias(true);
            linePaint.setDither(true);
            linePaint.setFilterBitmap(true);
            linePaint.setStyle(Paint.Style.FILL);
        }
        if (bitmapPaint == null) {
            bitmapPaint = new Paint();
            bitmapPaint.setAntiAlias(true);
            bitmapPaint.setDither(true);
            bitmapPaint.setFilterBitmap(true);
        }
        if (textPaint == null) {
            textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            textPaint.setTextAlign(Paint.Align.CENTER);
            textPaint.setTextSize(50);
            textPaint.setColor(getResources().getColor(android.R.color.black));
            textPaint.setAntiAlias(true);
            textPaint.setDither(true);
        }
        if (textDescPaint == null) {
            textDescPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            textDescPaint.setTextAlign(Paint.Align.CENTER);
            textDescPaint.setTextSize(textSize);
            textDescPaint.setColor(getResources().getColor(android.R.color.holo_red_dark));
            textDescPaint.setAntiAlias(true);
            textDescPaint.setDither(true);
        }
        if (textBgPaint == null) {
            textBgPaint = new Paint();
            textBgPaint.setColor(getResources().getColor(android.R.color.white));
            linePaint.setAntiAlias(true);
            linePaint.setDither(true);
            linePaint.setFilterBitmap(true);
            linePaint.setStyle(Paint.Style.FILL);
        }

        if (numberTextPaint == null) {
            numberTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            numberTextPaint.setTextAlign(Paint.Align.CENTER);
            numberTextPaint.setTextSize(32);
            numberTextPaint.setColor(getResources().getColor(android.R.color.holo_red_dark));
            numberTextPaint.setAntiAlias(true);
            numberTextPaint.setDither(true);
        }
        if (numberTextBgPaint == null) {
            numberTextBgPaint = new Paint();
            numberTextBgPaint.setColor(getResources().getColor(android.R.color.white));
            numberTextBgPaint.setAntiAlias(true);
            numberTextBgPaint.setDither(true);
            numberTextBgPaint.setFilterBitmap(true);
            numberTextBgPaint.setStyle(Paint.Style.FILL);
        }
        /*if (myItem.isEmpty()) {
            MyItem item;
            for (int i = 0; i < 8; i++) {
                item = new MyItem(points[i],new Random().nextInt(4));
                item.setIndex(i);
                myItem.add(item);
            }
        }*/
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
//        Log.e("MainActivity", "dispatchTouchEvent    :       ");
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected boolean dispatchHoverEvent(MotionEvent event) {
//        Log.e("MainActivity", "dispatchHoverEvent    :       ");
        return super.dispatchHoverEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        Log.e("MainActivity", "onTouchEvent    :       ");
        return super.onTouchEvent(event);
    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        Log.e("MainActivity", "setOnTouchListener    :       ");
        super.setOnTouchListener(l);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        Log.e("MainActivity", "setOnClickListener    :       ");
        super.setOnClickListener(l);
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
//        Log.e("MainActivity", "onScrollChanged    :       ");
        super.onScrollChanged(l, t, oldl, oldt);
    }

    @Override
    public void scrollTo(@Px int x, @Px int y) {
        super.scrollTo(x, y);
//        Log.e("MainActivity", "scrollTo    :       ");
    }

    @Override
    public void scrollBy(@Px int x, @Px int y) {
        super.scrollBy(x, y);
//        Log.e("MainActivity", "scrollBy    :       ");
    }

    @Override
    public void setTranslationX(float translationX) {
        super.setTranslationX(translationX);
        Log.e("MainActivity", "setTranslationX    :       ");
    }

    @Override
    public void setTranslationY(float translationY) {
        super.setTranslationY(translationY);
        Log.e("MainActivity", "setTranslationY    :       ");
    }

    @Override
    public void setTranslationZ(float translationZ) {
        super.setTranslationZ(translationZ);
        Log.e("MainActivity", "setTranslationZ    :       ");
    }

    private float scrollXMax , scrollYMax;

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
//        Log.e("MainActivity", "onTouch    :       ");
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                current_x = motionEvent.getX();
                current_y = motionEvent.getY();
                startX = motionEvent.getX();
                startY = motionEvent.getY();
                int size = myItem.size();

                for (int i = 0; i < size; i++) {
                    if (myItem.get(i).getMoveRect().contains((int) current_x, (int) current_y)) {
                        isClick = true;
                    }
                }
            }
            break;
            case MotionEvent.ACTION_MOVE: {
                next_x = motionEvent.getX();
                next_y = motionEvent.getY();
                //一单view移动则，isclick = false
                if (hourWidth * (timeCount) + offsetX*2 > getWidth() && (scrollType == 0 || scrollType == 1)) {
                    //&& (scrollType == 0 || scrollType == 1)
                    if (Math.abs(next_x - current_x) > 8 ) {
                        //10个像素判断为移动
                        scrollType = 1;
                        if (scrollXMax == 0) {
                            scrollXMax = c_line[2] + offsetX  - getWidth();
                        }
                        if (next_x > current_x) {
                            //左移
                            if (getScrollX() <= 1) {
                                Log.e(TAG, "getScaleX():" + getScaleX());
                                smoothScrollTo(1,getScrollY());
                                zeroX = true;
                            } else {
                                scrollTo(getScrollX() - (int) (Math.abs(next_x - current_x)), getScrollY());
                                zeroX = false;
                            }
                            isClick = false;
                        } else {
                            //右移
                            if (getScrollX() >= (scrollXMax)) {
                                smoothScrollTo((int) (scrollXMax),getScrollY());
                                zeroX = false;
                            } else {
                                scrollTo(getScrollX() + (int) (Math.abs(next_x - current_x)), getScrollY());
                                zeroX = false;
                            }
                            isClick = false;
                        }
                    }
                }
                if ((setp * (lineCount) + offsetY ) > getHeight()&& (scrollType == 0 || scrollType == 2)) {
                    //&& (scrollType == 0 || scrollType == 2)
                    if(Math.abs(next_y - current_y) > 8   ){
                        scrollType = 2;
                        //10个像素判断为移动
                        if (scrollYMax == 0) {
                            scrollYMax = ((setp * (lineCount ) + offsetY + setp/2 ) - getHeight());
                        }
                        if (next_y > current_y) {
                            //上移
                            if (getScrollY() <= 0) {
                                smoothScrollTo(getScrollX(),0);
                                zeroY = true;
                            } else {
                                scrollTo(getScrollX() , getScrollY() - (int) (Math.abs(next_y - current_y)));
                                zeroY = false;
                            }
                            isClick = false;
                        } else {
                            //下移
                            //总长 - 偏移量
                            if (getScrollY() >= ( scrollYMax )) {
                                smoothScrollTo(getScrollX(), (int) scrollYMax );
                                zeroY = false;
                            } else {
                                scrollTo( getScrollX(), getScrollY() + (int) (Math.abs(next_y - current_y)));
                                zeroY = false;
                            }
                            isClick = false;
                        }
                    }
                }
                current_x = next_x;
                current_y = next_y;
            }
            break;
            case MotionEvent.ACTION_UP:
                scrollType = 0;
                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                if (!myItem.isEmpty()) {
                    int size = myItem.size();
                    for (int i = 0; i < size; i++) {
                        myItem.get(i)._setOffsetX(-getScrollX());
                        myItem.get(i)._setOffsetY(-getScrollY());
                    }
                    if (isClick) {
                        MyItem item;
                        for (int i = 0; i < size; i++) {
                            item = myItem.get(i);
                            if (item.getMoveRect().contains(x, y)) {
                                //Toast.makeText(getContext(), item.getTextDest(), Toast.LENGTH_SHORT).show();
                                if (listener != null) {
                                    listener.onItemClick(item);
                                }
                                isClick = false;
                            }
                        }
                    }
                }
                //X Y 轴归位
                if (zeroX) {
                    smoothScrollTo(0,getScrollY());
                }
                if (zeroY) {
                    smoothScrollTo(getScrollX(),0);
                }
                break;
        }
        return false;
    }

    /**
     * 缓慢滚动到指定位置
     * @param destX     指定滚动到的X轴位置
     * @param destY     指定滚动到的Y轴位置
     */
    private void smoothScrollTo(int destX, int destY) {
        //获取当前滚动的距离X
        int scrollX = getScrollX();
        //获取需要滚动的偏移量X
        int delta = destX - scrollX;
        //设置1000ms内滚动到delta位置，而效果就是慢慢滑动
        //获取当前滚动的距离Y
        int scrollY = getScrollY();
        //获取需要滚动的偏移量X
        int deltb = destY - scrollY;

        scroller.startScroll(scrollX, scrollY, delta, deltb , 500);
        invalidate();
    }

    /**
     * 持续滚动，实现慢慢滑动
     */
    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(),scroller.getCurrY());
            postInvalidate();
        }
    }

    public void setMyItem(List<MyItem> items , int tabCount , int lineCount ) {
        this.myItem = items;
        this.timeCount = tabCount;
        this.lineCount = lineCount;
        invalidate();
    }

    public void setLineItem(List<Integer> lineIndex){
        this.lineItem = lineIndex;
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (c_line[0] == 0) {
            c_line[0] = offsetX;
            c_line[1] = offsetY;
            c_line[2] = hourWidth * timeCount + offsetX;
            c_line[3] = offsetY;
        }
        if (isShowTabLine)
        drawLine(canvas);
        if (v_line[0] == 0) {
            int left = offsetX + hourWidth * 1;//X位置
            int top = offsetY;//从offsetX-0像素画起
            int right = offsetX + hourWidth * 1;
            int bottom = offsetY + setp * (lineCount);//超出0个像素
            v_line[0] = left;
            v_line[1] = top;
            v_line[2] = right;
            v_line[3] = bottom;
        }
        if (isShowTabLine)
        drawVerticalLine(canvas);
        MyItem item;
        for (int i = 0; i < myItem.size(); i++) {
            item = myItem.get(i);
            if (item.getDrawType() == 1) {
                if (item.getBitmap() == null) {
                    //生成图像
                    item.setBitmap(BitmapFactory.decodeResource(getResources(), item.getResouceId()));
                    item.setBitmapHeight(setp);
                    item.setBitmapWidth((int) (item.getOffsetX()[1] - item.getOffsetX()[0]));
                }
                /*if (item.getBitmapHeight() == 0) {
                    item.setBitmapHeight(setp);
                    item.setBitmapWidth((int) (item.getOffsetX()[1] - item.getOffsetX()[0]));
                }*/
                if (item.getSrcRect() == null) {
                    //图像大小
                    item.setSrcRect(new Rect(0, 0, item.getBitmap().getWidth(),item.getBitmap().getHeight()));
                }
            }else{
                if (item.getBitmapHeight() == 0) {
                    item.setBitmapHeight(setp);
                    item.setBitmapWidth((int) (item.getOffsetX()[1] - item.getOffsetX()[0]));
                }
                if (item.getSrcRect() == null) {
                    //图像大小
                    item.setSrcRect(new Rect(0, 0, (int) (item.getOffsetX()[1] - item.getOffsetX()[0]), setp));
                }
            }
            if (item.getDestRect() == null) {
                //绘制位置
                int top = setp * item.getOffsetY() + (int) c_line[1] + 1;
                int left = ((int) item.getOffsetX()[0] + offsetX);
                int right = ((int) item.getOffsetX()[1] + offsetX);
                int bottom = top + setp * item.getSpanLine();
                item.setDestRect(new Rect(left, top, right, bottom));
                item.setDestBgRectf(new RectF(left, top + 3, right, bottom - 3));
            }
            if (item.getDrawType() == 1) {
                drawBitmap(canvas,item);
            }else{
                dt_center[1] = (item.getDestRect().bottom - item.getDestRect().top) / 2 + textSize/2 + offsetY;
                drawDestText(canvas, item);
            }
        }
//        drawText(canvas);
        drawLineNumber(canvas);
    }

    /**
     * 标记半径
     */
    private int cirNumber = 30;
    /**
     * 绘制行数标记
     * @param canvas
     */
    private void drawLineNumber(Canvas canvas) {
        if (lineItem == null || lineItem.isEmpty()) {
            return;
        }
        //计算文字中心的位置
        int top = (offsetY + setp/2) - cirNumber;//中心位置往上半径
        int left = offsetX/2 - cirNumber;//中心位置往左半径
        int right = offsetX/2 + cirNumber;//中心位置往右半径
        int bottom = (offsetY + setp/2) + cirNumber;//中心位置往下半径
        int centerY = (int) ((bottom + top) / 2 + numberTextPaint.getTextSize()/2);
        //(top + bottom) /2  + (lineItem.get(i).intValue() * setp )  + numberTextPaint.getTextSize()/3
        for (int i = 0; i < lineItem.size(); i++) {
            canvas.drawRoundRect(new RectF(left,top + (lineItem.get(i).intValue() * setp ) , right,bottom  + (lineItem.get(i).intValue() * setp )), cirNumber, cirNumber, numberTextBgPaint);
            canvas.drawText((i+1)+"", (left+right)/2 ,centerY +  (lineItem.get(i).intValue() * setp ), numberTextPaint);
        }
    }


    /**
     * 绘制详细说明
     *
     * @param canvas
     * @param item
     */
    private void drawDestText(Canvas canvas, MyItem item) {
        if ((item.getOffsetX()[1] - item.getOffsetX()[0]) <= minWidth*3) {
            return;
        }
        //计算X轴像素宽度
        String str = calcText(item);
        float center = (item.getOffsetX()[0] + item.getOffsetX()[1]) / 2 + offsetX;
        canvas.drawRoundRect(item.getDestBgRectf(), setp / 2, setp / 2, textBgPaint);
        canvas.drawText(str, center, dt_center[1] + (item.getOffsetY() * setp), textDescPaint);
    }

    private String calcText(MyItem item) {
        //文字总长
        float measure = textDescPaint.measureText(item.getTextDest());
        //单个文字长度
        float textSize = textDescPaint.getTextSize();
        //算出文字容器总长
        float width = item.getOffsetX()[1] - item.getOffsetX()[0];
        StringBuffer buffer = new StringBuffer(item.getTextDest());
        if (measure >= width) {
            //总共存放文字数量
            int lenght = (int) ((width) / textSize);
            buffer.replace(lenght - 2, buffer.length(), "...");
        }
        return buffer.toString();
    }



    private void drawText(Canvas canvas) {
        for (int i = 0; i <= timeCount; i++) {
            String str;
            if (i < 10) {
                str = "0" + i;
            } else {
                str = "" + i;
            }
            canvas.drawText(str, t_center[0] + (i * hourWidth) + (offsetX), t_center[1] + offsetY - 8, textPaint);
        }
    }




    private void drawBitmap(Canvas canvas, MyItem item) {
        canvas.drawBitmap(item.getBitmap(), item.getSrcRect(), item.getDestRect(), bitmapPaint);
    }

    private void drawLine(Canvas canvas) {
        for (int i = 0; i < lineCount + 1; i++) {
            canvas.drawLine(c_line[0], c_line[1] + setp * i, c_line[2], c_line[3] + setp * i, linePaint);
        }
//          canvas.drawLine(c_line[0], c_line[1] + setp, c_line[2], c_line[3] + setp, linePaint);
//        canvas.drawLine(c_line[0], c_line[1] + setp*2, c_line[2], c_line[3] + setp*2, linePaint);
//        canvas.drawLine(c_line[0], c_line[1] + setp*3, c_line[2], c_line[3] + setp*3, linePaint);
//        canvas.drawLine(c_line[0], c_line[1] + setp*4, c_line[2], c_line[3] + setp*4, linePaint);
    }

    private void drawVerticalLine(Canvas canvas) {
        //0点和24点画线为25条，不画为23条`1
        canvas.drawLine(v_line[0]  - hourWidth , v_line[1] , v_line[2] - hourWidth , v_line[3], linePaint);
        for (int i = 0; i < timeCount; i++) {
            canvas.drawLine(v_line[0] + i * hourWidth, v_line[1], v_line[2] + i * hourWidth, v_line[3], linePaint);
        }
    }

    @Override
    public void setBackground(Drawable background) {
        super.setBackground(background);
        Log.e("MainActivity", "setBackground    :       ");
    }

    public MyItem createItem(int index, int lineIndex,  int startHour, int startMin, int endHour, int endMin  ) {
        //offset[0] 为起始时间, offset[1]为持续时间
        float[] offset = new float[2];
        float s_h = hourWidth * startHour;
        float s_m = minWidth * startMin;

        float e_h = hourWidth * endHour;
        float e_m = minWidth * endMin;

        offset[0] = s_h + s_m;
        offset[1] = e_h + e_m;
        MyItem myItem = new MyItem(index, offset, lineIndex);
        return myItem;
    }



    public int getTabWidth() {
        return hourWidth;
    }
}
