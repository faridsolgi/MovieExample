package com.fsol.movieexample.view

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fsol.movieexample.R
import com.fsol.movieexample.databinding.ItemMoviesMainBinding
import com.fsol.movieexample.model.MovieItem
import com.squareup.picasso.Picasso
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class MovieListAdapter @Inject constructor(
    @ActivityContext val context: Context
) :
    PagingDataAdapter<MovieItem, MovieListAdapter.MovieListAdapterViewHolder>(MovieDiffCallBack()) {

    override fun onBindViewHolder(holder: MovieListAdapterViewHolder, position: Int) {
       holder.onBind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListAdapterViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding: ItemMoviesMainBinding = ItemMoviesMainBinding.inflate(inflater, parent, false)
        return MovieListAdapterViewHolder(binding)
    }

    class MovieDiffCallBack : DiffUtil.ItemCallback<MovieItem>() {
        override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
         return  oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
            return oldItem == newItem
        }

    }

    class MovieListAdapterViewHolder(val binding: ItemMoviesMainBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun  onBind (item: MovieItem?) {
                try {
                    binding.apply {
                        movieName.setText(item?.title.toString())
                        imdbRate.text = item?.imdbRating
                        genreName.text =item?.genres?.joinToString {  s ->
                            s.toString()
                        }

                    }

                    ContextCompat.getDrawable(binding.root.context,R.drawable.circle_loading_shape)
                        ?.let {
                            Picasso.get()
                                .load(item?.poster.toString())
                                .placeholder(it)
                                .into(binding.movieposter)
                        }

                }catch (e:Exception){
                    e.printStackTrace()
                }

            }

    }


}