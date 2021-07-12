package com.diegitsen.reignchallenge.util

import android.content.Context
import android.content.SharedPreferences
import android.location.Location
import com.diegitsen.reignchallenge.data.entity.Hit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Preferences (var context: Context?){

    var pref: SharedPreferences? = null


    fun saveHits(key: String, hits: List<Hit>){
        val editor = pref!!.edit()
        val jsonString = Gson().toJson(hits)
        editor.putString(key, jsonString).apply()
    }

    fun getHits(key: String): List<Hit>?{
        val jsonString = pref!!.getString(key, "")
        return Gson().fromJson(jsonString, object: TypeToken<ArrayList<Hit>>(){}.type)
    }




    fun clearPreferences(){
        pref!!.edit().clear().apply()
    }

    init {
        pref = context!!.getSharedPreferences(context!!.packageName, Context.MODE_PRIVATE)
    }
}
