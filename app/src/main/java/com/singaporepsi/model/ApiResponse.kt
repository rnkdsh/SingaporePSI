package com.singaporepsi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ApiResponse(
    @SerializedName("api_info")
    val apiInfo: ApiInfo = ApiInfo(),
    @SerializedName("items")
    val items: List<Item> = listOf(),
    @SerializedName("region_metadata")
    val regionMetadata: List<RegionMetadata> = listOf()
) : Parcelable