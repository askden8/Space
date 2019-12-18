package com.shevchenko.space_x.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Links(
    @SerializedName("mission_patch")
    val missionPatch: String?
) : Parcelable