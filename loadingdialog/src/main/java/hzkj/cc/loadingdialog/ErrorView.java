package hzkj.cc.loadingdialog;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class ErrorView extends View {
    private Paint paint;
    private Path leftUpPath;
    private Path rightUpPath;
    private Path leftDownPath;
    private Path rightDownPath;
    private ValueAnimator circleAnimator;
    private ValueAnimator forkAnimator;

    public ErrorView(Context context) {
        super(context);
        init();
    }

    public ErrorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        leftUpPath = new Path();
        rightUpPath = new Path();
        leftDownPath = new Path();
        rightDownPath = new Path();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(15);
        paint.setAntiAlias(true);
        paint.setColor(0xFFFF0000);
        circleAnimator = ObjectAnimator.ofFloat(0, 360);
        circleAnimator.setInterpolator(new LinearInterpolator());
        circleAnimator.setDuration(1000);
        circleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                leftUpPath.addArc(10, 10, getWidth() - 10, getHeight() - 10, 90, (Float) animation.getAnimatedValue());
                postInvalidate();
            }
        });
        circleAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                startErrorView();
            }
        });
    }

    private void startErrorView() {
        leftUpPath.moveTo((float) (getWidth() / 2), getHeight() / 2);
        rightUpPath.moveTo((float) (getWidth() / 2), getHeight() / 2);
        leftDownPath.moveTo((float) (getWidth() / 2), getHeight() / 2);
        rightDownPath.moveTo((float) (getWidth() / 2), getHeight() / 2);
        forkAnimator = ValueAnimator.ofFloat(0, getWidth() / 70);
        forkAnimator.setInterpolator(new LinearInterpolator());
        forkAnimator.setDuration(500);
        forkAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float progress = (float) animation.getAnimatedValue();
                leftUpPath.rLineTo(-progress, -progress);
                rightUpPath.rLineTo(progress, -progress);
                leftDownPath.rLineTo(-progress, progress);
                rightDownPath.rLineTo(progress, progress);
                postInvalidate();
            }
        });
        forkAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(leftUpPath, paint);
        canvas.drawPath(rightUpPath, paint);
        canvas.drawPath(leftDownPath, paint);
        canvas.drawPath(rightDownPath, paint);
    }

    public void startCircle() {
        circleAnimator.start();
    }
}
