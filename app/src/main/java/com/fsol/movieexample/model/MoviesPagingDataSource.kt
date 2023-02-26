package com.fsol.movieexample.model

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fsol.movieexample.model.retrofit.MovieApi
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class MoviesPagingDataSource @Inject constructor(
    private val movieApi: MovieApi
) : PagingSource<Int, MovieItem>() {
    override fun getRefreshKey(state: PagingState<Int, MovieItem>): Int? {
   return state.anchorPosition?.let {
       state.closestPageToPosition(it)
           ?.prevKey?.plus(1)
           ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
   }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItem> {
        val pageNumber = params.key ?: 1
        return try {
            val response = movieApi.movieList(pageNumber).firstOrNull()
            var nextPageNumber: Int? = null
            if (pageNumber < response?.metadata?.pageCount!!) {
                nextPageNumber = pageNumber+1
            }
            LoadResult.Page(
                response.movies.orEmpty(),
                null,
                nextPageNumber
            )


        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }


}