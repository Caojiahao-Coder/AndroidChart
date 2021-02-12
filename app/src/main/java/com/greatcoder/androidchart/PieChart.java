package com.greatcoder.androidchart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Size;
import android.view.View;

import androidx.annotation.Nullable;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PieChart extends View {
    private Paint chartPaint = new Paint();
    private Paint valuePaint = new Paint();
    private Paint itemNamePaint = new Paint();

    private Size centerPoint;
    private int radius;


    private List<ChartModelData> lsMyData = new ArrayList<>();

    public void bindDataSource(List<ChartModelData> lsMyData) {
        this.lsMyData = lsMyData;
    }

    public PieChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //初始化一下我们的画笔
        chartPaint.setAntiAlias(true);
        valuePaint.setAntiAlias(true);
        chartPaint.setColor(Color.GREEN);
        valuePaint.setColor(Color.WHITE);
        valuePaint.setTextSize(32f);
        valuePaint.setTextAlign(Paint.Align.CENTER);

        itemNamePaint.setTextSize(48f);
        itemNamePaint.setAntiAlias(true);

    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        centerPoint = new Size(this.getMeasuredWidth() / 2, this.getMeasuredHeight() / 2);

        radius = getMeasuredWidth() > getMeasuredHeight() ? (getMeasuredHeight() / 2) - 20 : getMeasuredWidth() / 2 - 20;
    }

    float total = 0;
    float startPoints = 0;

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        @SuppressLint("DrawAllocation") RectF rectF = new RectF(-radius, -radius, radius, radius);

        float startItemTitle = -centerPoint.getHeight() + 50f;


        canvas.translate(centerPoint.getWidth(), centerPoint.getHeight());


        if (lsMyData.size() == 0) return;
        //这里需要计算一个总的大小
        lsMyData.forEach(a -> total += a.getValue());

        float eachValue = 360f / total;
        for (int i = 0; i < lsMyData.size(); i++) {
            int color = Color.parseColor(ColorUtil.getRandomColor());
            chartPaint.setColor(color);
            canvas.drawArc(rectF, startPoints, lsMyData.get(i).getValue() * eachValue, true, chartPaint);

            float textAngle = (startPoints) + (lsMyData.get(i).getValue() * eachValue) / 2;

            float x = (float) (radius / 2 * Math.cos(textAngle * Math.PI / 180));
            float y = (float) (radius / 2 * Math.sin(textAngle * Math.PI / 180));

            canvas.drawText(new DecimalFormat("0.0").format(lsMyData.get(i).getValue()), x, y, valuePaint);

            startPoints += lsMyData.get(i).getValue() * eachValue;

            canvas.drawText(lsMyData.get(i).getName() + " - " + lsMyData.get(i).getValue(), -centerPoint.getWidth() + 50, 50 * i + startItemTitle, itemNamePaint);

            canvas.drawRect(-centerPoint.getWidth() + 10, 50 * i + startItemTitle - 32, -centerPoint.getWidth() + 40, (50 * i + startItemTitle - 32) + 30, chartPaint);
        }


    }
}
