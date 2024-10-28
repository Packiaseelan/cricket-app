package com.ps.cricketapp.data.model

data class CricketApiResponse(
    val apikey: String,
    val data: List<Match>
)

data class Match(
    val id: String,
    val name: String,
    val matchType: String,
    val status: String,
    val venue: String,
    val date: String,
    val dateTimeGMT: String,
    val teams: List<String>,
    val teamInfo: List<TeamInfo>?,
    val score: List<Score>,
    val seriesId: String,
    val fantasyEnabled: Boolean,
    val bbbEnabled: Boolean,
    val hasSquad: Boolean,
    val matchStarted: Boolean,
    val matchEnded: Boolean
)