package com.example.wuht.spinningwheel;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wuht on 2016/9/27.
 */

public class WheelView extends View {

    private Paint
            mPaint = new Paint();
    private int mWidth, mHeight;
    private Path mCirclePath1, mCirclePath2;
    private PathMeasure mPathMeasure1, mPathMeasure2, mPathMeasure;
    private float mPathLength;
    private float alpha;
    private ValueAnimator valueAnimator;

    public WheelView(Context context) {
        this(context, null);
        init();
    }

    public WheelView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    public WheelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        initPaint(mPaint, Color.WHITE, true, Paint.Style.STROKE);
        mPaint.setStrokeWidth(15);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        mCirclePath1 = new Path();
        mCirclePath2 = new Path();

        RectF rectF = new RectF(-100, -100, 100, 100);
        mCirclePath1.addArc(rectF, -90, 359.99f);
        mCirclePath2.addArc(rectF, 90, 359.99f);

        mPathMeasure1 = new PathMeasure();
        mPathMeasure2 = new PathMeasure();
        mPathMeasure1.setPath(mCirclePath1, false);
        mPathMeasure2.setPath(mCirclePath2, false);

        mPathMeasure = new PathMeasure();

        mPathLength = mPathMeasure1.getLength();

        valueAnimator = ValueAnimator.ofFloat(0, 1f).setDuration(2000);
        valueAnimator.setRepeatCount(-1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                alpha = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.translate(mWidth / 2, mHeight / 2);
        Path path = new Path();
        mPathMeasure1.getSegment(mPathLength * alpha * alpha, mPathLength * alpha * alpha + 100 * (-4 * alpha * alpha + 4 * alpha), path, true);
        canvas.drawPath(path,mPaint);



        Path path1 = new Path();
        mPathMeasure2.getSegment(mPathLength * alpha * alpha, mPathLength * alpha * alpha + 100 * (-4 * alpha * alpha + 4 * alpha), path1, true);
        canvas.drawPath(path1,mPaint);
    }


    private void initPaint(Paint paint, int red, boolean isAntiAlias, Paint.Style style) {
        paint.setColor(red);
        paint.setAntiAlias(isAntiAlias);
        paint.setStyle(style);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    public void starV() {
        valueAnimator.start();
    }
}
