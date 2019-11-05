package com.example.app_clima

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_ciudad.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Ciudad : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ciudad, container, false)

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDatos()

    }


    internal fun  getDatos(){
        val name = "Mexicali"
        val appid = "2e65127e909e178d0af311a81f39948c"
        val cnt = "5"
        val metric= "metric"


        val retrofil = Retrofit.Builder().baseUrl("http://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofil.create(ServicioClima::class.java)
        val call = service.getDatosClima(name, metric,appid).also {
            it.enqueue(object : Callback<ClimaResponse>{
                override fun onFailure(call: Call<ClimaResponse>?, t: Throwable?) {
                    tvCiudad.text = "mal"
                    Log.d("HTTPREQUESTMAL", t.toString())
                }

                override fun onResponse(
                    call: Call<ClimaResponse>?,
                    response: Response<ClimaResponse>?
                ) {
                    Log.d("HTTPREQUEST", response.toString())
                    if (response != null) {
                        if ( response.code() == 200){
                            val climaRespuesta = response.body()!!
                            tvCiudad.text = climaRespuesta.city.name
                            tvTempActual.text = climaRespuesta.list[0].main.day.toInt().toString() + "°"
                            val indexNumbers: ArrayList<Int> = ArrayList()
                            for((index, value) in climaRespuesta.list.withIndex()){
                                if(climaRespuesta.list[index].date.contains("12:00:00")){
                                    indexNumbers.add(index)
                                }
                            }
                            for((index, value) in indexNumbers.withIndex()){
                                when(index) {
                                    0 -> if (climaRespuesta.list[value].weather[0].main != "Clear") {
                                        imaDia1.setImageResource(R.drawable.cloud)
                                    }
                                    1 -> if (climaRespuesta.list[value].weather[0].main != "Clear") {
                                        imaDia2.setImageResource(R.drawable.cloud)
                                    }
                                    2 -> if (climaRespuesta.list[value].weather[0].main != "Clear") {
                                        imaDia3.setImageResource(R.drawable.cloud)
                                    }
                                    3 -> if (climaRespuesta.list[value].weather[0].main != "Clear") {
                                        imaDia4.setImageResource(R.drawable.cloud)
                                    }
                                    4 -> if (climaRespuesta.list[value].weather[0].main != "Clear") {
                                        imaDia5.setImageResource(R.drawable.cloud)
                                    }
                                }
                            }
                            tvDia1.text = climaRespuesta.list[indexNumbers[0]].main.day.toInt().toString() + "°"
                            tvDia2.text = climaRespuesta.list[indexNumbers[1]].main.day.toInt().toString() + "°"
                            tvDia3.text = climaRespuesta.list[indexNumbers[2]].main.day.toInt().toString() + "°"
                            tvDia4.text = climaRespuesta.list[indexNumbers[3]].main.day.toInt().toString() + "°"
                            tvDia5.text = climaRespuesta.list[indexNumbers[4]].main.day.toInt().toString() + "°"

                        }else{
                            Log.d("HTTPREQUEST", response.message())
                            Log.d("HTTPREQUEST", response.errorBody().string())
                        }

                    }
                }
            })
        }
    }

}
