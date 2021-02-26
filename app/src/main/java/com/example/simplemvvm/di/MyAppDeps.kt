package com.example.simplemvvm.di

import com.example.simplemvvm.feature.home.network.HomeService

interface MyAppDeps  {
    fun homeService(): HomeService
}