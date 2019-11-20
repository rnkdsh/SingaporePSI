package com.singaporepsi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(
    @SerializedName("readings")
    val readings: Readings = Readings(),
    @SerializedName("timestamp")
    val timestamp: String = "",
    @SerializedName("update_timestamp")
    val updateTimestamp: String = ""
) : Parcelable