package com.diegitsen.reignchallenge.data.datasource.hint

import android.content.Context
import com.diegitsen.reignchallenge.data.entity.Hit
import com.diegitsen.reignchallenge.util.Constant
import com.diegitsen.reignchallenge.util.Preferences

class HitLocalDatasource(context: Context): Constant {

    var preference = Preferences(context)
    fun getHits(onHitLocalReadyCallback: OnHitLocalReadyCallback){
        val hits = preference.getHits(HITS_DATA)
        hits?.let { onHitLocalReadyCallback.onLocalDataReady(it)}
    }
    fun saveHits(hits: List<Hit>){
        preference.saveHits(HITS_DATA, hits)
    }

    fun getHitsData():  List<Hit>? {
        return preference.getHits(HITS_DATA)
    }
}

interface OnHitLocalReadyCallback{
    fun onLocalDataReady(data: List<Hit>)
}