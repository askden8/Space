package com.shevchenko.space_x.ui.main

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shevchenko.space_x.data.DataRepository
import com.shevchenko.space_x.data.LaunchModel
import com.shevchenko.space_x.data.RocketModel
import com.shevchenko.space_x.utils.PREF_START

class RocketsViewModel(
    private val dataRepository: DataRepository,
    private val sharedPreferences: SharedPreferences) : ViewModel() {

    private val mFavoriteFilter = MutableLiveData<Boolean>()
    val state = MutableLiveData<RocketsListState>()
    private var launchesList = emptyList<LaunchModel>()

    init {
        setupMediator()
        checkFirstLaunch()
    }

    fun showFavorites() {
        val showFavorite = mFavoriteFilter.value ?: false
        mFavoriteFilter.postValue(!showFavorite)
        setupMediator()
    }

    fun updateDate() {
        dataRepository.update()
    }

    private fun getLaunches(): LiveData<List<LaunchModel>> {
        return dataRepository.getLaunches()
    }

    private fun getRockets(): LiveData<List<RocketModel>> {
        return dataRepository.getRocket()
    }

    private fun setupMediator() {
        getRockets().observeForever { rockets ->
            state.postValue(if (rockets != null) {
                RocketsListState.SuccessState(
                    filterList(rockets)
                )
            } else RocketsListState.ErrorState)
        }
        getLaunches().observeForever { launches ->
            launchesList = launches
        }
    }

    private fun checkFirstLaunch() {
        val string = sharedPreferences.getString(PREF_START, "")
        if (string.isNullOrBlank()) {
            val editor = sharedPreferences.edit()
            editor.putString(PREF_START, "start")
            editor.apply()
            state.postValue(RocketsListState.ShowDialogState)
        }
    }

    private fun filterList(list: List<RocketModel>): List<RocketModel> =
        if (mFavoriteFilter.value == true) {
            list.filter { it.active == true }
        } else {
            list
        }

    fun getLaunch(rocket: RocketModel) {
        state.postValue(RocketsListState.ShowDetail(
            rocket,
            launchesList.filter { it.rocket?.rocketId == rocket.rocketId }))
    }
}









