package hzkj.cc.dialoglib;

import com.xuexiang.xui.XUI;

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        XUI.init(this);
        XUI.debug(true);
    }
}
