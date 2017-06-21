package hero.simplemvp;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

/**
 * 主要功能:
 * Created by wz on 2017/6/21
 * 修订历史:
 */
public class BaseApplication extends Application {

    public static String cacheDir="";
    private static Context applicationContext;
    @Override
    public void onCreate() {
        super.onCreate();
        initApplicationData();
    }
    private void initApplicationData(){
        applicationContext=getApplicationContext();
        if (applicationContext.getExternalCacheDir() != null && android.os.Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cacheDir = applicationContext.getExternalCacheDir().toString();
        } else {
            cacheDir = applicationContext.getCacheDir().toString();
        }
    }

    public static Context getContext() {
        return applicationContext;
    }
}
