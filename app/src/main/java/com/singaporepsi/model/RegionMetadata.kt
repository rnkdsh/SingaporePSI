package com.singaporepsi.model

import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RegionMetadata(
    @SerializedName("label_location")
    val labelLocation: LabelLocation = LabelLocation(),
    @SerializedName("name")
    val name: String = "",
    @SerializedName("psi")
    var psi: Int = 0
) : Parcelable {
    fun getLatLng(): LatLng {
        return LatLng(labelLocation.latitude, labelLocation.longitude)
    }

    fun isNational(): Boolean {
        return name.equals("national", ignoreCase = true)
    }
}