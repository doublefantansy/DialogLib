package hzkj.cc.loadingdialog;

import android.content.Context;

public class Util {
    public static int dipTopx(Context context, float dpValue) {
        final float scale = context.getResources()
                .getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources()
                .getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int px2dip(Context context, int px) {
        final float scale = context.getResources()
                .getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }
}
