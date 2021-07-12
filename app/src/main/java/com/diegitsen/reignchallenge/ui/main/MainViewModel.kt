package com.diegitsen.reignchallenge.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.diegitsen.reignchallenge.data.entity.Hit
import com.diegitsen.reignchallenge.data.repository.HitRepository
import com.diegitsen.reignchallenge.data.repository.OnHitRepositoryReadyCallback

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val isLoading = MutableLiveData<Boolean>()
    var mHits = MutableLiveData<List<Hit>>()
    private var hitRepository = HitRepository(getApplication())

    fun getHits(){
        Log.e("hey", ":O")
        isLoading.value = true
        hitRepository.getHits(object : OnHitRepositoryReadyCallback{
            override fun onDataReady(data: List<Hit>) {
                isLoading.value = false
                mHits.value = data
            }
        })
    }
}