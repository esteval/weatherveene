package com.example.app_clima

import android.util.Log
import androidx.fragment.app.Fragment
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CiudadPresenter(private val view: CiudadView) : Fragment(){

    //PIDE LOS DATOS DE CLIMA
    fun searchWeather(cityName:String){
        val APPID = "2e65127e909e178d0af311a81f39948c"
        doAsync {
            val retrofit = Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            //PIDE LOS DATOS DE CLIMA ACTUAL A LA API
            val callCurrent = retrofit.create(ParametrosQuery::class.java).getCurrentWeather(cityName, "metric", APPID).execute()
            val resultCurrent = callCurrent.body() as CurrentWeather
            //PIDE LOS DATOS DE CLIMA A 5 DIAS A LA API
            val callFuture = retrofit.create(ParametrosQuery::class.java).getFutureWeather(cityName, "metric", APPID).execute()
            val resultFuture = callFuture.body() as ClimaResponse
            uiThread {
                modelar(resultCurrent,resultFuture)
            }
        }
    }
    //MODELA LOS DATOS
    fun modelar(resultCurrent: CurrentWeather, resultFuture: ClimaResponse){
        val clima = ClimaTop(null, null,null,null,null,null,null,null,null,null,null,null,null,null,null)
        clima.name = resultCurrent.name
        clima.temp = resultCurrent.temp.current.toInt().toString()
        clima.tempMax = resultCurrent.temp.tempMax.toInt().toString()
        clima.tempMin = resultCurrent.temp.tempMin.toInt().toString()
        clima.sky = resultCurrent.weather[0].main
        val i = separarDias(resultFuture)
        clima.sky1 = resultFuture.list[i[0]].weather[0].main
        clima.sky2 = resultFuture.list[i[1]].weather[0].main
        clima.sky3 = resultFuture.list[i[2]].weather[0].main
        clima.sky4 = resultFuture.list[i[3]].weather[0].main
        clima.sky5 = resultFuture.list[i[4]].weather[0].main
        clima.temp1 = resultFuture.list[i[0]].main.day.toInt().toString()
        clima.temp2 = resultFuture.list[i[1]].main.day.toInt().toString()
        clima.temp3 = resultFuture.list[i[2]].main.day.toInt().toString()
        clima.temp4 = resultFuture.list[i[3]].main.day.toInt().toString()
        clima.temp5 = resultFuture.list[i[4]].main.day.toInt().toString()
        view.displayDatos(clima)
    }
    //RETORNA LOS INDICES DE OBJETOS QUE SON CADA DÍA
    fun separarDias(objeto : ClimaResponse) : MutableList<Int>{
        val i : MutableList<Int> = mutableListOf()
        for((index, value) in objeto.list.withIndex()){
            if(objeto.list[index].date.contains("12:00:00")){
                i.add(index)
            }
            Log.d("VALUE", value.toString())
        }
        return i
    }
}

