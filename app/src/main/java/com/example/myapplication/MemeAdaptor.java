package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MemeAdaptor extends RecyclerView.Adapter<MemeAdaptor.MemeHolder> {

    Context context;
    MemeHolder memeHolder;

    ArrayList<Meme> memes=new ArrayList<>();

    public MemeAdaptor(Context context, ArrayList<Meme> memes) {
        this.context = context;
        this.memes = memes;
    }

    @NonNull
    @Override
    public MemeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.meme,parent,false);

        MemeHolder memeHolder = new MemeHolder(view);

        return memeHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MemeHolder holder, int position) {

        holder.txtTitle.setText(memes.get(position).title);
        Glide
                .with(context)
                .load(memes.get(position).url)
                .centerCrop()
                .into(holder.imgMeme);

    }

    @Override
    public int getItemCount() {
        return memes.size();
    }


    class MemeHolder extends  RecyclerView.ViewHolder{
        TextView txtTitle;
        ImageView imgMeme;

        public MemeHolder(@NonNull View itemView)  {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.textView);
            imgMeme = itemView.findViewById(R.id.memeimg);

        }
    }
}
