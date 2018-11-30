package com.androiddesdecero.retrofitgithub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.androiddesdecero.retrofitgithub.model.GitHubRepo;
import com.androiddesdecero.retrofitgithub.service.Client;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Client client = retrofit.create(Client.class);

        Call<List<GitHubRepo>> call = client.getReposForUser("albertoandroid");

        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                for(int i=0; i<response.body().size(); i++){
                    GitHubRepo repo = response.body().get(i);
                    Log.d("TAG1", "Respositorio: " + i + " Nombre: " + repo.getName() +
                    " Avatar: " + repo.getOwner().getAvatarUrl() + " login: " + repo.getOwner().getLogin());
                }
            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                Log.d("TAG1", "Error: " + t.getMessage().toString());
            }
        });
    }
}
