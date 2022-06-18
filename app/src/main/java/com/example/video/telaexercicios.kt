package com.example.video


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.video.databinding.ActivitySegundatelaBinding
import com.example.video.databinding.ActivityTelaexerciciosBinding

private lateinit var mBinding: ActivityTelaexerciciosBinding

class telaexercicios : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityTelaexerciciosBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.exercicio1.setOnClickListener {
            val voltarexercicios = Intent(this, QuizActivity::class.java)
            startActivity(voltarexercicios)
            finish()
        }
    }
}