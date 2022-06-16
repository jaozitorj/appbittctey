package com.example.video

import android.app.Instrumentation
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.video.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.BatchResultToken
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    private lateinit var googleSignInClient: GoogleSignInClient

    private lateinit var binding: ActivityMainBinding
    private var auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        auth = Firebase.auth

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("265859998214-ep1mtt9kdleqkihvr4boupmoktuaie9g.apps.googleusercontent.com")
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)


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
        binding.signInButton.setOnClickListener { View ->
            signIn()
        }
        }
        private fun signIn(){
            val intent = googleSignInClient.signInIntent
            abreActivity.launch(intent)
        }
    var abreActivity = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        result: ActivityResult ->

        if(result.resultCode== RESULT_OK){
            val intent = result.data
            val task = GoogleSignIn.getSignedInAccountFromIntent(intent)
            try {
                    val conta = task.getResult(ApiException::class.java)
                loginComGoogle(conta.idToken!!)
            }catch (exception: ApiException){

            }
        }
    }

        private fun loginComGoogle(token: String){
            val credential = GoogleAuthProvider.getCredential(token, null)
            auth.signInWithCredential(credential).addOnCompleteListener(this)
            {
                task: Task<AuthResult> ->
                if(task.isSuccessful){
                    navegartelaprincipal()

                }else {
                    Toast.makeText(
                        baseContext, "Erro autenticaçao",
                        Toast.LENGTH_SHORT

                    ).show()
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