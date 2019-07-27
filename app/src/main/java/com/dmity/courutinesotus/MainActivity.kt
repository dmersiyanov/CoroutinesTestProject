package com.dmity.courutinesotus

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dmity.courutinesotus.adapter.MoviesAdapter
import com.dmity.courutinesotus.base.BaseActivity
import com.dmity.courutinesotus.base.BaseRepository
import com.dmity.courutinesotus.models.MovieDTO
import com.dmity.courutinesotus.network.MoviesService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : BaseActivity() {

    private val adapter: MoviesAdapter by lazy { MoviesAdapter(this::onMovieClick) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecycler()

        rvFilmsList.setOnClickListener {
            fetchMovies()
        }

        fetchMovies()

    }


    private fun initRecycler() {
        rvFilmsList.apply {
            val linearLayoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
            layoutManager = linearLayoutManager
        }
    }

    private fun fetchMovies() {
        scope.launch {
            try {
                val response = BaseRepository().safeApiCall(
                    call = { service.getMovies(MoviesService.API_KEY) },
                    errorMessage = "error"
                )

                withContext(Dispatchers.Main) {
                    adapter.setItems(response?.results.toNotNullList())
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun onMovieClick(movie: MovieDTO) {
        FilmDetailActivity.display(
            movieId = movie.id ?: -1,
            context = this
        )
    }


}
