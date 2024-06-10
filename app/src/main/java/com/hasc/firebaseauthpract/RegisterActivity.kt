package com.hasc.firebaseauthpract

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.hasc.firebaseauthpract.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity(){
    private lateinit var auth: FirebaseAuth
    private var binding : ActivityRegisterBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        auth = Firebase.auth

        binding?.btnRegister?.setOnClickListener {
            val email: String = binding?.etEmail?.text.toString()
            val password: String = binding?.etPassword?.text.toString()
            val confirmPassword: String = binding?.etConfirmPassword?.text.toString()


            if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()){
                if (password == confirmPassword) {
                    createUserWithEmailAndPassword(email, password)
                } else {
                    Toast.makeText(this, "Senhas incompativeis", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, preencha os campos.", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun createUserWithEmailAndPassword(email : String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d(TAG, "createUserWithEmailAndPassword:Success")
                Toast.makeText(this, "Conta criada com sucesso", Toast.LENGTH_SHORT).show()
               //  val user = auth.currentUser
            } else {
                Log.w(TAG, "createUserWithEmailAndPassword:Failure", task.exception)
                Toast.makeText(baseContext, "Authentication Failure", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private var TAG = "emailAndPassword"
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
