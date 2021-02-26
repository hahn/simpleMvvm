package com.example.simplemvvm.feature.detail.di

import com.example.simplemvvm.di.FeatureScope
import com.example.simplemvvm.di.MyAppComponent
import com.example.simplemvvm.feature.detail.ui.DetailActivity
import dagger.Component

@FeatureScope
@Component(
    dependencies = [MyAppComponent::class],
    modules = [DetailModule::class]
)
interface DetailComponent {
    fun inject(activity: DetailActivity)
}