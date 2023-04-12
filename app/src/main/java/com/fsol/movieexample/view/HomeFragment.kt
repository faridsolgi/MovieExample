package com.fsol.movieexample.view

import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.fsol.movieexample.databinding.FragmentHomeBinding
import com.fsol.movieexample.model.MovieItem
import com.fsol.movieexample.model.Utils.initRecycler
import com.fsol.movieexample.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    MovieListAdapter.SetOnItemClick {

    @Inject
    lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var movieAdapter: MovieListAdapter


    override fun fragmentBody() {
        //init recyclerview
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        movieAdapter.setOnItemClick = this
        binding.rvMovies.initRecycler(
            layoutManager,
            movieAdapter
        )
        //show movies
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.movieList().collectLatest {
                movieAdapter.submitData(it)
            }

        }

        movieAdapter.addLoadStateListener {
            if (it.refresh is LoadState.Loading ||
                it.append is LoadState.Loading
            ) {
                binding.progressLoadAllMovie.visibility = View.VISIBLE
            } else {
                binding.progressLoadAllMovie.visibility = View.GONE
                if (it.refresh is LoadState.Error ||
                    it.append is LoadState.Error ||
                    it.prepend is LoadState.Error
                ) {
                    movieAdapter.retry()
                }
            }
        }


    }

    override fun onMovieItemClickListener(item: MovieItem?) {
        println(item)
    }
}