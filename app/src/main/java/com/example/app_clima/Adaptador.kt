package com.example.app_clima

import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adaptador(var ciudad: ArrayList<ClimaResponse>): RecyclerView.Adapter<Adaptador.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.tarjeta_ciudad,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return ciudad.size
    }

    override fun onBindViewHolder(holder: Adaptador.ViewHolder, position: Int) {
        holder.enlazarObjetos(ciudad[position])
    }

    class ViewHolder(vista:View): RecyclerView.ViewHolder(vista){

        fun enlazarObjetos(ciudad:ClimaResponse){
            val name: TextView = itemView.findViewById(R.id.textCiudad)
            val textTemp: TextView = itemView.findViewById(R.id.textTemp)
            val textTempMin: TextView = itemView.findViewById(R.id.textTempMin)
            val textTempMax: TextView = itemView.findViewById(R.id.textTempMax)
            val tempDia1: TextView = itemView.findViewById(R.id.tempDia1Mini)
            val tempDia2: TextView = itemView.findViewById(R.id.tempDia2Mini)
            val tempDia3: TextView = itemView.findViewById(R.id.tempDia3Mini)
            val tempDia4: TextView = itemView.findViewById(R.id.tempDia4Mini)
            val tempDia5: TextView = itemView.findViewById(R.id.tempDia5Mini)
            val imaDia1: ImageView = itemView.findViewById(R.id.imaDia1)
            val imaDia2: ImageView = itemView.findViewById(R.id.imaDia2)
            val imaDia3: ImageView = itemView.findViewById(R.id.imaDia3)
            val imaDia4: ImageView = itemView.findViewById(R.id.imaDia4)
            val imaDia5: ImageView = itemView.findViewById(R.id.imaDia5)

            name.text = ciudad.city.name
            textTemp.text = ciudad.list[0].main.day.toInt().toString() + "°"
            textTempMax.text = ciudad.list[0].main.max.toInt().toString() + "°"
            textTempMin.text = ciudad.list[0].main.min.toInt().toString() + "°"
            val indexNumbers: ArrayList<Int> = ArrayList()
            for((index, value) in ciudad.list.withIndex()){
                if(ciudad.list[index].date.contains("12:00:00")){
                    indexNumbers.add(index)
                }
            }
            for((index, value) in indexNumbers.withIndex()){
                Log.d("INDEX", index.toString())
                Log.d("VALUE", value.toString())
                Log.d("NUBES", ciudad.list[value].weather[0].main)
                when(index) {
                    0 -> if (ciudad.list[value].weather[0].main != "Clear") {
                        imaDia1.setImageResource(R.drawable.cloud)
                    }
                    1 -> if (ciudad.list[value].weather[0].main != "Clear") {
                        imaDia2.setImageResource(R.drawable.cloud)
                    }
                    2 -> if (ciudad.list[value].weather[0].main != "Clear") {
                        imaDia3.setImageResource(R.drawable.cloud)
                    }
                    3 -> if (ciudad.list[value].weather[0].main != "Clear") {
                        imaDia4.setImageResource(R.drawable.cloud)
                    }
                    4 -> if (ciudad.list[value].weather[0].main != "Clear") {
                        imaDia5.setImageResource(R.drawable.cloud)
                    }
                }
            }


            tempDia1.text = ciudad.list[indexNumbers[0]].main.day.toInt().toString() + "°"
            tempDia2.text = ciudad.list[indexNumbers[1]].main.day.toInt().toString() + "°"
            tempDia3.text = ciudad.list[indexNumbers[2]].main.day.toInt().toString() + "°"
            tempDia4.text = ciudad.list[indexNumbers[3]].main.day.toInt().toString() + "°"
            tempDia5.text = ciudad.list[indexNumbers[4]].main.day.toInt().toString() + "°"
        }
    }
}