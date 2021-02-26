package com.example.simplemvvm.feature.detail.di

import androidx.lifecycle.ViewModel
import com.example.simplemvvm.di.ViewModelKey
import com.example.simplemvvm.feature.detail.ui.DetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DetailModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun provideViewModel(binds: DetailViewModel): ViewModel
}