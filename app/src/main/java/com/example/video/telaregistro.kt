package com.example.video

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.video.databinding.ActivityTelaregistroBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class telaregistro : AppCompatActivity() {

    private lateinit var binding: ActivityTelaregistroBinding
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaregistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        binding.botaovoltar.setOnClickListener {
            val voltarMainActivity = Intent(this, MainActivity::class.java)
            startActivity(voltarMainActivity)
            finish()
        }

        binding.botaoregistrarse.setOnClickListener {view ->
            val confirmasenha = binding.editconfirmarsenha.text.toString()
            val email = binding.editdigiteemail.text.toString()
            val telefone = binding.telefone.text.toString()
            val senha1 = binding.editsenha1.text.toString()
            val nomecompleto = binding.editnomecompleto.text.toString()

            if (email.isEmpty() || senha1.isEmpty() || confirmasenha.isEmpty() || telefone.isEmpty() || nomecompleto.isEmpty())
            {
                val snackbar = Snackbar.make(view, "Preencha todos os campos!", Snackbar.LENGTH_LONG)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.show()
            }else{
                auth.createUserWithEmailAndPassword(email,confirmasenha).addOnCompleteListener { cadastro ->

                    if (cadastro.isSuccessful){
                        val snackbar = Snackbar.make(view, "Sucesso ao cadastrar", Snackbar.LENGTH_LONG)
                        snackbar.setBackgroundTint(Color.BLUE)
                        snackbar.show()
                        binding.editdigiteemail.setText("")
                        binding.editsenha1.setText("")
                        binding.editconfirmarsenha.setText("")
                        binding.telefone.setText("")
                        binding.editnomecompleto.setText("")


                    }
                }.addOnFailureListener { exception ->

                    val mensagemErro = when(exception){
                        is FirebaseAuthWeakPasswordException -> "Digite uma senha com no minimo 6 caracteres!"
                        is FirebaseAuthInvalidCredentialsException -> "Digite um email valido!"
                        is FirebaseAuthUserCollisionException -> "Esta conta ja foi cadastrada!"
                        is FirebaseNetworkException -> "Sem conexão com a internet!"
                        else -> "Erro ao cadastrar usuario!"
                    }
                    val snackbar = Snackbar.make(view,mensagemErro, Snackbar.LENGTH_LONG)
                    snackbar.setBackgroundTint(Color.RED)
                    snackbar.show()

                }

            }
        }
    }
    }
