package com.ps.cricketapp.data.service

import com.ps.cricketapp.data.model.Match
import com.ps.cricketapp.utility.Config
import retrofit2.http.GET
import retrofit2.http.Query

interface CricketApiService {
    @GET("currentMatches")
    suspend fun getCurrentMatches(
        @Query("apikey") apiKey: String = Config.API_KEY,
        @Query("offset") offset: Int = 0
    ): List<Match>


    @GET("match_info")
    suspend fun getMatchInfo(
        @Query("apikey") apiKey: String = Config.API_KEY,
        @Query("id") matchId: String
    ): Match
}
