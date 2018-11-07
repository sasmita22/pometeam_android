package com.project.pentacode.pomestaff.retrofit;

import com.project.pentacode.pomestaff.model.Example;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ServiceInterface {
    @GET("/api/project/2")
    Call<Example> getExample();

}
