package com.fsol.movieexample.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.fsol.movieexample.model.MovieItem
import com.fsol.movieexample.model.MoviesPagingDataSource
import com.fsol.movieexample.model.Utils.MOVIES_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieListRepository @Inject
constructor(
    private val moviesPagingDataSource: MoviesPagingDataSource
) {

    fun getMovies(): Flow<PagingData<MovieItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = MOVIES_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                moviesPagingDataSource
            }
        )
            .flow

    }

}