package com.rassa.rassauser.webApi;

import android.util.Log;

import com.rassa.rassauser.RasaUser;
import com.rassa.rassauser.utils.RUserApp;
import com.rassa.rassauser.utils.UserProfile;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class WebService {
    //public static final String BASE_URL = "http://market.bahartel.ir/api/";
    //public static final String BASE_URL = "http://151.80.66.156/HumanResources/api/";
    private static Retrofit retrofit;
    private static UserProfile userProfile;

    public static Retrofit getClient() {
        userProfile = new UserProfile(RUserApp.getContext());
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS).addInterceptor(loggingInterceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request.Builder ongoing = chain.request().newBuilder();
                        if (userProfile.getKeyJwt( "-1")!=null) {
                            Log.d("TAG" ,"Authorization: "+"Bearer "+ userProfile.getKeyJwt("-1"));
                            ongoing.addHeader("Authorization", "Bearer "+userProfile.getKeyJwt("-1"));
                            //ongoing.addHeader("DeviceId", Value.getDeviceId(App.getContext()));
                        }
                        return chain.proceed(ongoing.build());
                    }
                })
                .build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(RasaUser.baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }

    public Retrofit getRetrofitNoLog() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS).build();
                /*.addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request.Builder ongoing = chain.request().newBuilder();
                        if (UserInfo.getKeyJwt(context, "-1")!=null) {
                            Log.d("" ,"Authorization: "+"Bearer "+ UserInfo.getKeyJwt(context, "-1"));
                            ongoing.addHeader("Authorization", "Bearer "+UserInfo.getKeyJwt(context, "-1"));
                            //ongoing.addHeader("DeviceId", Value.getDeviceId(App.getContext()));
                        }
                        return chain.proceed(ongoing.build());
                    }
                })
                .build();*/

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RasaUser.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit;
    }

}