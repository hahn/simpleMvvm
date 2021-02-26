package com.example.simplemvvm

import androidx.multidex.MultiDexApplication
import com.example.simplemvvm.di.DaggerMyAppComponent
import com.example.simplemvvm.di.MyAppComponent
import com.example.simplemvvm.di.MyDepsProvider
import com.example.simplemvvm.di.MyNetworkModule
import timber.log.Timber

open class App : MultiDexApplication(), MyDepsProvider {

    open lateinit var appComponent: MyAppComponent
        protected set

    override fun onCreate() {
        super.onCreate()
        appComponent = myAppComponent()
        appComponent.inject(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun provideDeps(): MyAppComponent = myAppComponent()

    private fun myAppComponent() = DaggerMyAppComponent.builder()
        .application(this)
        .context(this.applicationContext)
        .networkModule(MyNetworkModule(BuildConfig.HOST_BASE_URL))
        .build()
}