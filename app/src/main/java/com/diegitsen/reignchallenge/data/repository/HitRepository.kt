package com.diegitsen.reignchallenge.data.repository

import android.content.Context
import android.net.ConnectivityManager
import com.diegitsen.reignchallenge.data.datasource.hint.HitLocalDatasource
import com.diegitsen.reignchallenge.data.datasource.hint.HitRemoteDatasource
import com.diegitsen.reignchallenge.data.datasource.hint.OnHitLocalReadyCallback
import com.diegitsen.reignchallenge.data.datasource.hint.OnHitRemoteReadyCallback
import com.diegitsen.reignchallenge.data.entity.Hit

class HitRepository(context: Context) {
    private val remoteDataSource =
        HitRemoteDatasource(
            context
        )
    private val localDataSource =
        HitLocalDatasource(
            context
        )
    private val netManager = NetManager(context)

    fun getHits(onHitRepositoryReadyCallback: OnHitRepositoryReadyCallback) {

        netManager.isConnectedToInternet?.let{
            if(it){
                remoteDataSource.getHints(object :
                    OnHitRemoteReadyCallback {
                    override fun onRemoteDataReady(data: List<Hit>) {
                        onHitRepositoryReadyCallback.onDataReady(data)
                    }
                })
            }else{
                localDataSource.getHits(object : OnHitLocalReadyCallback {
                    override fun onLocalDataReady(data: List<Hit>) {
                        onHitRepositoryReadyCallback.onDataReady(data)
                    }
                })
            }
        }

    }
}

interface OnHitRepositoryReadyCallback {
    fun onDataReady(data: List<Hit>)
}

class NetManager(private var applicationContext: Context) {
    private var status: Boolean? = false

    val isConnectedToInternet: Boolean?
        get() {
            val conManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val ni = conManager.activeNetworkInfo
            return ni != null && ni.isConnected
        }
}