package hzkj.cc.dialoglib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import hzkj.cc.loadingdialog.AttdenceView;
import hzkj.cc.loadingdialog.AttdenceViewListenner;

/**
 * @author cc
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AttdenceView view = findViewById(R.id.sss);
        view.setText("签到");
        view.setListenner(new AttdenceViewListenner() {
            @Override
            public void click() {
                Toast.makeText(MainActivity.this, "in", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }
}
