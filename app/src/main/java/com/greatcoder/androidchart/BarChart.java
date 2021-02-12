package com.greatcoder.androidchart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BarChart extends View {

    private List<ChartModelData> lsData = new ArrayList<>();

    private Paint lingPaint;
    private Paint chartPaint;
    private Paint textPaint;
    private int measureHeight;
    private int measuredWidth;

    //轴线与试图的距离
    private int linePadding = 30;
    //轴宽
    private int lingWidth = 8;
    //间隔大小
    private int padding = 20;

    //管理是否显示当前数据的数值
    private boolean isShowLabel = true;

    private int minValue = 0;

    public void bindDataSource(List<ChartModelData> lsData) {
        this.lsData = lsData;
        minValue = lsData.get(0).getValue();
        for (ChartModelData item : lsData) {
            if (item.getValue() < minValue)
                minValue = item.getValue();
        }
    }

    public void setLingWidth(int lingWidth) {
        this.lingWidth = lingWidth;
    }

    public void setPadding(int padding) {
        if (padding > 15)
            this.padding = padding;
        else
            this.padding = 20;
    }

    public void setLinePadding(int linePadding) {

        if (linePadding <= 20)
            this.linePadding = linePadding;
        else
            this.linePadding = 20;
    }

    public void setShowLabel(boolean showLabel) {
        isShowLabel = showLabel;
    }

    public BarChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        lingPaint = new Paint();
        lingPaint.setColor(Color.RED);
        lingPaint.setAntiAlias(true);
        lingPaint.setStrokeWidth(lingWidth);

        chartPaint = new Paint();
        chartPaint.setAntiAlias(true);

        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(32f);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        measuredWidth = getMeasuredWidth();
        measureHeight = this.getMeasuredHeight();
    }

    private int max = 0;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(0, 0);

        //绘制x轴
        canvas.drawLine(0, measureHeight - linePadding - 40f, measuredWidth, measureHeight - linePadding - 40f, lingPaint);
        //绘制y轴
        canvas.drawLine(linePadding, 0, linePadding, measureHeight, lingPaint);

        //我们需要的柱状条数 (这个数据将有数据源决定)
        int totalCount = lsData.size();

        int eachWidth = (measuredWidth - linePadding - padding) / totalCount;


        lsData.forEach(a -> {
            if (a.getValue() > max)
                max = a.getValue();
        });

        for (int i = 0; i < totalCount; i++) {
            int value = lsData.get(i).getValue();
            chartPaint.setColor(Color.parseColor(ColorUtil.getRandomColor()));
            canvas.drawRect((i * eachWidth) + padding + linePadding , measureHeight - (linePadding + value), ((i * eachWidth) + padding + linePadding) + eachWidth - (padding / 2), measureHeight - linePadding - lingWidth - 40f, chartPaint);


            String name=lsData.get(i).getName();

            canvas.drawText(name,((i * eachWidth) + padding + linePadding) + (eachWidth / 2),
                    this.measureHeight - 20,textPaint);


            if (isShowLabel)
                canvas.drawText(String.valueOf(value), ((i * eachWidth) + padding + linePadding) + (eachWidth / 2), measureHeight - (linePadding + value) - 20, textPaint);
        }
    }
}
