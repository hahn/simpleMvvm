package com.example.simplemvvm.di

interface MyDepsProvider {
    val appDeps: MyAppComponent
        get() = provideDeps()

    fun provideDeps(): MyAppComponent
}