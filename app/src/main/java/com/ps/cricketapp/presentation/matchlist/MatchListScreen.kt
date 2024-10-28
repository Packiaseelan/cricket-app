package com.ps.cricketapp.presentation.matchlist

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ps.cricketapp.data.model.Match

@Composable
fun MatchListScreen(navController: NavController, viewModel: MatchListViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()

    when (state) {
        is MatchListState.Init -> {
            // Initial state, can show a placeholder or nothing
        }
        is MatchListState.Loading -> {
            CircularProgressIndicator()
        }
        is MatchListState.Success -> {
            MatchList(matches = (state as MatchListState.Success).matches, navController)
        }
        is MatchListState.Error -> {
            Text(text = (state as MatchListState.Error).message)
        }
    }
}

@Composable
fun MatchList(matches: List<Match>, navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        matches.forEach { match ->
            MatchItem(match = match, onClick = {
                navController.navigate("matchDetails/${match.id}")
            })
        }
    }
}

@Composable
fun MatchItem(match: Match, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = match.name)
            Text(text = match.status)
        }
    }
}