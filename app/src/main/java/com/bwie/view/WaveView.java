package com.bwie.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wangbingjun on 2018/10/8.
 */

public class WaveView extends View {

    //定义需要用的绘图的画笔和常量等
    private float fai = 0;
    private Paint paint1;
    private Paint paint2;
    private Path path1;
    private Path path2;
    private DrawFilter drawFilter;

    public interface OnWaveViewListener{
        void onChanged(float y);
    }
    private OnWaveViewListener listener;

    public void setOnWaveViewListener(OnWaveViewListener listener) {
        this.listener = listener;
    }

    public WaveView(Context context) {
        this(context,null);
    }

    public WaveView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //画笔创建2个,用来画波浪线的
        paint1 = new Paint();
        paint1.setColor(Color.RED);
        paint1.setAntiAlias(true);
        paint1.setStrokeWidth(5);
        paint1.setStyle(Paint.Style.FILL);

        paint2 = new Paint();
        paint2.setColor(Color.RED);
        paint2.setAntiAlias(true);
        paint2.setStrokeWidth(5);
        paint2.setStyle(Paint.Style.FILL);
        paint2.setAlpha(60);

        //两条线的路径
        path1 = new Path();
        path2 = new Path();

//      给个滤镜
        drawFilter = new PaintFlagsDrawFilter(0,Paint.FILTER_BITMAP_FLAG | Paint.ANTI_ALIAS_FLAG);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        double Ω = 2 * Math.PI / getMeasuredWidth();

        canvas.setDrawFilter(drawFilter);//将滤镜填进去
        fai -= 0.1f;//给个度数

        //获取高度
        int A = getMeasuredHeight() / 2;
        //重置滤镜
         path1.reset();
         path2.reset();

         path1.moveTo(getLeft(),getBottom());
         path2.moveTo(getLeft(),getBottom());

         //画
        for (int x = 0; x <= getMeasuredWidth() ; x += 20) {
            //y = Asin(Ωx+ψ)+k

            float y1 = A * (float)Math.sin(Ω * x +fai) + A;
            float y2 = -A * (float)Math.sin(Ω * x +fai) + A;

            if(x> getMeasuredWidth() / 2 -10 && x< getMeasuredWidth() / 2 +10){
                listener.onChanged(y2);
            }
            path1.lineTo(x,y1);
            path2.lineTo(x,y2);
        }
        //停止的地方
        path1.lineTo(getWidth(),getBottom());
        path2.lineTo(getWidth(),getBottom());

        //画的线的路径
        canvas.drawPath(path1,paint1);
        canvas.drawPath(path2,paint2);

        postInvalidateDelayed(88);//画

    }
}
