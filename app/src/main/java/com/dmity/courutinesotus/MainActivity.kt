package com.dmity.courutinesotus

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dmity.courutinesotus.adapter.MoviesAdapter
import com.dmity.courutinesotus.models.MovieDTO
import com.dmity.courutinesotus.network.Api
import com.dmity.courutinesotus.network.BaseRepository
import com.dmity.courutinesotus.network.CountriesService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)


    val adapter: MoviesAdapter by lazy { MoviesAdapter(this::onMovieClick) }


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

        val service = Api.getInstance().create(CountriesService::class.java)

        scope.launch {
            try {
                val response = BaseRepository().safeApiCall(
                    call = { service.getMovies(CountriesService.API_KEY) },
                    errorMessage = "error"
                )

                withContext(Dispatchers.Main) {
                    adapter.setItems(response?.results.toNotNullList())
                    toast(response?.totalResults.toString())
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun onMovieClick(moview: MovieDTO) {
        toast(moview.title.toString())
    }

    private inline fun <reified T : Any> List<T?>?.toNotNullList() = this?.filterNotNull().orEmpty()

}
