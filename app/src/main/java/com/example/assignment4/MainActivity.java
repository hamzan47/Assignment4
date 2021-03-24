package com.example.assignment4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.assignment4.models.UsersResponse;
import com.example.assignment4.networking.ApiInterface;
import com.squareup.picasso.Picasso;

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

        RecyclerView user = findViewById(R.id.userList);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface service = retrofit.create(ApiInterface.class);

        Call<UsersResponse> call = service.getMultipleUser(50);

        call.enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {

                Log.v("", response.body().toString());
                user.setAdapter(new UserAdapter(call,response));
                user.setLayoutManager(new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,false));
            }
            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {

                Log.e("", t.getLocalizedMessage());
            }
        });
        user.setLayoutManager(new LinearLayoutManager(this));
    }
}