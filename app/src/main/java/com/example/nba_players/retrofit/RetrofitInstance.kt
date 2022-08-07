package com.example.nba_players.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: PlayerApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.balldontlie.io/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PlayerApi::class.java)
    }

}