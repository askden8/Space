package com.shevchenko.space_x.ui.main

import com.shevchenko.space_x.data.LaunchModel
import com.shevchenko.space_x.data.RocketModel

sealed class RocketsListState {
    object LoadingState : RocketsListState()
    object ErrorState : RocketsListState()
    object ShowDialogState : RocketsListState()
    data class SuccessState(val itemList: List<RocketModel>) : RocketsListState()
    data class ShowDetail(val rocket: RocketModel, val launchList: List<LaunchModel>) :
        RocketsListState()
}
