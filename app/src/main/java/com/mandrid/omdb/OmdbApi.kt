package com.mandrid.omdb

import com.mandrid.omdb.MovieDetail
import com.mandrid.omdb.SearchMovie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface OmdbApi {
    @GET("/")
     fun searchMovies(
        @Query("s") query: String,
        @Query("apiKey") apiKey: String
    ): Call<SearchMovie>


    @GET("/")
     fun getMovieDetails(
        @Query("i") imdbId: String,
        @Query("apiKey") apiKey: String
    ): Call<MovieDetail>

}