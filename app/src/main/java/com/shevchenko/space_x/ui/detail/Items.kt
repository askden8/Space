package com.shevchenko.space_x.ui.detail

import com.shevchenko.space_x.data.LaunchModel

interface Item

data class HeaderItem(val date: String) : Item

data class LaunchItem(val launchModel: LaunchModel) : Item