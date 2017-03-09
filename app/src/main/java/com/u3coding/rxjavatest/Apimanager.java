package com.u3coding.rxjavatest;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${U3} on 2017/3/9.
 */

public class Apimanager {
    private static final long DEFAULT_TIMEOUT = 5;
    private static final String BASE_URL = "https://api.douban.com/v2/movie/";
    private final Retrofit retrofit;
    private final MovieService movieService;

    private Apimanager() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        movieService = retrofit.create(MovieService.class);
    }
    private static class SingletonHolder{
        private static final Apimanager INSTANCE  = new Apimanager();
    }
    public static Apimanager getInstance(){
        return SingletonHolder.INSTANCE;
    }
    public void getTopMovie(Subscriber<MovieEntity> subscriber,int start,int count){
        movieService.getTopMoive(start,count)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
