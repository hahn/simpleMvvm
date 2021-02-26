package com.example.simplemvvm.feature.home.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubUser(
    @field:SerializedName("login") val login: String? = null,
    @field:SerializedName("id") val id:Int? = null,
    @field:SerializedName("avatar_url") val avatarUrl: String? = null,
    @field:SerializedName("url") val url: String? = null
): Parcelable
