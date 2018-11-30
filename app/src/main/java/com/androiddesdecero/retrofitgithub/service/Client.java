package com.androiddesdecero.retrofitgithub.service;

import com.androiddesdecero.retrofitgithub.model.GitHubRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by albertopalomarrobledo on 30/11/18.
 */

public interface Client {

    @GET("/users/{user}/repos")
    Call<List<GitHubRepo>> getReposForUser(@Path("user") String user);
}
