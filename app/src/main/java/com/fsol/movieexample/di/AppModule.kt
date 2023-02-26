package com.fsol.movieexample.di

import com.fsol.movieexample.model.Utils.BASE_URL
import com.fsol.movieexample.model.Utils.CONNECTION_TIME
import com.fsol.movieexample.model.retrofit.MovieApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBaseUrl()  = BASE_URL

    @Provides
    @Singleton
    fun provideConnectionTime() = CONNECTION_TIME
    @Provides
    @Singleton
    fun provideGson() = GsonBuilder().setLenient().create()



    @Provides
    @Singleton
    fun provideInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }



    @Provides
    @Singleton
    fun provideOkHttpClient(time :Long,httpLoggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .writeTimeout(time ,TimeUnit.SECONDS)
            .readTimeout(time ,TimeUnit.SECONDS)
            .connectTimeout(time ,TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl :String,gson: Gson,client : OkHttpClient):Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit) : MovieApi =
        retrofit.create(MovieApi::class.java)
}