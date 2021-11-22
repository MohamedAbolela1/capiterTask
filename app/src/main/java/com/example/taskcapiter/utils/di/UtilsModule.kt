package com.example.taskcapiter.utils.di

import com.example.taskcapiter.utils.validation.network.NetworkValidator
import com.example.taskcapiter.utils.validation.network.NetworkValidatorImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UtilsModule {
    @Binds
    abstract fun bindNetworkValidator(networkValidatorImp: NetworkValidatorImp): NetworkValidator
}
