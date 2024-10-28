package com.ps.cricketapp.di

import com.ps.cricketapp.data.repository.CricketRepository
import com.ps.cricketapp.data.repository.CricketRepositoryImpl
import com.ps.cricketapp.domain.usecase.GetCurrentMatchesUseCase
import com.ps.cricketapp.domain.usecase.GetMatchDetailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.cricapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideCricketRepository(retrofit: Retrofit): CricketRepository {
        return CricketRepositoryImpl(retrofit)
    }

    @Provides
    fun provideGetCurrentMatchesUseCase(repository: CricketRepository): GetCurrentMatchesUseCase {
        return GetCurrentMatchesUseCase(repository)
    }

    @Provides
    fun provideGetMatchDetailsUseCase(repository: CricketRepository): GetMatchDetailsUseCase {
        return GetMatchDetailsUseCase(repository)
    }
}