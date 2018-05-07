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
 * Created by Admin on 2018/5/3.
 * 公司：铭轩科技
 */

public class TestView extends View {
    Canvas canvas;
    Paint mInnerPaint;
    Paint mOutPaint,mTextPaint;
    private int mInnerColor = Color.RED;
    private int mOuterColor = Color.BLUE;
    private int mStepTextColor = Color.RED;
    private int mStepTextSize;
    private int stepTextColor;
    private int mBorderWidth = 20;
    private int mStepMax = 0;//总步数
    private int mCurrenStep = 0;//当前步数

    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //1.分析效果
        //2.确定自定义属性，编写attrs.xml
        //3.布局中用
        //4.自定义view中获取自定义属性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TestView);
        mOuterColor = array.getColor(R.styleable.TestView_outerColor,mOuterColor);
        mInnerColor = array.getColor(R.styleable.TestView_innerColor,mOuterColor);
        stepTextColor = array.getColor(R.styleable.TestView_stepTextColor,stepTextColor);
        mStepTextSize = array.getDimensionPixelSize(R.styleable.TestView_stepTextSize,mStepTextSize);
        mBorderWidth = (int) array.getDimension(R.styleable.TestView_borderWidth,mBorderWidth);
        array.recycle();
        //5.onMeasure
        //6.画图
    }

    /*
    测量view的大小
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(width>height?height:width,width>height?height:width);
    }

    /*
    实际绘制内容
    */
    @Override
    protected void onDraw(Canvas canvas) {
        initPaint();
        //画外圆弧
        int center = getWidth() / 2;
        int radius = getWidth() / 2 - mBorderWidth / 2 ;
        RectF rectF = new RectF(center - radius,center - radius ,center + radius,center + radius);

        canvas.drawArc(rectF,135,270,false,mOutPaint);

        if(mStepMax == 0) return;
//        画内圆弧
        float sweepAngle = (float) mCurrenStep / mStepMax;
        canvas.drawArc(rectF,135,sweepAngle * 270,false,mInnerPaint);

        //画文字
        String step = "" + mCurrenStep;
        Rect bounds = new Rect();
        mTextPaint.getTextBounds(step,0,step.length(),bounds);
        int dx = getWidth()/2 - bounds.width()/2;
        //基线
        Paint.FontMetricsInt fontMetricsInt = mTextPaint.getFontMetricsInt();
        int dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
        int baseLine = getHeight()/2 + dy;
        canvas.drawText(step,dx,baseLine,mTextPaint);
    }

    private void initPaint() {
        mOutPaint = new Paint();
        mOutPaint.setAntiAlias(true);
        mOutPaint.setColor(mOuterColor);
        mOutPaint.setStyle(Paint.Style.STROKE);//填充
        mOutPaint.setStrokeWidth(mBorderWidth);   //设置画笔宽度为10px
        mOutPaint.setStrokeCap(Paint.Cap.ROUND);

        mInnerPaint = new Paint();
        mInnerPaint.setAntiAlias(true);
        mInnerPaint.setColor(mInnerColor);
        mInnerPaint.setStyle(Paint.Style.STROKE);//填充
        mInnerPaint.setStrokeWidth(mBorderWidth);   //设置画笔宽度为10px
        mInnerPaint.setStrokeCap(Paint.Cap.ROUND);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(mStepTextColor);
    }

    public synchronized void setStepMax(int stepMax){
        this.mStepMax = stepMax;
    }

    public synchronized void setmCurrenStep(int currenStep){
        this.mCurrenStep = currenStep;
        invalidate();
    }

    /*
  确定view的大小
   */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    /*
    确定子布局大小
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}
