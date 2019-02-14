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
    private ValueAnimator leftUpforkAnimator;
    private ValueAnimator rightUpforkAnimator;
    private ValueAnimator leftDownForkAnimator;
    private ValueAnimator rightDownForkAnimator;
    private Point currentPoint;

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
        Point startPoint = new Point(getWidth() / 2, getHeight() / 2);
        Point leftUpEndPoint = new Point(getWidth() * 3 / 10, getHeight() * 3 / 10);
        Point rightUpEndPoint = new Point(getWidth() * 7 / 10, getHeight() * 3 / 10);
        Point leftDownEndPoint = new Point(getWidth() * 3 / 10, getHeight() * 7 / 10);
        Point rightDownEndPoint = new Point(getWidth() * 7 / 10, getHeight() * 7 / 10);
        leftUpforkAnimator = ValueAnimator.ofObject(new PointEvaluator(), startPoint, leftUpEndPoint);
        leftUpforkAnimator.setInterpolator(new LinearInterpolator());
        leftUpforkAnimator.setDuration(500);
        leftUpforkAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (Point) (animation.getAnimatedValue());
                leftUpPath.lineTo(currentPoint.x, currentPoint.y);
                postInvalidate();
            }
        });
        leftUpforkAnimator.start();
        rightUpforkAnimator = ValueAnimator.ofObject(new PointEvaluator(), startPoint, rightUpEndPoint);
        rightUpforkAnimator.setInterpolator(new LinearInterpolator());
        rightUpforkAnimator.setDuration(500);
        rightUpforkAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (Point) (animation.getAnimatedValue());
                rightUpPath.lineTo(currentPoint.x, currentPoint.y);
                postInvalidate();
            }
        });
        rightUpforkAnimator.start();
        leftDownForkAnimator = ValueAnimator.ofObject(new PointEvaluator(), startPoint, leftDownEndPoint);
        leftDownForkAnimator.setInterpolator(new LinearInterpolator());
        leftDownForkAnimator.setDuration(500);
        leftDownForkAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (Point) (animation.getAnimatedValue());
                leftDownPath.lineTo(currentPoint.x, currentPoint.y);
                postInvalidate();
            }
        });
        leftDownForkAnimator.start();
        rightDownForkAnimator = ValueAnimator.ofObject(new PointEvaluator(), startPoint, rightDownEndPoint);
        rightDownForkAnimator.setInterpolator(new LinearInterpolator());
        rightDownForkAnimator.setDuration(500);
        rightDownForkAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (Point) (animation.getAnimatedValue());
                rightDownPath.lineTo(currentPoint.x, currentPoint.y);
                postInvalidate();
            }
        });
        rightDownForkAnimator.start();
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
