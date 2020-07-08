package com.example.musicplayer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.ViewHolder> {
    Context context;
    List<Songs> songsList;

    public SongsAdapter(Context context, List<Songs> songsList) {
        this.context = context;
        this.songsList = songsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View heroView = inflater.inflate(R.layout.item_song, parent, false);
        ViewHolder viewHolder = new ViewHolder(heroView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Songs song = songsList.get(position);
        Picasso.with(context).load(songsList.get(position).linkAnh).into(holder.imgSongs);
        holder.tvName.setText(song.getName());
        holder.tvSinger.setText(song.getSinger());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context=v.getContext();
                Intent intent=new Intent(context, PlayingSongActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("name",song.name);
                bundle.putString("linkAnh",song.linkAnh);
                bundle.putString("linkMp3",song.linkMp3);
                bundle.putString("singer",song.singer);
                bundle.putDouble("duration", song.dur);
                intent.putExtra("bdPlayingSong",bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return songsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgSongs;
        TextView tvName, tvSinger;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgSongs = itemView.findViewById(R.id.imgSongs);
            tvName = itemView.findViewById(R.id.tvName);
            tvSinger = itemView.findViewById(R.id.tvSinger);
        }
    }
}
