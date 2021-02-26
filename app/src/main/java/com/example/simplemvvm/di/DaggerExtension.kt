package com.example.simplemvvm.di

import android.app.Activity

fun Activity.myAppDaggerComponent(): MyAppComponent {
    return (this.application as MyDepsProvider).appDeps
}