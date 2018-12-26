package com.project.pentacode.pomestaff.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    private static Retrofit retrofit;
    private static final String BASE_URL = "http://192.168.137.1:8000/";
    private static final String BASE_URL_API = BASE_URL+"api/";
    public static final String BASE_URL_IMAGE_PROFILE = BASE_URL+"images/profile/";
    public static final String BASE_URL_IMAGE_COMPANY = BASE_URL+"images/company/";

    public static Retrofit getInstance() {

        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request newRequest = chain.request().newBuilder()
                            //.addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjQ0M2EyM2VlZjlmZDA3MTQ5YTBjMmJiMTA4YTAyOTU5NDQ2NjBlNmExODBkNTJlMzZlNzViZmEwMWEyMDBhY2Q2YmQxZjc3MzFhNjMzNjFjIn0.eyJhdWQiOiIxIiwianRpIjoiNDQzYTIzZWVmOWZkMDcxNDlhMGMyYmIxMDhhMDI5NTk0NDY2MGU2YTE4MGQ1MmUzNmU3NWJmYTAxYTIwMGFjZDZiZDFmNzczMWE2MzM2MWMiLCJpYXQiOjE1NDQ3MDQyNDEsIm5iZiI6MTU0NDcwNDI0MSwiZXhwIjoxNTc2MjQwMjQxLCJzdWIiOiI3Iiwic2NvcGVzIjpbXX0.SBh_GABYui9800aAZsTKoCIZamLwTxFqTwvCJS4mlS4WLLg3KCJkN0XI8oklvcNr-tUttMCo4QTS7cmeTMK1q2UPDaY-oNsoZVhizHvA1ZcnOp3f9uQLLh81WTM7KRbTDQS9zHJhcWw3yrESMKJWECizVG2qm1X02eljfSbLlpQIKAt866T8uArrLB8HhKEKr8_MkfrxJd_7wZx-2QhOCz05-Cg4-UFNkjEJf5MLtmfnq7J2b7SVMqLtYH0Z1s-AKANZoDn7SiP9vDROvDHBWS3ObE6KrTTYjy7_lZjhSpdLGvZVolXf49v4rgMyVjMulVp2m7yELcQVxH1-kVMfzP5rz8uiz58ACsREEWi-2WnKnLfwy-zAGPhRbnEBo3ez_9JCIGCBD_RiKyMu3KuHdFnTaRQj9Da63yLtjLmHmZBKPFyHp-6rAXr7nJ8E9mL5scmTs5X6PYTYT2mGNoRyzY475QatKPU2CW-RuMc_-_9gTJS8ImhV5Pn0gQndzXQ5mRYe1cdvB1EYRy7u-DHxVv5jZJwxmxojmPy9PRJuUt2A6W0ULWGTvBPChpU-SgdXJGxKpnYnBPTUuTpMnDVd3Uq6DKg6gR51NxWh1HFETqXbOF3viSiK7xPwAaLY-ClAfFR7M8lsTSFcX2g1103z-TLgm_Ng2RhmWGjlXqzEBKk")
                            .addHeader("Accept", "application/json")
                            .build();
                    return chain.proceed(newRequest);
                }
            }).build();


            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(BASE_URL_API)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }


}
