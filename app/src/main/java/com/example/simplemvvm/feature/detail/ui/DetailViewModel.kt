package com.example.simplemvvm.feature.detail.ui

import com.example.simplemvvm.base.MyBaseViewModel
import javax.inject.Inject

class DetailViewModel @Inject constructor(): MyBaseViewModel<DetailViewModel.DetailViewState>() {

    sealed class DetailViewState {

    }
}