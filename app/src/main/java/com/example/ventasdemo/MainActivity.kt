package com.example.ventasdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.security.AuthProvider

class MainActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authStateListener:FirebaseAuth.AuthStateListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnsing : Button = findViewById(R.id.buttonSingIn)
        val txtemail : TextView = findViewById(R.id.email)
        val txtpass : TextView = findViewById(R.id.password)
        firebaseAuth= Firebase.auth
        btnsing.setOnClickListener() {
                singIn(txtemail.text.toString(),txtpass.text.toString())
        }
    }
    private fun singIn(email:String, password:String){
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    val user= firebaseAuth.currentUser
                    Toast.makeText(baseContext,user?.uid.toString(),Toast.LENGTH_SHORT).show()
                } else{
                    Toast.makeText(baseContext,"Email or password not valid!!",Toast.LENGTH_SHORT).show()

                }

            }
    }


}