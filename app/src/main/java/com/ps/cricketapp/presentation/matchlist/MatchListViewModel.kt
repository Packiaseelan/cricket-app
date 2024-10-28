package com.ps.cricketapp.presentation.matchlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ps.cricketapp.data.model.Match
import com.ps.cricketapp.domain.usecase.GetCurrentMatchesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchListViewModel @Inject constructor(
    private val getCurrentMatchesUseCase: GetCurrentMatchesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<MatchListState>(MatchListState.Init)
    val state: StateFlow<MatchListState> get() = _state

    init {
        fetchMatches()
    }

    private fun fetchMatches() {
        viewModelScope.launch {
            _state.value = MatchListState.Loading
            try {
                val matches = getCurrentMatchesUseCase()
                _state.value = MatchListState.Success(matches)
            } catch (e: Exception) {
                _state.value = MatchListState.Error(e.message ?: "An error occurred")
            }
        }
    }
}

sealed class MatchListState {
    object Init : MatchListState()
    object Loading : MatchListState()
    data class Success(val matches: List<Match>) : MatchListState()
    data class Error(val message: String) : MatchListState()
}