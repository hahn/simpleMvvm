package com.example.simplemvvm.feature.detail.di

import com.example.simplemvvm.di.myAppDaggerComponent
import com.example.simplemvvm.feature.detail.ui.DetailActivity

object DetailInjector {
    fun of(activity: DetailActivity) {
        DaggerDetailComponent.builder()
            .myAppComponent(activity.myAppDaggerComponent())
            .build()
            .inject(activity)
    }
}