package com.example.home.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Jason
 * @version 1.0
 * @date 2017/9/15 0015
 */

public class MyView extends View {
    private Paint paint;
    private Bitmap mFactory;
    /**
     * 刻度尺的个数
     */
    private int scale = 15;
    /**
     * 刻度尺的倍数
     */
    private int multiple = 5;
    /**
     * 绘制刻度的初始x值
     */
    int maxX = 170;
    /**
     * 绘制刻度的初始值y
     */
    int y1 = 60;
    /**
     * 刻度上面的值
     */
    String[] values = {" 50", "100", "150"};
    float max = 150.0f;


    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        mFactory = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.yellowup);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.parseColor("#27525C"));
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        canvas.drawCircle(100, 100, 50, paint);
        paint.setTextSize(14f);
        paint.setStrokeWidth(2f);
        int x = maxX;
        int j = 0;//刻度上面的值的索引
        for (int i = 1; i <= scale; i++) {
            if (i % multiple == 0) {
                y1 = 45;
                paint.setColor(Color.parseColor("#0099e3"));
                canvas.drawText(values[j], x - 25, 50, paint);
                j++;
            }
            paint.setColor(Color.parseColor("#7196A4"));
            canvas.drawLine(x, y1, x, 75, paint);
            if (i != scale) {
                x += 30;
            }
            y1 = 60;
        }
        paint.setTextSize(20f);
        canvas.drawText("℃", x + 30, 65, paint);


        paint.setColor(Color.parseColor("#27525C"));
        RectF oval2 = new RectF(100, 80, x + 30, 120);// 设置个新的长方形
        canvas.drawRoundRect(oval2, 20, 20, paint);//第二个参数是x半径，第三个参数是y半径


        RectF oval1 = new RectF(130, 90, x + 20, 110);// 设置个新的长方形
        paint.setColor(Color.parseColor("#D6ECF6"));
        canvas.drawRoundRect(oval1, 10, 10, paint);//第二个参数是x半径，第三个参数是y半径


        Shader shader = new LinearGradient(100, 120, 90, 140, Color.parseColor("#F9D922"), Color.parseColor("#ECA118"), Shader.TileMode.CLAMP);
        paint.setShader(shader);
        canvas.drawCircle(100, 100, 40, paint);


        paint.setShader(null);

        float offset = temperature / max;


        float offsetX = maxX + offset * (x-maxX) ;


        RectF oval3 = new RectF(139, 90, offsetX, 110);// 设置个新的长方形
        paint.setColor(Color.parseColor("#F9DC22"));
        canvas.drawRect(oval3, paint);
        canvas.drawBitmap(mFactory, null, oval3, paint);
        canvas.drawText(String.valueOf(offsetX) + "xx" + String.valueOf(x), 300, 300, paint);
    }


    private int temperature = 0;

    public void setTemperature(int temperature) {
        this.temperature = temperature;
        invalidate();
    }
}
