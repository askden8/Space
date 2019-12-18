package com.shevchenko.space_x.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RocketModel(
    @SerializedName("active")
    val active: Boolean?,
    @SerializedName("boosters")
    val boosters: Int?,
    @SerializedName("company")
    val company: String?,
    @SerializedName("cost_per_launch")
    val costPerLaunch: Int?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("engines")
    val engines: Engines?,
    @SerializedName("first_flight")
    val firstFlight: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("rocket_id")
    val rocketId: String?,
    @SerializedName("rocket_name")
    val rocketName: String?,
    @SerializedName("rocket_type")
    val rocketType: String?,
    @SerializedName("stages")
    val stages: Int?,
    @SerializedName("success_rate_pct")
    val successRatePct: Int?,
    @SerializedName("wikipedia")
    val wikipedia: String?
) : Serializable