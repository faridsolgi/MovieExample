package com.fsol.movieexample.model.retrofit

import com.fsol.movieexample.model.MovieListResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("movies")
    fun movieList(@Query("page") page:Int) :Flow<MovieListResponse?>
}