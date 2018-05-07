package com.example.admin.touying.view.news;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.admin.touying.R;
import com.example.admin.touying.SizeUtil;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Admin on 2018/5/5.
 * 公司：铭轩科技
 */

public class WatchBoardView extends View {
    Paint hourWheel,minuteWheel,secondWheel,bigRound,smallRound,longLine,shortLine,hourText;
    private int  hourWheelBorderWidth = 5;
    private int  minuteWheelBorderWidth = 10;
    private int  secondWheelBorderWidth = 15;
    private int  hourWheelColor = Color.RED;
    private int  minuteWheelColor = Color.YELLOW;
    private int  secondWheelColor = Color.BLUE;
    private int  longLineBorderWidth;
    private int  shortLineBorderWidth;
    private int  longLineColor = Color.BLACK;
    private int  shortLineColor = Color.GRAY;
    private int  bigRoundColor = Color.WHITE;
    private int  smallRoundColor= Color.RED;
    private int  hourTextColor = Color.BLACK;
    private int  hourTextSize;
    int millSecond,second,minute,hour;

    public WatchBoardView(Context context) {
        super(context);
    }

    public WatchBoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public WatchBoardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WatchBoardView);
        hourWheelBorderWidth = (int) typedArray.getDimension(R.styleable.WatchBoardView_hourWheelBorderWidth,hourWheelBorderWidth);
        minuteWheelBorderWidth = (int) typedArray.getDimension(R.styleable.WatchBoardView_minuteWheelBorderWidth,minuteWheelBorderWidth);
        secondWheelBorderWidth = (int) typedArray.getDimension(R.styleable.WatchBoardView_secondWheelBorderWidth,secondWheelBorderWidth);
        hourWheelColor = typedArray.getColor(R.styleable.WatchBoardView_hourWheelColor,hourWheelColor);
        minuteWheelColor = typedArray.getColor(R.styleable.WatchBoardView_hourWheelColor,minuteWheelColor);
        secondWheelColor = typedArray.getColor(R.styleable.WatchBoardView_hourWheelColor,secondWheelColor);
        longLineBorderWidth = (int) typedArray.getDimension(R.styleable.WatchBoardView_longLineBorderWidth,longLineBorderWidth);
        shortLineBorderWidth = (int) typedArray.getDimension(R.styleable.WatchBoardView_shortLineBorderWidth,shortLineBorderWidth);
        longLineColor = typedArray.getColor(R.styleable.WatchBoardView_longLineColor,longLineColor);
        shortLineColor = typedArray.getColor(R.styleable.WatchBoardView_shortLineColor,shortLineColor);
        bigRoundColor= typedArray.getColor(R.styleable.WatchBoardView_bigRoundColor,bigRoundColor);
        smallRoundColor= typedArray.getColor(R.styleable.WatchBoardView_smallRoundColor,smallRoundColor);
        hourTextColor = typedArray.getColor(R.styleable.WatchBoardView_hourTextColor,hourTextColor);
        hourTextSize = typedArray.getDimensionPixelSize(R.styleable.WatchBoardView_hourTextSize,hourTextSize);

        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(width>height?height:width,width>height?height:width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //获取时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        millSecond = calendar.get(Calendar.MILLISECOND);
        second = calendar.get(Calendar.SECOND);
        minute = calendar.get(Calendar.MINUTE);
        hour = calendar.get(Calendar.HOUR);

//        if(){
//
//        }
        int radius = getWidth() / 2 ;//半径
        //大圆
        canvas.drawCircle(radius,getHeight() / 2,radius - 5,bigRound);

        for (int i = 0; i < 60; i++) {
            if(i%5==0){
                //长刻度
                canvas.drawLine(radius,getHeight()/2 - radius + 15 ,radius, getHeight()/2 - radius + 65,longLine);

                //刻度数字
                String numb = "" + (i/5==0?12:(i/5));
                Rect textBound = new Rect();
                hourText.getTextBounds(numb, 0, numb.length(), textBound);
                int dx = getWidth()/2 - textBound.width()/2;
                //基线
                Paint.FontMetricsInt fontMetricsInt = hourText.getFontMetricsInt();
                int dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
                int baseLine = getHeight()/2  + dy;

                canvas.save();
//                canvas.translate(0, -radius + DptoPx(5)  + (textBound.bottom - textBound.top));
//                canvas.rotate(-6 * i);
                canvas.drawText(numb,dx, baseLine - radius + 95,hourText);
                canvas.restore();
            }else {
                //短刻度
                canvas.drawLine(radius,getHeight()/2 - radius + 15 ,radius, getHeight()/2 - radius + 45,shortLine);
            }
            canvas.rotate(6,radius,getHeight()/2);
        }
        canvas.restore();

        //时针
        canvas.save();
        canvas.rotate((hour % 12) * 360 / 12,radius,getHeight()/2);
        canvas.drawLine(radius,getHeight()/2 ,radius, getHeight()/2 - radius + 125,hourWheel);
        canvas.restore();
        // 分针
        canvas.save();
        canvas.rotate(minute * 360 / 60,radius,getHeight()/2);
        canvas.drawLine(radius,getHeight()/2 ,radius, getHeight()/2 - radius + 145,minuteWheel);
        canvas.restore();
        // 秒针
        canvas.save();
        canvas.rotate(second * 360 / 60,radius,getHeight()/2);
        canvas.drawLine(radius,getHeight()/2 ,radius, getHeight()/2 - radius + 155,secondWheel);
        canvas.restore();

        //小圆
        canvas.drawCircle(radius,getHeight() / 2,20,smallRound);
        canvas.restore();

        //刷新
        postInvalidateDelayed(1000);
    }

    private void initPaint() {
        hourWheel = new Paint();
        hourWheel.setColor(hourWheelColor);
        hourWheel.setAntiAlias(true);
        hourWheel.setStyle(Paint.Style.FILL);
        hourWheel.setStrokeWidth(hourWheelBorderWidth);

        minuteWheel = new Paint();
        minuteWheel.setColor(minuteWheelColor);
        minuteWheel.setAntiAlias(true);
        minuteWheel.setStyle(Paint.Style.FILL);
        minuteWheel.setStrokeWidth(minuteWheelBorderWidth);

        secondWheel = new Paint();
        secondWheel.setColor(secondWheelColor);
        secondWheel.setAntiAlias(true);
        secondWheel.setStyle(Paint.Style.FILL);
        secondWheel.setStrokeWidth(secondWheelBorderWidth);

        bigRound = new Paint();
        bigRound.setStrokeWidth(5);
        bigRound.setStyle(Paint.Style.FILL);
        bigRound.setAntiAlias(true);
        bigRound.setColor(bigRoundColor);

        smallRound = new Paint();
        smallRound.setStrokeWidth(5);
        smallRound.setStyle(Paint.Style.FILL);
        smallRound.setAntiAlias(true);
        smallRound.setColor(smallRoundColor);

        longLine = new Paint();
        longLine.setStrokeWidth(8);
        longLine.setAntiAlias(true);
        longLine.setColor(longLineColor);

        shortLine = new Paint();
        shortLine.setStrokeWidth(5);
        shortLine.setAntiAlias(true);
        shortLine.setColor(shortLineColor);

        hourText = new Paint();
        hourText.setTextSize(45);
        hourText.setAntiAlias(true);
        hourText.setColor(hourTextColor);
    }

    //Dp转px
    private float DptoPx(int value) {
        return SizeUtil.Dp2Px(getContext(), value);
    }

    //sp转px
    private float SptoPx(int value) {
        return SizeUtil.Sp2Px(getContext(), value);
    }

}
