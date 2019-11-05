package com.example.app_clima

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//data/2.5/forecast/daily?

interface ServicioClima {
    @GET("data/2.5/forecast?")
    fun getDatosClima(@Query("q") q:String, @Query("units") metric: String,@Query("APPID") appid: String): Call<ClimaResponse>
}
