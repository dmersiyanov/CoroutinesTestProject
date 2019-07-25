package com.dmity.courutinesotus

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvFilmsList.setOnClickListener {
            fetchMovies()
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
}
