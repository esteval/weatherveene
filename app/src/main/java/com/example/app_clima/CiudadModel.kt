package com.example.app_clima

data class ClimaFuturo(
    var sky1:String,
    var sky2:String,
    var sky3:String,
    var sky4:String,
    var sky5:String,
    var temp1:String,
    var temp2:String,
    var temp3:String,
    var temp4:String,
    var temp5:String)

data class ClimaAtual(
    var name: String,
    var temp: String,
    var tempMax: String,
    var tempMin: String,
    var sky:String)

data class ClimaTop(
    var name: String?,
    var temp: String?,
    var tempMax: String?,
    var tempMin: String?,
    var sky:String?,
    var sky1:String?,
    var sky2:String?,
    var sky3:String?,
    var sky4:String?,
    var sky5:String?,
    var temp1:String?,
    var temp2:String?,
    var temp3:String?,
    var temp4:String?,
    var temp5:String?)


