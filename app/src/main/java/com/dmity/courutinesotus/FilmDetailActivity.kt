package com.dmity.courutinesotus

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.dmity.courutinesotus.base.BaseActivity
import com.dmity.courutinesotus.base.BaseRepository
import com.dmity.courutinesotus.network.MoviesService
import kotlinx.android.synthetic.main.activity_film_detail.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FilmDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_detail)

        intent.getIntExtra(ARGS_MOVIE_ID, -1).let { id ->
            if (id != -1) {
                loadMovieDetail(id)
            }
        }
    }

    private fun loadMovieDetail(movieId: Int) {
        scope.launch {
            try {
                val response = BaseRepository().safeApiCall(
                    call = { service.getMovieDetail(movieId, MoviesService.API_KEY) },
                    errorMessage = "error"
                )

                withContext(Dispatchers.Main) {
                    response?.let {
                        tvMovieDescription.text = response.overview
                        tvMovieTitle.text = response.title
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    companion object {

        private const val ARGS_MOVIE_ID = "movie_item"

        fun display(movieId: Int, context: Context) {
            context.startActivity(Intent(context, FilmDetailActivity::class.java).apply {
                putExtra(ARGS_MOVIE_ID, movieId)
            })
        }
    }
}
