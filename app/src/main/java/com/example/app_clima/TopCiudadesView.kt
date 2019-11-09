package com.example.app_clima


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_top__ciudades.*


class TopCiudadesView : Fragment() {

    private lateinit var presenter: TopCiudadesPresenter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = TopCiudadesPresenter(this)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top__ciudades, container, false)
    }

    var array = ArrayList<ClimaResponse>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewTop.layoutManager=LinearLayoutManager(this.context)

        presenter.searchWeather("Mazatlan")
        presenter.searchWeather("Mexicali")
        presenter.searchWeather("Los Angeles")
        presenter.searchWeather("Las Vegas")

    }

    val climas: ArrayList<ClimaTop> = ArrayList()
    var adapter : Adaptador? = null

    fun displayRecycler(item: ClimaTop){
        if(isAdded){
            if ( adapter != null ){
                adapter!!.funcion(item)
            }else{
                climas.add(item)
                adapter = Adaptador(climas)
                recyclerViewTop.adapter = adapter
            }
        }
    }
    /*internal fun  getDatos(name:String){

        val appid = "2e65127e909e178d0af311a81f39948c"
        val metric= "metric"
        val retrofil = Retrofit.Builder().baseUrl("http://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofil.create(ParametrosQuery::class.java)
        val call = service.getFutureWeather(name, metric,appid).also {
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
        Log.d("call", call.toString())

    }



    fun puente(climaRespuesta:ClimaResponse){
        array.add(climaRespuesta)
        val adapter = Adaptador(array)
        recyclerViewTop.adapter = adapter
    }*/
}
