package com.example.widetech.data.network;

import com.example.widetech.data.models.GetProductsResponse;
import com.example.widetech.data.models.LoginResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RetrofitService {
    @FormUrlEncoded
    @Headers("User-Agent: Chrome")
    @POST("rest/Login")
    Observable<LoginResponse> doLogin(@Field("ani_number") String email,
                                      @Field("product_id") String password
    );

    @FormUrlEncoded
    @Headers("User-Agent: Chrome")
    @POST("rest/GetProductsData")
    Observable<GetProductsResponse> getInfoProducts(@Field("response_type") String responseType);

    class Creator {
        public static RetrofitService newRetrofitServiceAdapter() {
            Gson gson = new GsonBuilder().setLenient().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
            Retrofit retrofit = new Retrofit.Builder().baseUrl("https://tracphapigeneric.com/v2/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(new OkHttpClient.Builder()
                            .addInterceptor(new HttpLoggingInterceptor()
                                    .setLevel(HttpLoggingInterceptor.Level.BODY))
                            .connectTimeout(30, TimeUnit.SECONDS)
                            .writeTimeout(30, TimeUnit.SECONDS)
                            .readTimeout(30, TimeUnit.SECONDS).build())
                    .build();

            return retrofit.create(RetrofitService.class);
        }
    }
}
