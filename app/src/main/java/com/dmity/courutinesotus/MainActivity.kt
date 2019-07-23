package com.dmity.courutinesotus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        unirest.get("https://restcountries-v1.p.rapidapi.com/all")
//            .header("X-RapidAPI-Host", "restcountries-v1.p.rapidapi.com")
//            .header("X-RapidAPI-Key", "3d24c47c88msh44b5be0ebd52ffbp194530jsn65a37bef31e4")
//            .end(function (result) {
//                console.log(result.status, result.headers, result.body);
//            });
    }
}
