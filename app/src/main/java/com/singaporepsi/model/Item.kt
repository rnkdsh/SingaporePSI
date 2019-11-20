package com.singaporepsi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.ZonedDateTime

@Parcelize
data class Item(
    @SerializedName("readings")
    val readings: Readings = Readings(),
    @SerializedName("timestamp")
    val timestamp: ZonedDateTime = ZonedDateTime.now(),
    @SerializedName("update_timestamp")
    val updateTimestamp: ZonedDateTime = ZonedDateTime.now()
) : Parcelable