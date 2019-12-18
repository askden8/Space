package com.shevchenko.space_x.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LaunchModel(
    @SerializedName("details")
    val details: String?,
    @SerializedName("flight_number")
    val flightNumber: Int?,
    @SerializedName("is_tentative")
    val isTentative: Boolean?,
    @SerializedName("launch_date_local")
    val launchDateLocal: String?,
    @SerializedName("launch_date_unix")
    val launchDateUnix: Int?,
    @SerializedName("launch_date_utc")
    val launchDateUtc: String?,
    @SerializedName("launch_year")
    val launchYear: String,
    @SerializedName("mission_name")
    val missionName: String?,
    @SerializedName("rocket")
    val rocket: Rocket?,
    @SerializedName("tbd")
    val tbd: Boolean?,
    @SerializedName("links")
    val links: Links?,
    @SerializedName("launch_success")
    val launchSuccess: Boolean?,
    @SerializedName("tentative_max_precision")
    val tentativeMaxPrecision: String?,
    @SerializedName("upcoming")
    val upcoming: Boolean?
) : Parcelable