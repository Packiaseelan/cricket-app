package com.ps.cricketapp.data.repository

import com.ps.cricketapp.data.model.Match
import com.ps.cricketapp.data.model.MatchDetails
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface CricketRepository {
    suspend fun getCurrentMatches(): List<Match>
    suspend fun getMatchDetails(matchId: String): MatchDetails
}

class CricketRepositoryImpl(private val retrofit: Retrofit) : CricketRepository {

    private val api = retrofit.create(CricketApi::class.java)

    override suspend fun getCurrentMatches(): List<Match> {
        return api.getCurrentMatches("your_api_key").data
    }

    override suspend fun getMatchDetails(matchId: String): MatchDetails {
        return api.getMatchDetails("your_api_key", matchId).data
    }

    interface CricketApi {
        @GET("currentMatches")
        suspend fun getCurrentMatches(@Query("apikey") apiKey: String): MatchesResponse

        @GET("match_info")
        suspend fun getMatchDetails(@Query("apikey") apiKey: String, @Query("id") matchId: String): MatchDetailsResponse
    }

    data class MatchesResponse(val data: List<Match>)
    data class MatchDetailsResponse(val data: MatchDetails)
}