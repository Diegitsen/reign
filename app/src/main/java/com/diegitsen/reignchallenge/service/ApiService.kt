package com.diegitsen.reignchallenge.service

import com.diegitsen.reignchallenge.data.entity.Model
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("search_by_date?query=mobile")
    fun getHits(): Observable<Model.Result>

    companion object {
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://hn.algolia.com/api/v1/")
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }

}