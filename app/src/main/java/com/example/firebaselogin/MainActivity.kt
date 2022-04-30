package com.example.firebaselogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    lateinit var autenticacion: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        autenticacion = FirebaseAuth.getInstance()

    }

    override fun onStart() {
        super.onStart()
        val user: FirebaseUser? = autenticacion.currentUser

        if (user==null){
            //mandar al Login
        }else{
            //avanzar a la pantalla principal
        }
    }

    fun login(){
        val correo: String = findViewById<EditText>(R.id.etUser).text.toString()
        val password: String = findViewById<EditText>(R.id.etPswd).text.toString()

        autenticacion.signInWithEmailAndPassword(correo,password).addOnCompleteListener {
            if (it.isSuccessful){
                //avanzar a la pantalla principal
                Toast.makeText(this, "Exito", Toast.LENGTH_SHORT).show()
            }else{
                //Mostrar un resultado error
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun loginVerify(view: View) {
        login()
    }
}