package com.example.video

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.video.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        binding.btregistrar.setOnClickListener {
            val intent = Intent(this, telaregistro::class.java)
            startActivity(intent)
            finish()
        }

        binding.btIrparatelaentrar.setOnClickListener { view ->
            val email = binding.editemail.text.toString()
            val senha = binding.editsenha.text.toString()

            if (email.isEmpty() || senha.isEmpty()){
                val snackbar = Snackbar.make(view, "Preencha todos os campos!",Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.show()
            }else{

                auth.signInWithEmailAndPassword(email,senha).addOnCompleteListener { autenticacao ->
                    if (autenticacao.isSuccessful){
                        navegartelaprincipal()

                }
            }.addOnFailureListener {
                    val snackbar = Snackbar.make(view, "Erro ao fazer login do usuario!",Snackbar.LENGTH_SHORT)
                    snackbar.setBackgroundTint(Color.RED)
                    snackbar.show()
                }
        }
        }
        }
        private fun navegartelaprincipal(){
            val intent = Intent(this, segundatela::class.java)
            startActivity(intent)

        }

    override fun onStart() {
        super.onStart()

        val usuarioAtual = FirebaseAuth.getInstance().currentUser

        if (usuarioAtual != null){
            navegartelaprincipal()
        }
    }
    }