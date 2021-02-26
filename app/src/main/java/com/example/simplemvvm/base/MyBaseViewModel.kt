package com.example.simplemvvm.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

open class MyBaseViewModel<T> : ViewModel() {

    protected val _state: MutableLiveData<T> = MutableLiveData()
    val state: LiveData<T>
        get() = _state

    val addDisposable by lazy { CompositeDisposable() }

    override fun onCleared() {
        addDisposable.clear()
    }
}