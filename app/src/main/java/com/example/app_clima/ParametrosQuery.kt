package com.example.app_clima

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//data/2.5/forecast/daily?

interface ParametrosQuery {
    @GET("data/2.5/forecast?")
    fun getFutureWeather(@Query("q") q:String, @Query("units") metric: String,@Query("APPID") appid: String): Call<ClimaResponse>
    @GET("data/2.5/weather?")
    fun getCurrentWeather(@Query("q") q:String, @Query("units") metric: String, @Query("APPID") appid: String): Call<CurrentWeather>

}
