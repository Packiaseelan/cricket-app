package com.ps.cricketapp.domain.usecase

import com.ps.cricketapp.data.repository.CricketRepository
import com.ps.cricketapp.data.model.Match

class GetCurrentMatchesUseCase(private val repository: CricketRepository) {
    suspend operator fun invoke(): List<Match> {
        return repository.getCurrentMatches()
    }
}