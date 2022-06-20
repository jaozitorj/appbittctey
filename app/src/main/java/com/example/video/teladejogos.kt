package com.example.video

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.video.databinding.ActivityTeladejogosBinding

private lateinit var mBinding: ActivityTeladejogosBinding

class teladejogos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityTeladejogosBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.jogoflippybird.setOnClickListener {

    }
         }
}