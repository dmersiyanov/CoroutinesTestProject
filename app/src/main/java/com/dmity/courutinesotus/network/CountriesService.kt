package com.dmity.courutinesotus.network

import com.dmity.courutinesotus.models.FilmsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CountriesService {

    @GET("discover/movie?sort_by=popularity.desc")
    fun getCountries(@Query("api_key") key: String): Call<FilmsResponse>

    companion object {
        const val API_KEY = "aabd39219c1a20aebc0da7d4b1a8c53e"
    }

}