package com.dmity.courutinesotus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dmity.courutinesotus.models.FilmsResponse
import com.dmity.courutinesotus.network.Api
import com.dmity.courutinesotus.network.CountriesService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val service = Api().getInstance()?.create(CountriesService::class.java)


        rvFilmsList.setOnClickListener {
            service?.getCountries(CountriesService.API_KEY)
                ?.enqueue(object : Callback<FilmsResponse> {
                    override fun onFailure(call: Call<FilmsResponse>, t: Throwable) {
                        println(t.message)
                    }

                    override fun onResponse(call: Call<FilmsResponse>, response: Response<FilmsResponse>) {
                        println(response)
                    }
                })
        }



    }
}
