package fus.com.vuatuvung.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fus.com.vuatuvung.data.repository.sharedrepositoy.SharedRepository
import fus.com.vuatuvung.data.repository.sharedrepositoy.SharedRepositoryImpl
import fus.com.vuatuvung.data.repository.soundrepository.SoundRepository
import fus.com.vuatuvung.data.repository.soundrepository.SoundRepositoryImpl

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