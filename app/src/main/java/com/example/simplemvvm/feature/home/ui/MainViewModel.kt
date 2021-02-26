package com.example.simplemvvm.feature.home.ui

import com.example.simplemvvm.base.MyBaseViewModel
import com.example.simplemvvm.feature.home.data.HomeUseCase
import com.example.simplemvvm.feature.home.model.GithubUser
import javax.inject.Inject

class MainViewModel @Inject constructor(private val useCase: HomeUseCase) : MyBaseViewModel<MainViewModel.HomeViewState>() {

    fun getUsers() {
        useCase.getUsers()
            .doOnSubscribe { _state.value = HomeViewState.ShowLoading }
            .doAfterTerminate { _state.value = HomeViewState.HideLoading }
            .subscribe({data ->
                if (data.isEmpty()) {
                    _state.value = HomeViewState.EmptyResult
                } else {
                    _state.postValue(HomeViewState.UserLoaded(data))
                }
            }, {
                _state.value = HomeViewState.Error(it.message.orEmpty())
            }).let(addDisposable::add)
    }

    sealed class HomeViewState {
        object ShowLoading: HomeViewState()
        object HideLoading: HomeViewState()
        object EmptyResult: HomeViewState()

        data class UserLoaded(val data: List<GithubUser>): HomeViewState()
        data class Error(val message: String): HomeViewState()
    }
}