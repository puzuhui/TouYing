package com.example.admin.touying.view.news;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.admin.touying.R;

/**
 * Created by Admin on 2018/5/4.
 * 公司：铭轩科技
 */

public class SwipeRefreshView extends View {
    private int mSwipeTextColor;
    private int mSwipeTextSize;
    private int mInnerColor = Color.RED;
    private int mOuterColor = Color.BLUE;
    private int mBorderWidth = 20;
    int start = 50;
    int finish = 100;
    Paint innerPaint,outerPaint,textPaint;

    public SwipeRefreshView(Context context) {
        super(context);
    }

    public SwipeRefreshView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public SwipeRefreshView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SwipeRefreshView);
        mOuterColor = typedArray.getColor(R.styleable.SwipeRefreshView_swipeOuterColor,mOuterColor);
        mInnerColor = typedArray.getColor(R.styleable.SwipeRefreshView_swipeInnerColor,mOuterColor);
        mSwipeTextColor = typedArray.getColor(R.styleable.SwipeRefreshView_swipeTextColor,mSwipeTextColor);
        mSwipeTextSize = typedArray.getDimensionPixelSize(R.styleable.SwipeRefreshView_swipeTextSize,mSwipeTextSize);
        mBorderWidth = (int) typedArray.getDimension(R.styleable.SwipeRefreshView_swipeBorderWidth,mBorderWidth);

        typedArray.recycle();

        //内环 外环 数字
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(width>height?height:width , width>height?height:width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int center = getWidth() / 2;
        int radius = getWidth() / 2 - mBorderWidth / 2 ;
        RectF rectF = new RectF(center-radius,center-radius,center+radius,center+radius);

        //外环
        canvas.drawArc(rectF,0,360,false,outerPaint);

        //内环
        float swipe = (float) start / finish;
        canvas.drawArc(rectF,0,swipe * 360 ,false,innerPaint);

        //文字
        String swipeText = start +"%";
        Rect rect = new Rect();
        textPaint.getTextBounds(swipeText,0,swipeText.length(),rect);
        int sx = getWidth() / 2 - rect.width() / 2;
        Paint.FontMetricsInt fontMetricsInt = textPaint.getFontMetricsInt();
        int sy = (fontMetricsInt.bottom / 2 - fontMetricsInt.top / 2) - fontMetricsInt.bottom / 2;
        int baseLine = getHeight()/2 + sy;
        canvas.drawText(swipeText,sx,baseLine,textPaint);
    }

    public synchronized void setmSwipeStartText(int start){
        this.start = start;
        invalidate();
    }

    public synchronized void setSwipeFinishText(int finish){
        this.finish = finish;
    }

    private void initPaint() {
        innerPaint = new Paint();
        innerPaint.setStyle(Paint.Style.STROKE);
        innerPaint.setAntiAlias(true);
        innerPaint.setStrokeWidth(mBorderWidth);
        innerPaint.setColor(mInnerColor);

        outerPaint = new Paint();
        outerPaint.setStyle(Paint.Style.STROKE);
        outerPaint.setAntiAlias(true);
        outerPaint.setStrokeWidth(mBorderWidth);
        outerPaint.setColor(mOuterColor);

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(mOuterColor);
    }
}
