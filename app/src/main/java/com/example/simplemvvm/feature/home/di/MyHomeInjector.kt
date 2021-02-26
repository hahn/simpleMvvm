package com.example.simplemvvm.feature.home.di

import com.example.simplemvvm.di.myAppDaggerComponent
import com.example.simplemvvm.feature.home.ui.MainActivity

object MyHomeInjector {
    fun of(activity: MainActivity) {
        DaggerMyHomeComponent.builder()
            .myAppComponent(activity.myAppDaggerComponent())
            .build()
            .inject(activity)
    }
}