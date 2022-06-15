package com.example.video

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class exercicios : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercicios)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}