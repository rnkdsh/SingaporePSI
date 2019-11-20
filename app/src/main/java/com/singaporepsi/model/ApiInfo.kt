package com.singaporepsi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ApiInfo(
    @SerializedName("status")
    val status: String = ""
) : Parcelable