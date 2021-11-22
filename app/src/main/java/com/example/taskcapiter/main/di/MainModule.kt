package com.example.taskcapiter.main.di

import com.example.taskcapiter.main.data.MainRepository
import com.example.taskcapiter.main.domain.IMainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class MainModule {
    @Binds
    internal abstract fun bindMainRepository(mainRepository: MainRepository): IMainRepository
}