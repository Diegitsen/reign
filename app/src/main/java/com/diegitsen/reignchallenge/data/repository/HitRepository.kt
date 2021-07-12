package com.diegitsen.reignchallenge.data.repository

import android.content.Context
import com.diegitsen.reignchallenge.data.datasource.hint.HitRemoteDatasource
import com.diegitsen.reignchallenge.data.datasource.hint.OnHitRemoteReadyCallback
import com.diegitsen.reignchallenge.data.entity.Hit

class HitRepository(context: Context) {
    private val remoteDataSource =
        HitRemoteDatasource(
            context
        )

    fun getHits(onHitRepositoryReadyCallback: OnHitRepositoryReadyCallback) {
        remoteDataSource.getHints(object :
            OnHitRemoteReadyCallback {
            override fun onRemoteDataReady(data: List<Hit>) {
                onHitRepositoryReadyCallback.onDataReady(data)
            }
        })
    }
}

interface OnHitRepositoryReadyCallback {
    fun onDataReady(data: List<Hit>)
}