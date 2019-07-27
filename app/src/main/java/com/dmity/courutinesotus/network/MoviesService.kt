package com.dmity.courutinesotus.network

import com.dmity.courutinesotus.models.FilmsResponse
import com.dmity.courutinesotus.models.MovieDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {

    @GET("discover/movie?sort_by=popularity.desc")
    suspend fun getMovies(@Query("api_key") key: String): Response<FilmsResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") id: Int,
        @Query("api_key") key: String
    ): Response<MovieDetailResponse>

    companion object {
        const val API_KEY = "aabd39219c1a20aebc0da7d4b1a8c53e"
    }
}