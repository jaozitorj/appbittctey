package com.example.video

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.DisplayMetrics
import android.widget.TextView
import com.example.video.databinding.ActivitySegundatelaBinding
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text

class segundatela : AppCompatActivity() {


    private lateinit var mBinding: ActivitySegundatelaBinding

    private var mWidth: Int? = null
    private var mHeight: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySegundatelaBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        supportActionBar!!.hide()

        mBinding.botaoexercicios.setOnClickListener {
            val voltarexercicios = Intent(this, exercicios::class.java)
            startActivity(voltarexercicios)
            finish()
        }

        mBinding.deslogar.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val voltarMainActivity = Intent(this, MainActivity::class.java)
            startActivity(voltarMainActivity)
            finish()
        }

        val metrics = DisplayMetrics()
        val result = windowManager

        //verificando o android
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            //pegando o tamanho da tela
            mWidth = result.currentWindowMetrics.bounds.width()
            mHeight = result.currentWindowMetrics.bounds.height()
        } else {
            result.defaultDisplay.getMetrics(metrics)
            mWidth = metrics.widthPixels
            mHeight = metrics.heightPixels

        }
        val imageLogo = mBinding.imageLogo
        val containerCategorias = mBinding.containerCategorias

        containerCategorias.y = (mHeight!! * 0.56).toFloat()

        imageLogo.y = (mHeight!! *0.25).toFloat()

        imageLogo.scaleX = (mHeight!! *0.0006).toFloat()
        imageLogo.scaleY = (mHeight!! *0.0006).toFloat()

        Handler(Looper.getMainLooper()).postDelayed({

            //Esse Bloco sera executado depois de 2 segundos
            containerCategorias.animate().duration = 500 // 500 = meop segundo
            containerCategorias.animate().yBy(-(mHeight!! * 0.56).toFloat())

            imageLogo.animate().duration = 500
            imageLogo.animate().yBy(-(mHeight!! *0.25).toFloat())
            imageLogo.animate().scaleX((mHeight!! *0.0004).toFloat())
            imageLogo.animate().scaleY((mHeight!! *0.0004).toFloat())

        }, 2000)//2000 = 2segundos

    }
}