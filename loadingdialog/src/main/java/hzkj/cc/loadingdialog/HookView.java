package hzkj.cc.loadingdialog;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class HookView extends View {
    Paint paint = new Paint();
    Path path = new Path();
    private ValueAnimator circleAnimator;
    ValueAnimator hookAnimator;

    public HookView(Context context) {
        super(context);
        init();
    }

    public HookView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);
        paint.setColor(Color.GREEN);
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
        circleAnimator.start();
    }

    private void startHook() {
        path.moveTo((float) (getWidth() / 3.5), getHeight() / 2);
        hookAnimator = ValueAnimator.ofFloat(0, 8);
        hookAnimator.setInterpolator(new LinearInterpolator());
        hookAnimator.setDuration(500);
        hookAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float progress = (float) animation.getAnimatedValue();
                if (progress < 5) {
                    path.rLineTo(progress, progress);
                } else {
                    path.rLineTo(progress, -progress);
                }
                postInvalidate();
            }
        });
        hookAnimator.start();
    }
}
