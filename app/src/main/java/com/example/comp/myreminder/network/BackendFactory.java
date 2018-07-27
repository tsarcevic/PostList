package com.example.comp.myreminder.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by COMP on 25.7.2018..
 */

public class BackendFactory {

    private static String BASE_URL = "https://jsonplaceholder.typicode.com/";

    private static Retrofit retrofit;

    private static ApiService apiService;

    private static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(new OkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }

        return retrofit;
    }

    public static ApiService getApiServiceInstance() {
        if (apiService == null) {
            apiService = getRetrofitInstance().create(ApiService.class);
        }

        return apiService;
    }
}
