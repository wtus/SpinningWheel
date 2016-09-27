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
        initPaint(mPaint, Color.RED, true, Paint.Style.STROKE);
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
        mPathMeasure1.getSegment(mPathLength * alpha * alpha, mPathLength * alpha * alpha + 200 * (-4 * alpha * alpha + 4 * alpha), path, true);
        mPathMeasure.setPath(path, false);
        int num = (int) (mPathMeasure.getLength() / 15);
        float[] pos = new float[2];
        switch (num) {
            default:
            case 6:
                mPathMeasure.getPosTan(num * 6, pos, null);
                mPaint.setColor(Color.argb(0, 255, 54, 43));
                canvas.drawPoint(pos[0], pos[1], mPaint);
            case 5:
                mPathMeasure.getPosTan(num * 5, pos, null);
                mPaint.setColor(Color.argb(255 - 28 * 1, 255, 54, 43));
                canvas.drawPoint(pos[0], pos[1], mPaint);
            case 4:
                mPathMeasure.getPosTan(num * 4, pos, null);
                mPaint.setColor(Color.argb(255 - 28 * 2, 255, 54, 43));
                canvas.drawPoint(pos[0], pos[1], mPaint);
            case 3:
                mPathMeasure.getPosTan(num * 3, pos, null);
                mPaint.setColor(Color.argb(255 - 28 * 3, 255, 54, 43));
                canvas.drawPoint(pos[0], pos[1], mPaint);
            case 2:
                mPathMeasure.getPosTan(num * 2, pos, null);
                mPaint.setColor(Color.argb(255 - 28 * 4, 255, 54, 43));
                canvas.drawPoint(pos[0], pos[1], mPaint);
            case 1:
                mPathMeasure.getPosTan(num, pos, null);
                mPaint.setColor(Color.argb(255 - 28 * 5, 255, 54, 43));
                canvas.drawPoint(pos[0], pos[1], mPaint);
            case 0:
                mPathMeasure.getPosTan(0, pos, null);
                mPaint.setColor(Color.argb(255 - 6 * 28, 255, 54, 43));
                canvas.drawPoint(pos[0], pos[1], mPaint);
                break;
        }
/*        for (int i = 0; i < num - 1; i++) {
            mPathMeasure.getPosTan(num * i, pos, null);
            mPaint.setColor(0xffffffff - i * 200);
            canvas.drawPoint(pos[0], pos[1], mPaint);
        }*/


        //canvas.drawPath(path, mPaint);


        Path path1 = new Path();
        mPathMeasure2.getSegment(mPathLength * alpha * alpha, mPathLength * alpha * alpha + 200 * (-4 * alpha * alpha + 4 * alpha), path1, true);
        mPathMeasure.setPath(path1, false);
        int num1 = (int) (mPathMeasure.getLength() / 15);
        float[] pos1 = new float[2];
        switch (num1) {
            default:
            case 6:
                mPathMeasure.getPosTan(num * 6, pos1, null);
                mPaint.setColor(Color.argb(0, 255, 54, 43));
                canvas.drawPoint(pos1[0], pos1[1], mPaint);
            case 5:
                mPathMeasure.getPosTan(num * 5, pos1, null);
                mPaint.setColor(Color.argb(255 - 28 * 1, 255, 54, 43));
                canvas.drawPoint(pos1[0], pos1[1], mPaint);
            case 4:
                mPathMeasure.getPosTan(num * 4, pos1, null);
                mPaint.setColor(Color.argb(255 - 28 * 2, 255, 54, 43));
                canvas.drawPoint(pos1[0], pos1[1], mPaint);
            case 3:
                mPathMeasure.getPosTan(num * 3, pos1, null);
                mPaint.setColor(Color.argb(255 - 28 * 3, 255, 54, 43));
                canvas.drawPoint(pos1[0], pos1[1], mPaint);
            case 2:
                mPathMeasure.getPosTan(num * 2, pos1, null);
                mPaint.setColor(Color.argb(255 - 28 * 4, 255, 54, 43));
                canvas.drawPoint(pos1[0], pos1[1], mPaint);
            case 1:
                mPathMeasure.getPosTan(num, pos1, null);
                mPaint.setColor(Color.argb(255 - 28 * 5, 255, 54, 43));
                canvas.drawPoint(pos1[0], pos1[1], mPaint);
            case 0:
                mPathMeasure.getPosTan(0, pos1, null);
                mPaint.setColor(Color.argb(255 - 6 * 28, 255, 54, 43));
                canvas.drawPoint(pos1[0], pos1[1], mPaint);
                break;
        }
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
