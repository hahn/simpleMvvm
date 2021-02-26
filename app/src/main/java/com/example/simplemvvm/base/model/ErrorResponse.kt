package com.example.simplemvvm.base.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ErrorResponse(
    @field:SerializedName("code") val code: Int?,
    @field:SerializedName("message") val message: String?
): Parcelable
