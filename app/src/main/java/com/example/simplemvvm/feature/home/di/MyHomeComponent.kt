package com.example.simplemvvm.feature.home.di

import com.example.simplemvvm.di.FeatureScope
import com.example.simplemvvm.di.MyAppComponent
import com.example.simplemvvm.feature.home.ui.MainActivity
import dagger.Component

@FeatureScope
@Component(
    dependencies = [MyAppComponent::class],
    modules = [MyHomeModule::class]
)
interface MyHomeComponent {
    fun inject(inject: MainActivity)
}