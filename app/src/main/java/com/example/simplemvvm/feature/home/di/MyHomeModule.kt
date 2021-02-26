package com.example.simplemvvm.feature.home.di

import androidx.lifecycle.ViewModel
import com.example.simplemvvm.di.ViewModelKey
import com.example.simplemvvm.feature.home.data.HomeRepository
import com.example.simplemvvm.feature.home.data.HomeRepositoryImpl
import com.example.simplemvvm.feature.home.data.HomeUseCase
import com.example.simplemvvm.feature.home.data.HomeUseCaseImpl
import com.example.simplemvvm.feature.home.ui.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MyHomeModule {

    @Binds
    abstract fun provideRepository(binds: HomeRepositoryImpl): HomeRepository

    @Binds
    abstract fun provideUseCase(binds: HomeUseCaseImpl): HomeUseCase

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun provideViewModel(binds: MainViewModel): ViewModel
}