package com.appcrud.amobasoftwaredemo.Utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ApiUtils {

    private static Services single_instance = null;

    public static String baseurl = "https://fake-api-amoba.onrender.com/";//BuildConfig.baseurl;

    public static Services getInstance()
    {
        if (single_instance == null){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                    .connectTimeout(1, TimeUnit.HOURS)
                    .readTimeout(1, TimeUnit.HOURS)
                    .writeTimeout(1, TimeUnit.HOURS).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(baseurl)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();
            single_instance = retrofit.create(Services.class);
        }
        return single_instance;
    }

}
