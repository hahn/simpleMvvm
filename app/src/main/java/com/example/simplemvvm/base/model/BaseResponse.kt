package com.example.simplemvvm.base.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class BaseResponse<T>(
    @field:SerializedName("status") val status: String,
    @field:SerializedName("code") val code: Int?,
    @field:SerializedName("data") val data: T?,
    @field:SerializedName("error") val error: ErrorResponse? = null
)
