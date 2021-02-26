package com.example.simplemvvm.di

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MyNetworkModule::class])
interface MyAppComponent : MyAppDeps {

    fun inject(application: Application)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        @BindsInstance
        fun application(application: Application): Builder

        fun networkModule(networkModule: MyNetworkModule): Builder

        fun build(): MyAppComponent
    }
}