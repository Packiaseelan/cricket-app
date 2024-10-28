package com.ps.cricketapp.domain.usecase

import com.ps.cricketapp.data.model.MatchDetails
import com.ps.cricketapp.data.repository.CricketRepository

class GetMatchDetailsUseCase(private val repository: CricketRepository) {
    suspend operator fun invoke(matchId: String): MatchDetails {
        return repository.getMatchDetails(matchId)
    }
}
