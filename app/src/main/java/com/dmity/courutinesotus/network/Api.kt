package com.dmity.courutinesotus.network

import com.dmity.courutinesotus.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Api {

    private var retrofit: Retrofit
    private var logging = HttpLoggingInterceptor()
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val REQUEST_TIMEOUT = 60L

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getHttpLogClient())
            .build()
    }

    private fun getHttpLogClient(): OkHttpClient {
        val httpClient = OkHttpClient().newBuilder()
            .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        }
        httpClient.addInterceptor(logging)
        return httpClient.build()
    }

    fun getInstance(): Retrofit {
        return retrofit
    }


}