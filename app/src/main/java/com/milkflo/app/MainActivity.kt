package com.milkflo.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler


class MainActivity : AppCompatActivity() {

    private val splashTimeOut = 3000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed(
            {
                val i = Intent(this@MainActivity, Registration::class.java)
                startActivity(i)
                finish()
            }, splashTimeOut)
    }
}