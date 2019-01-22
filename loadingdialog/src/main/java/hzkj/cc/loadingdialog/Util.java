package hzkj.cc.loadingdialog;

import android.content.Context;

public class Util {
    public static int dipTopx(Context context, float dpValue) {
        final float scale = context.getResources()
                .getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
