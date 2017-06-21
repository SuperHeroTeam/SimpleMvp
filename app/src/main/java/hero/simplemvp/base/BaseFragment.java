package hero.simplemvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import hero.simplemvp.net.SimpleApi;
import hero.simplemvp.net.config.ApiFactory;

/**
 * 主要功能:
 * Created by wz on 2017/6/21
 * 修订历史:
 */
public class BaseFragment extends Fragment {

    public SimpleApi apiService;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiService= ApiFactory.INSTANCE.getSimpleApi();
    }
}
