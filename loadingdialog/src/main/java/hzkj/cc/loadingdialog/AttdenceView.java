package hzkj.cc.loadingdialog;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class AttdenceView extends View {
    private int inCircleColor;
    private int outCircleColor;
    Paint bigCirclePaint = new Paint();
    Paint paint = new Paint();
    Paint textPaint = new Paint();
    private float x;
    private float y;
    AttdenceViewListenner listenner;
    String text;
    int textSize = 14;

    public void setText(String text) {
        this.text = text;
    }

    public void setListenner(AttdenceViewListenner listenner) {
        this.listenner = listenner;
    }

    public AttdenceView(Context context) {
        super(context);
    }

    private void init() {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(15);
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(R.color.colorPrimary));
    }

    public AttdenceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.cc);
        inCircleColor = array.getInt(R.styleable.cc_inCircleColor, getResources().getColor(R.color.hzBlue));
        outCircleColor = array.getInt(R.styleable.cc_outCircleColor, getResources().getColor(R.color.hzBlue1));
        array.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = measureWidth(widthMeasureSpec);
        int height = measureWidth(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    private int measureWidth(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.AT_MOST) {
            specSize = Util.dipTopx(getContext(), 80);
        }
        return specSize;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = event.getX();
        y = event.getY();
        where();
        return super.onTouchEvent(event);
    }

    private void where() {
        if (Math.pow(Math.abs(getWidth() / 2 - x), 2) + Math.pow(Math.abs(getHeight() / 2 - y), 2) < Math.pow(Util.dipTopx(getContext(), 30), 2)) {
            if (listenner != null) {
                listenner.click();
            }
        }
    }

    public void setTextSize(int sp) {
        textSize = sp;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        bigCirclePaint.setColor(outCircleColor);
        bigCirclePaint.setStyle(Paint.Style.FILL);
        paint.setColor(inCircleColor);
        paint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.WHITE);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(Util.sp2px(getContext(), textSize));
        Rect rect = new Rect();
        textPaint.getTextBounds(text, 0, text.length(), rect);
        float width = rect.width();
        float height = rect.height();
        Path path = new Path();
        path.addCircle(getWidth() / 2, getHeight() / 2, getWidth()/2-Util.dipTopx(getContext(), 6), Path.Direction.CW);
        Path path1 = new Path();
        path1.addCircle(getWidth() / 2, getHeight() / 2, getWidth()/2, Path.Direction.CW);
        canvas.drawPath(path1, bigCirclePaint);
        canvas.drawPath(path, paint);
        canvas.drawText(text, getWidth() / 2 - width / 2, getHeight() / 2 + height / 2, textPaint);
    }
}
