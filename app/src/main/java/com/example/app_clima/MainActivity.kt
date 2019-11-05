package com.example.app_clima

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private val variable = BottomNavigationView.OnNavigationItemSelectedListener { item->
        when(item.itemId){
            R.id.btHome -> {
                cambiarFragmento(Ciudad())
                return@OnNavigationItemSelectedListener true
            }
            R.id.btSearch ->{
                cambiarFragmento(Busqueda())
                return@OnNavigationItemSelectedListener true
            }
            R.id.btTop ->{
                cambiarFragmento(Top_Ciudades())
                return@OnNavigationItemSelectedListener true
            }
        }
        false

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var navegationBar: BottomNavigationView = findViewById(R.id.nav_barra)
        navegationBar.setOnNavigationItemSelectedListener(variable)
        cambiarFragmento(Ciudad())

    }
    fun tostear(mensaje:String){
        Toast.makeText(this, mensaje,Toast.LENGTH_SHORT).show()
    }

    private fun cambiarFragmento(fragmentNew:Fragment){
        val cambio = supportFragmentManager.beginTransaction()
        cambio.replace(R.id.fragment,fragmentNew)
        cambio.commit()
    }


}
