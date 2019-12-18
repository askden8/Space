package com.shevchenko.space_x.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Engines(
    @SerializedName("engine_loss_max")
    val engineLossMax: String?,
    @SerializedName("layout")
    val layout: String?,
    @SerializedName("number")
    val number: Int,
    @SerializedName("propellant_1")
    val propellant1: String,
    @SerializedName("propellant_2")
    val propellant2: String,
    @SerializedName("thrust_to_weight")
    val thrustToWeight: String?,
    @SerializedName("type")
    val type: String,
    @SerializedName("version")
    val version: String
) : Serializable