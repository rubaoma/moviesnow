package com.rubdev.moviesnow.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.rubdev.moviesnow.R
import com.rubdev.moviesnow.databinding.ItemMovieBinding
import com.rubdev.moviesnow.model.Result

class MovieAdapter(
    private val movie: ArrayList<Result>
): RecyclerView.Adapter<MovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemMovieBinding:ItemMovieBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_movie,
            parent,
            false
        )
        return MovieViewHolder(itemMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.itemMovieBinding.result = movie[position]
    }

    override fun getItemCount(): Int = movie.size

    fun setUpData(movies: List<Result>){
        movie.clear()
        movie.addAll(movies)
        notifyDataSetChanged()
    }

}

class MovieViewHolder(
    val itemMovieBinding: ItemMovieBinding
):RecyclerView.ViewHolder(itemMovieBinding.root)