package com.ps.cricketapp.presentation.matchdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ps.cricketapp.data.model.MatchDetails
import com.ps.cricketapp.domain.usecase.GetMatchDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchDetailsViewModel @Inject constructor(
    private val getMatchDetailsUseCase: GetMatchDetailsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<MatchDetailsState>(MatchDetailsState.Init)
    val state: StateFlow<MatchDetailsState> get() = _state

    fun fetchMatchDetails(matchId: String) {
        viewModelScope.launch {
            _state.value = MatchDetailsState.Loading
            try {
                val matchDetails = getMatchDetailsUseCase(matchId)
                _state.value = MatchDetailsState.Success(matchDetails)
            } catch (e: Exception) {
                _state.value = MatchDetailsState.Error(e.message ?: "An error occurred")
            }
        }
    }
}

sealed class MatchDetailsState {
    object Init : MatchDetailsState()
    object Loading : MatchDetailsState()
    data class Success(val matchDetails: MatchDetails) : MatchDetailsState()
    data class Error(val message: String) : MatchDetailsState()
}