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

        binding.Nivelexercicio1.setOnClickListener {
            val Irparatelaexercicios = Intent(this, telaexercicios::class.java)
            startActivity(Irparatelaexercicios)
            finish()
        }

        binding.Nivelexercicio2.setOnClickListener {
            val Irparatelaexercicios = Intent(this, telaexercicios2::class.java)
            startActivity(Irparatelaexercicios)
            finish()
        }

        binding.Nivelexercicio3.setOnClickListener {
            val Irparatelaexercicios = Intent(this, telaexercicio3::class.java)
            startActivity(Irparatelaexercicios)
            finish()
        }

        binding.Nivelexercicio4.setOnClickListener {
            val Irparatelaexercicios = Intent(this, telaexercicio4::class.java)
            startActivity(Irparatelaexercicios)
            finish()
        }

        binding.Nivelexercicio5.setOnClickListener {
            val Irparatelaexercicios = Intent(this, telaexercicio5::class.java)
            startActivity(Irparatelaexercicios)
            finish()
        }

        binding.Nivelexercicio6.setOnClickListener {
            val Irparatelaexercicios = Intent(this, listaexercicio6::class.java)
            startActivity(Irparatelaexercicios)
            finish()
        }


        binding.Nivelexercicio7.setOnClickListener {
            val Irparatelaexercicios = Intent(this, telaexercicio7::class.java)
            startActivity(Irparatelaexercicios)
            finish()
        }

        binding.Nivelexercicio8.setOnClickListener {
            val Irparatelaexercicios = Intent(this, telaexercicio8::class.java)
            startActivity(Irparatelaexercicios)
            finish()
        }

        binding.Nivelexercicio9.setOnClickListener {
            val Irparatelaexercicios = Intent(this, telaexercicio9::class.java)
            startActivity(Irparatelaexercicios)
            finish()
        }

        binding.Nivelexercicio10.setOnClickListener {
            val Irparatelaexercicios = Intent(this, telaexercicio10::class.java)
            startActivity(Irparatelaexercicios)
            finish()
        }

        binding.Nivelexercicio11.setOnClickListener {
            val Irparatelaexercicios = Intent(this, telaexercicio11::class.java)
            startActivity(Irparatelaexercicios)
            finish()
        }


        binding.Nivelexercicio12.setOnClickListener {
            val Irparatelaexercicios = Intent(this, telaexercicio12::class.java)
            startActivity(Irparatelaexercicios)
            finish()
        }

        }

    }