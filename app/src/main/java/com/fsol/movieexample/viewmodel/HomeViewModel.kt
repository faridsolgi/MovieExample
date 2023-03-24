package com.fsol.movieexample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fsol.movieexample.model.MovieItem
import com.fsol.movieexample.repository.MovieListRepository
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class HomeViewModel @Inject constructor(
    val movieListRepository: MovieListRepository
) : ViewModel() {

    fun movieList(): Flow<PagingData<MovieItem>> = movieListRepository.getMovies()
            .cachedIn(viewModelScope)




}