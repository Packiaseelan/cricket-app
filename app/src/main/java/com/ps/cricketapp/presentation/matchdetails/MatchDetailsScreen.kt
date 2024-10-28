package com.ps.cricketapp.presentation.matchdetails

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun MatchDetailsScreen(matchId: String, viewModel: MatchDetailsViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()

    when (state) {
        is MatchDetailsState.Init -> {
            viewModel.fetchMatchDetails(matchId)
        }
        is MatchDetailsState.Loading -> {
            CircularProgressIndicator()
        }
        is MatchDetailsState.Success -> {
            val matchDetails = (state as MatchDetailsState.Success).matchDetails
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Text(text = matchDetails.name)
                Text(text = "Status: ${matchDetails.status}")
                Text(text = "Venue: ${matchDetails.venue}")
                // Add more details as needed
            }
        }
        is MatchDetailsState.Error -> {
            Text(text = (state as MatchDetailsState.Error).message)
        }
    }
}