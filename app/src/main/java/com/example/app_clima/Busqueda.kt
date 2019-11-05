package com.example.app_clima

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_busqueda.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Busqueda : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_busqueda, container, false)
    }
    var array = ArrayList<ClimaResponse>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        seachView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                recyclerView.layoutManager= LinearLayoutManager(context)
                getDatos(query!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("TAG",newText)
                return true
            }
        })


    }

        fun toast(message:String){
            Toast.makeText(context,message,Toast.LENGTH_SHORT).show()

        }

    internal fun  getDatos(name:String ){

        val appid = "2e65127e909e178d0af311a81f39948c"
        val cnt = "5"
        val metric= "metric"
        val retrofil = Retrofit.Builder().baseUrl("http://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofil.create(ServicioClima::class.java)
        val call = service.getDatosClima(name, metric,appid).also {
            it.enqueue(object : Callback<ClimaResponse> {
                override fun onFailure(call: Call<ClimaResponse>?, t: Throwable?) {
                    Log.d("HTTPREQUESTMAL", "Algo mal: ")
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
                            puente(climaRespuesta)
                        }else{
                            Log.d("HTTPREQUEST", response.message())
                            Log.d("HTTPREQUEST", response.errorBody().string())
                        }

                    }
                }
            })
        }

    }
    fun puente(climaRespuesta:ClimaResponse){
        array.add(climaRespuesta)
        val adapter = Adaptador(array)
        recyclerView.adapter = adapter
    }
}

