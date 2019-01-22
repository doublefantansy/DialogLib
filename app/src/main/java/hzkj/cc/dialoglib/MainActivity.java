package hzkj.cc.dialoglib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import hzkj.cc.loadingdialog.CancelListener;
import hzkj.cc.loadingdialog.MyDialog;

/**
 * @author cc
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView v = findViewById(R.id.s);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyDialog(MainActivity.this, MyDialog.SUCCESS_DIALOG).setMessage("登录成功")
                        .setHookColor(getResources().getColor(R.color.green))
                        .setCancelListener(new CancelListener() {
                            @Override
                            public void onClick(MyDialog myDialog) {
                                myDialog.dismiss();
                            }
                        })
                        .showInCenter();
            }
        });
    }
}
