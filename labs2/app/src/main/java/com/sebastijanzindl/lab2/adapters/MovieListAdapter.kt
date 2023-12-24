package com.sebastijanzindl.lab2.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.sebastijanzindl.lab2.R

import androidx.recyclerview.widget.RecyclerView
import com.sebastijanzindl.lab2.models.Movie

class MovieListAdapter(private val data: MutableList<Movie>): RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val movieId: TextView;
        val movieTitle: TextView;
        val movieDirector: TextView;
        val layout: LinearLayout;
        init {
            movieId = view.findViewById(R.id.movie_details_id);
            movieTitle = view.findViewById(R.id.movie_details_title);
            movieDirector = view.findViewById(R.id.movie_details_director);
            layout = view.findViewById(R.id.linear_layout);
        }
    }

    private var onClickListener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false);
        return ViewHolder(view);
    }
    override fun getItemCount(): Int {
        return data.count();
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.movieId.text =  data[position].id.toString();
        holder.movieTitle.text = data[position].title.toString();
        holder.movieDirector.text = data[position].director.toString();
        val item = data[position];
        holder.itemView.setOnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onClick(position, item)
            }
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    fun updateMovies(movies: List<Movie>) {
        this.data.clear()
        this.data.addAll(movies)
        notifyDataSetChanged()
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onClick(position: Int, model: Movie)
    }
}