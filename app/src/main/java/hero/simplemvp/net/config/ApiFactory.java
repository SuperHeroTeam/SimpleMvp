package hero.simplemvp.net.config;

import hero.simplemvp.net.SimpleApi;

/**
 * 主要功能:创建可用的接口Api
 * Created by wz on 2017/6/21
 * 修订历史:
 */
public enum  ApiFactory {
    INSTANCE;
    private final SimpleApi simpleApi;

    ApiFactory() {
        simpleApi=RetrofitClient.INSTANCE.getRetrofit().create(SimpleApi.class);
    }

    public SimpleApi getSimpleApi() {
        return simpleApi;
    }
}
