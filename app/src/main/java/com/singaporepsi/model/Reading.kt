package com.singaporepsi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Reading(
    @SerializedName("central")
    val central: Double = 0.0,
    @SerializedName("east")
    val east: Double = 0.0,
    @SerializedName("national")
    val national: Double = 0.0,
    @SerializedName("north")
    val north: Double = 0.0,
    @SerializedName("south")
    val south: Double = 0.0,
    @SerializedName("west")
    val west: Double = 0.0
) : Parcelable