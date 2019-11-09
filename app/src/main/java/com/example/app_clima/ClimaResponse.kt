package com.example.app_clima

import com.google.gson.annotations.SerializedName


import kotlin.collections.ArrayList

data class ClimaResponse(@SerializedName("city") var city: City,
                         @SerializedName("list")var list: ArrayList<List> = ArrayList())

data class City(@SerializedName("name")var name: String)

data class List(@SerializedName("main")var main:Main,
                @SerializedName("weather")var weather: ArrayList<Weather> = ArrayList(),
                @SerializedName("dt_txt")var date: String)

data class Main(@SerializedName("temp") var day:Float,
                @SerializedName("temp_min")var min:Float,
                @SerializedName("temp_max")var max:Float)

data class Weather(@SerializedName("main")var main:String)

data class CurrentWeather(@SerializedName ("main")var temp:Temp,
                          @SerializedName ("name")var name:String,
                          @SerializedName("weather")var weather: ArrayList<Weather> = ArrayList())

data class Temp(@SerializedName ("temp") var current: Float,
                @SerializedName ("temp_min")var tempMin:Float,
                @SerializedName ("temp_max")var tempMax:Float)










