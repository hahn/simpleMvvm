package com.example.simplemvvm.feature.home.data

import com.example.simplemvvm.feature.home.model.GithubUser
import com.example.simplemvvm.utils.singleIo
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

interface HomeUseCase {
    fun getUsers(): Single<List<GithubUser>>
}

class HomeUseCaseImpl @Inject constructor(private val homeRepository: HomeRepository): HomeUseCase {
    override fun getUsers(): Single<List<GithubUser>> {
        return homeRepository.getUsers().compose(singleIo())
    }
}