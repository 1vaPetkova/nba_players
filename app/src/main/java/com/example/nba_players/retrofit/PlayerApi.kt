package com.example.nba_players.retrofit


import com.example.nba_players.entities.Player
import com.example.nba_players.entities.Players
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlayerApi {
    @GET("players")
    fun getAllPlayers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("search") search: String
    ): Call<Players>

    @GET("players/{id}")
    fun getSpecificPlayer(@Path("id") id: Int): Call<Player>
}