package com.example.app_clima

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class Splashscreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        Handler().postDelayed({
            startActivity(Intent(this@Splashscreen, MainActivity::class.java))
        finish()
        },1000)

    }
}



