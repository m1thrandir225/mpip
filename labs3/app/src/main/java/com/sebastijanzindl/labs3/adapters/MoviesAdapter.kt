package com.sebastijanzindl.labs3.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sebastijanzindl.labs3.R
import com.sebastijanzindl.labs3.domain.Models.Movie
import com.squareup.picasso.Picasso

class MoviesAdapter(
    private val movies: ArrayList<Movie> = ArrayList(),
    private val ClickListener: onClickListener
): RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    interface  onClickListener {
        fun onClickItem(id: String);
    }

    class ViewHolder(view: View) :  RecyclerView.ViewHolder(view) {
        private var imageView: ImageView = view.findViewById(R.id.movie_image);
        private var titleView: TextView = view.findViewById(R.id.movie_title);
        private var releaseYearView: TextView = view.findViewById(R.id.release_year);

        fun bind(
            movie: Movie,
            onClickListener: onClickListener
        ) {
            Picasso.get()
                .load(movie.poster)
                .into(imageView)


            titleView.text = movie.title
            releaseYearView.text = movie.year

            itemView.setOnClickListener {
                onClickListener.onClickItem(movie.imdbID)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_movie_layout, parent, false)

        return ViewHolder(view);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position], ClickListener)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateMovies(movies: List<Movie>?) {
        this.movies.clear();

        if (movies != null) {
            this.movies.addAll(movies)
        }

        notifyDataSetChanged();
    }
}