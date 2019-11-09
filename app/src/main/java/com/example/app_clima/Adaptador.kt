package com.example.app_clima

import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adaptador(var ciudades: ArrayList<ClimaTop>): RecyclerView.Adapter<Adaptador.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.tarjeta_ciudad,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return ciudades.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.enlazarObjetos(ciudades[position])
    }

    fun funcion(ciudad: ClimaTop){
        ciudades.add(ciudad)
        notifyItemInserted(ciudades.size-1)
    }

    class ViewHolder(vista:View): RecyclerView.ViewHolder(vista){

        fun enlazarObjetos(ciudad:ClimaTop){
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

            name.text = ciudad.name
            textTemp.text = ciudad.temp
            textTempMax.text = ciudad.tempMax
            textTempMin.text = ciudad.tempMin
            if(ciudad.sky1 != "Clear")imaDia1.setImageResource(R.drawable.cloud)
            if(ciudad.sky2 != "Clear")imaDia2.setImageResource(R.drawable.cloud)
            if(ciudad.sky3 != "Clear")imaDia3.setImageResource(R.drawable.cloud)
            if(ciudad.sky4 != "Clear")imaDia4.setImageResource(R.drawable.cloud)
            if(ciudad.sky5 != "Clear")imaDia5.setImageResource(R.drawable.cloud)
            tempDia1.text = ciudad.temp1
            tempDia2.text = ciudad.temp2
            tempDia3.text = ciudad.temp3
            tempDia4.text = ciudad.temp4
            tempDia5.text = ciudad.temp5

        }
    }
}