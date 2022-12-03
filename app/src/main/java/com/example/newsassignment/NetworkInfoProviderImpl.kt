package com.example.newsassignment

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.example.newsassignment.domain.NetworkInfoProvider
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class NetworkInfoProviderImpl @Inject constructor(private val connectivityManager: ConnectivityManager) :
    NetworkInfoProvider {

    override fun hasNetwork(): Boolean {
        val caps = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return caps?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }

    override fun listenToChanges(): Flow<Boolean> {
        return callbackFlow {
            val networkCallback = object : ConnectivityManager.NetworkCallback() {

                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    this@callbackFlow.trySend(true)
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    this@callbackFlow.trySend(false)
                }
            }

            connectivityManager.requestNetwork(
                NetworkRequest.Builder()
                    .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                    .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                    .build(), networkCallback
            )

            this.awaitClose {
                connectivityManager.unregisterNetworkCallback(networkCallback)
            }
        }
    }

}