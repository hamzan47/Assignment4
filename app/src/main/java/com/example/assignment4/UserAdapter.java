package com.example.assignment4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment4.models.UsersResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;

        public ViewHolder(View itemView) {

            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.text_view);
            imageView = (ImageView) itemView.findViewById(R.id.image_view);
        }
    }
    private Call<UsersResponse> myusers;
    Response<UsersResponse> myResponse;
    public UserAdapter(Call<UsersResponse> users, Response<UsersResponse> Response){

        myusers=users;
        myResponse=Response;
    }

    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_list,parent,false);
        return new ViewHolder(view);
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Set item views based on your views and data model
        holder.textView.setText(myResponse.body().getResults().get(0).getName().getFirst());
        ImageView img = holder.imageView;
        Picasso.get().load(myResponse.body().getResults().get(0).getPicture().getLarge()).into(img);
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return 50;
    }
}
