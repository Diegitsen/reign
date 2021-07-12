package com.diegitsen.reignchallenge.data.datasource.hint

import android.content.Context
import android.util.Log
import com.diegitsen.reignchallenge.data.entity.Hit
import com.diegitsen.reignchallenge.service.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HitRemoteDatasource(context: Context)  {
    var disposable: Disposable? = null
    private val apiService by lazy { ApiService.create() }
    private val hitLocalDataSource =
        HitLocalDatasource(
            context
        )

    fun getHints(onHitRemoteReadyCallback: OnHitRemoteReadyCallback){


        disposable = apiService.getHits()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    run {
                        if(result.hits.isNotEmpty()){
                            onHitRemoteReadyCallback.onRemoteDataReady(result.hits)
                            hitLocalDataSource.saveHits(result.hits)
                        }else{
                            Log.e("HitRemoteDS", "error getting the data")
                        }
                    }
                },
                { error ->
                    run {
                        Log.e("HitRemoteDS", error.toString())
                    }
                }
            )

    }


}

interface OnHitRemoteReadyCallback{
    fun onRemoteDataReady(data: List<Hit>)
}