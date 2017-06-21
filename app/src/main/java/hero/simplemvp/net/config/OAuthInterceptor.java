package hero.simplemvp.net.config;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.orhanobut.logger.Logger;

import java.io.IOException;

import hero.simplemvp.BaseApplication;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 主要功能:拦截器
 * Created by wz on 2017/6/21
 * 修订历史:
 */
public class OAuthInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        if(isOnline()){
            Request originalRequest = chain.request();

            //添加新的参数
            HttpUrl.Builder requestBuilder = originalRequest.url().newBuilder()
                    .scheme(originalRequest.url().scheme())
                    .host(originalRequest.url().host());
            //插入键值对参数到 url query 中
//                .addQueryParameter("key","value");
            String url = requestBuilder.build().toString();
            //添加appid和sign
            if (!url.contains("&utype=")) {
                String time = String.valueOf(System.currentTimeMillis() / 1000 / 60/60);
                String sign = "";
                String encryptMD5ToString = "";
                requestBuilder.addQueryParameter("utype","0");
                requestBuilder.addQueryParameter("sign", encryptMD5ToString);
                requestBuilder.addQueryParameter("appid","0");
            }
            //新的请求
            Request newRequest = originalRequest.newBuilder()
                    .method(originalRequest.method(), originalRequest.body())
                    .url(requestBuilder.build())
                    .build();

            Logger.e( requestBuilder.build().toString());

            Response response = chain.proceed(newRequest);

            String cacheControl = response.header("Cache-Control");

            if (cacheControl == null || cacheControl.contains("no-store") || cacheControl.contains("no-cache") ||
                    cacheControl.contains("must-revalidate") || cacheControl.contains("max-age=0")) {
                return response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + 10)
                        .build();
            } else {
                return response;
            }
        } else {
            Logger.e("没有网络");
            Request request = chain.request();

            if (!isOnline()) {
                int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                request = request.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }

            return chain.proceed(request);
        }
    }
    public static boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) BaseApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
