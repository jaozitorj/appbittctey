package com.example.video

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.video.databinding.ActivityCardsUnidade1Binding
import com.example.video.databinding.ActivityListaunidadeCardsBinding
import com.example.video.databinding.ActivitySegundatelaBinding


private lateinit var mBinding: ActivityListaunidadeCardsBinding
class listaunidade_cards : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityListaunidadeCardsBinding.inflate(layoutInflater)
        setContentView(mBinding.root)


        mBinding.cardsunidade1.setOnClickListener {
            val irparacards1 = Intent(this, cards_unidade1::class.java)
            startActivity(irparacards1)
            finish()
        }
    }
}