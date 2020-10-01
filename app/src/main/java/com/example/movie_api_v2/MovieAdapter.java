package com.example.movie_api_v2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.Holder> {

    List<Movie> movieList;
    Context context;
    PositionInterface listener;

    public void setListener(PositionInterface listener) {
        this.listener = listener;
    }

    public MovieAdapter(List<Movie> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.movie_card,parent,false);
        return new Holder(view,listener);
    }


    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.rating.setText(String.valueOf(movieList.get(position).getVote_average()));
        holder.progressBar.setProgress((int)(movieList.get(position).getVote_average() * 10), true);

        Picasso.get()
                .load(movieList.get(position).getPoster_path())
                .into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        ImageView poster;
        TextView rating;
        ProgressBar progressBar;

        public Holder(@NonNull View itemView, final PositionInterface listener) {
            super(itemView);

            poster = itemView.findViewById(R.id.poster);
            rating = itemView.findViewById(R.id.rating);
            progressBar = itemView.findViewById(R.id.progressView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    listener.onMovieClick(position);
                }
            });
        }
    }
    public interface PositionInterface {
        void onMovieClick(int position);
    }

}
