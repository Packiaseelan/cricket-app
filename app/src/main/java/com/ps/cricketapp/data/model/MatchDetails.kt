package com.ps.cricketapp.data.model

data class MatchDetails(
    val id: String,
    val name: String,
    val matchType: String,
    val status: String,
    val venue: String,
    val date: String,
    val dateTimeGMT: String,
    val teams: List<String>,
    val score: List<Score>,
    val tossWinner: String,
    val tossChoice: String,
    val matchWinner: String,
    val series_id: String,
    val fantasyEnabled: Boolean,
    val bbbEnabled: Boolean,
    val hasSquad: Boolean,
    val matchStarted: Boolean,
    val matchEnded: Boolean
)