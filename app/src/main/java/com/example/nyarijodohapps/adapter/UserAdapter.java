package com.example.nyarijodohapps.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nyarijodohapps.R;
import com.example.nyarijodohapps.entity.User;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.DataCalonViewHolder> {
    private ArrayList<User> users;
    private Context context;

    public UserAdapter(ArrayList<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public DataCalonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_data_calon,parent,false);
        return new DataCalonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataCalonViewHolder holder, int position) {
        Glide.with(context).load("http://55df6112262d.ngrok.io/image/"+users.get(position).getFoto()).into(holder.imageView);
        holder.tvNama.setText(users.get(position).getNama());
        holder.tvUmur.setText(users.get(position).getUmur());
        holder.tvUsername.setText(users.get(position).getUsername());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class DataCalonViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView tvNama, tvUmur, tvUsername;
        public DataCalonViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView5);
            tvNama = itemView.findViewById(R.id.tv_nama2);
            tvUmur = itemView.findViewById(R.id.tv_umur2);
            tvUsername = itemView.findViewById(R.id.tv_username2);
        }
    }
}
