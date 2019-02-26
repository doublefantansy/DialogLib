package hzkj.cc.loadingdialog;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CcDialog extends View {
    public static final int PROGRESS_DIALOG = 0;
    public static final int SUCCESS_DIALOG = 1;
    public static final int FAIL_DIALOG = 2;
    private int type;
    Dialog dialog;
    private HookView hookView;
    private TextView msg;
    private Button button;
    private CancelListener cancelListener;
    private ErrorView errorView;

    public CcDialog(Context context, int type) {
        super(context);
        this.type = type;
        init();
    }

    private void init() {
       dialog=new Dialog(getContext());
        switch (type) {
            case PROGRESS_DIALOG: {
                View view = LayoutInflater.from(getContext())
                        .inflate(R.layout.loading, null, false);
                dialog.setContentView(view);
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
                dialog.setContentView(view);
                hookView = view.findViewById(R.id.hookView);
                hookView.startCircle();
                msg = view.findViewById(R.id.text);
                button = view.findViewById(R.id.cancel);
                break;
            }
            case FAIL_DIALOG: {
                View view = LayoutInflater.from(getContext())
                        .inflate(R.layout.fail, null, false);
                dialog.setContentView(view);
                errorView = view.findViewById(R.id.errorView);
                errorView.startCircle();
                msg = view.findViewById(R.id.text);
                button = view.findViewById(R.id.cancel);
                break;
            }
        }
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow()
                .setBackgroundDrawableResource(android.R.color.transparent);
    }

    public void showDialog() {
        dialog.show();
    }

    public void dismissDialog() {
        dialog.dismiss();
    }

    public CcDialog setCancelListener(final CancelListener cancelListener) {
        this.cancelListener = cancelListener;
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    if (CcDialog.this.cancelListener != null) {
                        CcDialog.this.cancelListener.onClick(CcDialog.this);
                    }
                }
            });
        }
        return this;
    }

    public CcDialog setMessage(String message) {
        msg.setText(message);
        return this;
    }
}
