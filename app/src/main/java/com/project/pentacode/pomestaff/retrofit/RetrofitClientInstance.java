package com.project.pentacode.pomestaff.retrofit;

import com.project.pentacode.pomestaff.model.Example;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public class RetrofitClientInstance {
    private static Retrofit retrofit;
    private static final String BASE_URL = "http://192.168.1.11:8000/";

    public static Retrofit getInstance() {

        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request newRequest = chain.request().newBuilder()
                            .addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjBjMDY1YTdjNzU3MzU2ZjhkZmYxNTBmN2UwZmEyNjYyY2IzZjIzZWI2ZjA0NWJkNWMwZWRlMjFkYjQ5YWQ5ZWEwMGMwZWVjOWFmZWUxZWEzIn0.eyJhdWQiOiIxIiwianRpIjoiMGMwNjVhN2M3NTczNTZmOGRmZjE1MGY3ZTBmYTI2NjJjYjNmMjNlYjZmMDQ1YmQ1YzBlZGUyMWRiNDlhZDllYTAwYzBlZWM5YWZlZTFlYTMiLCJpYXQiOjE1NDE1NjIzNDksIm5iZiI6MTU0MTU2MjM0OSwiZXhwIjoxNTczMDk4MzQ4LCJzdWIiOiIxMDExNDMyMCIsInNjb3BlcyI6W119.PquRGSuGMLTdcFLhlGxFsiR37yA0Ly9hGt9lz-8mxLw6l2MMJLC6BCI-p62Sj0ec-blYinLDxYEwyfr8mqiPUGxAEbsThgIte0yFpEjAIDPDsYLTLpe2WZgJ3WM5mx6L9Itw5hMaI3iFQkNzRhd3n4eE1_KEWsq-NUIHK18kjC_26t8K7HhnUP30M36A8tvEizr119YXAfn4pFZjBH8g9N86GZ0C1fkysEdQ6wnBTZwatxGJs66XrlCPCB0RYerpfTjLmSosIGbyTUNzY_VvHQizG3E6MUupojgGFGl3bP35Qt-MKB1q6z2m83LCz7B1xZBhrIkTrj6vemQhTFCCJTdEO6xqEDoBXbpnRK__FduGgVvoOtmy-35NEfCQLa831RL7wceGjO2Q2RZDxyNJ4f8xyWe4CpYxJwCElbKjEYxWjIAHgZlB0BbsSzpjVodTwmzAhhm3aNq2z9swGrWda2z6Kzk0xtvX0LL-F7BHEoiTvbZYOXUIVWKtEH33jCuGkrNng1d7HfZzdFB6JLA5867fu6CYlDGH0WlzFjejMVfaPAQvYDHLHucz5at1V0Jg0oiABhDNI_h4o2Oa666cqCxTcJVS5J4g0dpXDDDK0OpaM1KW6l9BLkzrNJJyoRQC-lEOOWcTt-sI1ESAivIZG1D9JjIEXlZI-P2mdBiDHOw")
                            .addHeader("Accept", "application/json")
                            .build();
                    return chain.proceed(newRequest);
                }
            }).build();

            retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}
