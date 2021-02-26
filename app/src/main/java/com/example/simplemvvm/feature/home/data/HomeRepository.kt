package com.example.simplemvvm.feature.home.data

import com.example.simplemvvm.feature.home.model.GithubUser
import com.example.simplemvvm.feature.home.network.HomeService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

interface HomeRepository {
    fun getUsers(): Single<List<GithubUser>>
}

class HomeRepositoryImpl @Inject constructor(private val homeService: HomeService): HomeRepository {

    override fun getUsers(): Single<List<GithubUser>> = homeService.getAllUsers()
}