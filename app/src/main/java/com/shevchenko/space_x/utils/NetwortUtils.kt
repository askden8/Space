package com.shevchenko.space_x.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import okhttp3.Interceptor
import okhttp3.Response

fun hasNetwork(context: Context): Boolean? {
    var isConnected: Boolean? = false
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    if (activeNetwork != null && activeNetwork.isConnected)
        isConnected = true
    return isConnected
}


fun initCache(
    chain: Interceptor.Chain,
    context: Context): Response? {
    var request = chain.request()
    request = if (hasNetwork(context)!!)
        request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
    else
        request.newBuilder().header(
            "Cache-Control",
            "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
        ).build()
    return chain.proceed(request)
}