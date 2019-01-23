package hzkj.cc.loadingdialog;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * @author cc
 */
public class MyDialog extends View implements MyDialogInterface {
    public static final int PROGRESS_DIALOG = 0;
    public static final int SUCCESS_DIALOG = 1;
    PopupWindow popupWindow;
    private TextView msg;
    HookView hookView;
    private MyDialog dialog;
    private int type;
    private Context context;
    private WindowManager.LayoutParams ll;
    CancelListener cancelListener;

    public MyDialog(@NonNull Context context, int type) {
        super(context);
        this.type = type;
        this.context = context;
        dialog = this;
        init();
    }

    private void init() {
        popupWindow = new PopupWindow();
        ll = ((Activity) context).getWindow()
                .getAttributes();
        switch (type) {
            case PROGRESS_DIALOG: {
                View view = LayoutInflater.from(getContext())
                        .inflate(R.layout.loading, null, false);
                popupWindow.setContentView(view);
                popupWindow.setWidth(Util.dipTopx(context, 250));
                popupWindow.setHeight(Util.dipTopx(context, 180));
                popupWindow.setOutsideTouchable(false);
                popupWindow.setFocusable(true);
                ImageView image = view.findViewById(R.id.image);
                msg = view.findViewById(R.id.tv_text);
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(image, "rotation", 0, 360);
                objectAnimator.setDuration(3000);
                LinearInterpolator linearInterpolator = new LinearInterpolator();
                objectAnimator.setInterpolator(linearInterpolator);
                objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
                objectAnimator.start();
                break;
            }
            case SUCCESS_DIALOG: {
                View view = LayoutInflater.from(getContext())
                        .inflate(R.layout.success, null, false);
                popupWindow.setContentView(view);
                popupWindow.setWidth(Util.dipTopx(context, 250));
                popupWindow.setHeight(Util.dipTopx(context, 180));
                popupWindow.setOutsideTouchable(false);
                popupWindow.setFocusable(true);
                hookView = view.findViewById(R.id.hookView);
                hookView.startCircle();
                msg = view.findViewById(R.id.text);
                Button button = view.findViewById(R.id.cancel);
                button.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cancelListener.onClick(dialog);
                    }
                });
            }
            default: {
                break;
            }
        }
    }

    @Override
    public MyDialog setMessage(String message) {
        msg.setText(message);
        return dialog;
    }

    @Override
    public void dismiss() {
        popupWindow.dismiss();
        setDialogAlpha(1f);
    }

    @Override
    public void showInCenter() {
        popupWindow.showAtLocation(((Activity) context).getWindow()
                .getDecorView(), Gravity.CENTER, 0, 0);
        setDialogAlpha(0.5f);
    }

    @Override
    public void setDialogAlpha(float alpha) {
        ll.alpha = alpha;
        ((Activity) context).getWindow()
                .setAttributes(ll);
    }

    @Override
    public MyDialog setHookColor(int color) {
        hookView.setColor(color);
        return dialog;
    }

    @Override
    public MyDialog setCancelListener(CancelListener cancelListener) {
        this.cancelListener = cancelListener;
        return dialog;
    }
}
