package com.example.rinjin.di

import com.example.rinjin.data.remote.GithubApiClient
import com.example.rinjin.data.repository.UsersRepositoryImpl
import com.example.rinjin.domain.repository.UsersRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    const val BASE_URL = "https://api.github.com/"

    @Provides
    @Singleton
    fun provideUsers(): GithubApiClient {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(Moshi.Builder().add(KotlinJsonAdapterFactory()).build()),
            )
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideUsersRepository(api: GithubApiClient): UsersRepository {
        return UsersRepositoryImpl(api)
    }
}
