package hzkj.cc.dialoglib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import hzkj.cc.loadingdialog.CancelListener;
import hzkj.cc.loadingdialog.CcDialog;

/**
 * @author cc
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.ss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "in", Toast.LENGTH_SHORT)
                        .show();
            }
        });
        findViewById(R.id.s).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CcDialog(MainActivity.this, CcDialog.PROGRESS_DIALOG).setCancelListener(new CancelListener() {
                    @Override
                    public void onClick(CcDialog dialog) {
                    }
                })
                        .setMessage("登录失败")
                        .showDialog();
            }
        });
    }
}
