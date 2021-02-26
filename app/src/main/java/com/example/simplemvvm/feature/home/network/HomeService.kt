package com.example.simplemvvm.feature.home.network

import com.example.simplemvvm.feature.home.model.GithubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface HomeService {
    @GET("users")
    fun getAllUsers(): Single<List<GithubUser>>
}