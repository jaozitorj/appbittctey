package com.example.video

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.video.databinding.ActivityExerciciosBinding


private lateinit var binding: ActivityExerciciosBinding
class exercicios : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciciosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_exercicios)

        binding.Nivelexercicio1.setOnClickListener {
            val irparaexercicio1 = Intent(this, telaexercicios::class.java)
            startActivity(irparaexercicio1)
            finish()
        }
    }
}