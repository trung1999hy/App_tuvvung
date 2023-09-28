package com.tmt.vuaghepchu.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.tmt.vuaghepchu.data.repository.sharedrepositoy.SharedRepository
import com.tmt.vuaghepchu.data.repository.sharedrepositoy.SharedRepositoryImpl
import com.tmt.vuaghepchu.data.repository.soundrepository.SoundRepository
import com.tmt.vuaghepchu.data.repository.soundrepository.SoundRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideSharedRepository(
        sharedRepositoryImpl: SharedRepositoryImpl
    ): SharedRepository

    @Binds
    abstract fun provideSoundRepository(
        soundRepositoryImpl: SoundRepositoryImpl
    ): SoundRepository
}