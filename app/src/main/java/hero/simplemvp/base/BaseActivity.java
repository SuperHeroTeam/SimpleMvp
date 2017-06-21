package hero.simplemvp.base;

import android.app.Activity;
import android.os.Bundle;

import hero.simplemvp.net.SimpleApi;
import hero.simplemvp.net.config.ApiFactory;

/**
 * 主要功能:
 * Created by wz on 2017/6/21
 * 修订历史:
 */
public class BaseActivity extends Activity {
    public SimpleApi apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiService= ApiFactory.INSTANCE.getSimpleApi();
    }
}
