package com.singaporepsi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Readings(
    @SerializedName("co_eight_hour_max")
    val coEightHourMax: Reading = Reading(),
    @SerializedName("co_sub_index")
    val coSubIndex: Reading = Reading(),
    @SerializedName("no2_one_hour_max")
    val no2OneHourMax: Reading = Reading(),
    @SerializedName("o3_eight_hour_max")
    val o3EightHourMax: Reading = Reading(),
    @SerializedName("o3_sub_index")
    val o3SubIndex: Reading = Reading(),
    @SerializedName("pm10_sub_index")
    val pm10SubIndex: Reading = Reading(),
    @SerializedName("pm10_twenty_four_hourly")
    val pm10TwentyFourHourly: Reading = Reading(),
    @SerializedName("pm25_sub_index")
    val pm25SubIndex: Reading = Reading(),
    @SerializedName("pm25_twenty_four_hourly")
    val pm25TwentyFourHourly: Reading = Reading(),
    @SerializedName("psi_twenty_four_hourly")
    val psiTwentyFourHourly: Reading = Reading(),
    @SerializedName("so2_sub_index")
    val so2SubIndex: Reading = Reading(),
    @SerializedName("so2_twenty_four_hourly")
    val so2TwentyFourHourly: Reading = Reading()
) : Parcelable