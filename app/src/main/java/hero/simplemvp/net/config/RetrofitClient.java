package hero.simplemvp.net.config;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 主要功能:配置Retrofit
 * Created by wz on 2017/6/21
 * 修订历史:
 */
public enum  RetrofitClient {
    INSTANCE;
    private Retrofit retrofit;
    RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .client(OkHttpFactory.INSTANCE.getOkHttpClient())
                .baseUrl(ApiConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
    public Retrofit getRetrofit(){
        return  retrofit;
    }
}
