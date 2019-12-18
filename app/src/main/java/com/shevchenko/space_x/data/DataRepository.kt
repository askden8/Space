package com.shevchenko.space_x.data

import android.telecom.Call
import androidx.lifecycle.LiveData
import com.shevchenko.space_x.network.Api
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback
import androidx.lifecycle.MutableLiveData

interface DataRepository {
    fun getLaunches(): LiveData<List<LaunchModel>>
    fun getRocket(): LiveData<List<RocketModel>>
    fun update()
}

class DataRepositoryImp(private val api: Api) : DataRepository {
    override fun getLaunches(): LiveData<List<LaunchModel>> {
        api.getLaunches().enqueue(
            object : Callback, retrofit2.Callback<List<LaunchModel>> {
                override fun onFailure(call: Call<List<LaunchModel>>, t: Throwable) {
                    t.localizedMessage
                }

                override fun onResponse(
                    call: Call<List<LaunchModel>>,
                    response: Response<List<LaunchModel>>) {
                    if (response.isSuccessful) {
                        launchData.postValue(response.body())
                    }
                }
            }
        )
        return launchData
    }

    val rocketData = MutableLiveData<List<RocketModel>>()
    val launchData = MutableLiveData<List<LaunchModel>>()

    override fun getRocket(): LiveData<List<RocketModel>> {
        api.getRockets().enqueue(
            object : Callback, retrofit2.Callback<List<RocketModel>> {
                override fun onFailure(call: Call<List<RocketModel>>, t: Throwable) {
                    t.localizedMessage
                    rocketData.value = null
                }

                override fun onResponse(
                    call: Call<List<RocketModel>>,
                    response: Response<List<RocketModel>>) {
                    if (response.isSuccessful) {
                        rocketData.postValue(response.body())
                    }
                }
            }
        )
        return rocketData
    }

    override fun update() {
        getLaunches()
        getRocket()
    }
}