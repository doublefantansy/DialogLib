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

public class HookView extends View {
    private ValueAnimator circleAnimator;
    ValueAnimator hookAnimator;
    Paint paint;
    Path path;
    Point currentPoint;

    public HookView(Context context) {
        super(context);
    }

    public HookView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        path = new Path();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(15);
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(R.color.dialog_green));
        circleAnimator = ObjectAnimator.ofFloat(0, 360);
        circleAnimator.setInterpolator(new LinearInterpolator());
        circleAnimator.setDuration(1000);
        circleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                path.addArc(10, 10, getWidth() - 10, getHeight() - 10, 90, (Float) animation.getAnimatedValue());
                postInvalidate();
            }
        });
        circleAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                startHook();
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);
    }

    public void setColor(int color) {
        paint.setColor(color);
    }

    public void startCircle() {
        circleAnimator.start();
    }

    public void endCircle() {
        circleAnimator.end();
    }

    private void startHook() {
        path.moveTo(getWidth() / 4, getHeight() / 2);
        Point startPoint = new Point(getWidth() / 4, getHeight() / 2);
        Point middlePoint = new Point(getWidth() * 7 / 15, getHeight() * 11 / 16);
        Point endPoint = new Point(getWidth() * 3 / 4, getHeight() * 1 / 3);
        hookAnimator = ValueAnimator.ofObject(new PointEvaluator(), startPoint, middlePoint, endPoint);
        hookAnimator.setInterpolator(new LinearInterpolator());
        hookAnimator.setDuration(500);
        hookAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (Point) (animation.getAnimatedValue());
                path.lineTo(currentPoint.x, currentPoint.y);
                postInvalidate();
            }
        });
        hookAnimator.start();
    }
}
