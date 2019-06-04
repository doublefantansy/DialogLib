package hzkj.cc.dialoglib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xuexiang.xui.XUI;

import hzkj.cc.loadingdialog.CcDialog;

/**
 * @author cc
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        XUI.initTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.s).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              new CcDialog(MainActivity.this,CcDialog.SUCCESS_DIALOG).setMessage("ccc").showDialog();
            }
        });
    }
}
