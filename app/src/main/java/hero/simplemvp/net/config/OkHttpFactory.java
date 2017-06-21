package hero.simplemvp.net.config;

import java.io.File;
import java.util.concurrent.TimeUnit;

import hero.simplemvp.BaseApplication;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * 主要功能:配置OkHttp
 * Created by wz on 2017/6/21
 * 修订历史:
 */
public enum  OkHttpFactory {
    INSTANCE;

    private final OkHttpClient okHttpClient;

    private static final int TIMEOUT_READ = 50;
    private static final int TIMEOUT_WRITE = 50;
    private static final int TIMEOUT_CONNECTION = 50;

    OkHttpFactory() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        //缓存
        File cacheFile = new File(BaseApplication.cacheDir, "/NetCache");
        Cache cache = new Cache(cacheFile, 50 * 1024 * 1024);

        OAuthInterceptor authInterceptor = new OAuthInterceptor();
//        https证书不为空的情况下设置证书
        if (HttpsFactory.getSSLSocketFactory() != null) {
            builder.sslSocketFactory(HttpsFactory.getSSLSocketFactory());
            builder.hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        }
        builder.cache(cache).addNetworkInterceptor(authInterceptor);

        builder.retryOnConnectionFailure(true)//失败重连
                //time out
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_WRITE, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS);

        okHttpClient = builder.build();
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }
}
