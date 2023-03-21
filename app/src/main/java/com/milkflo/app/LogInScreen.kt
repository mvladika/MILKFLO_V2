package com.milkflo.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.milkflo.app.Extensions.toast
import com.milkflo.app.FirebaseUtils.firebaseAuth
import com.milkflo.app.databinding.ActivityLogInScreenBinding




class LogInScreen : AppCompatActivity() {
    private lateinit var binding: ActivityLogInScreenBinding
    private lateinit var logInEmail: String
    private lateinit var logInPassword: String
    private lateinit var logInInputsArray: Array<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val email = binding.editTextTextEmailAddress.text.toString().trim()
        val password = binding.editTextTextPassword.text.toString().trim()

        logInInputsArray = arrayOf(email, password)
        binding.createAccount.setOnClickListener {
            startActivity(Intent(this, Registration::class.java))
            finish()
        }

        binding.signIn.setOnClickListener {
            loginUser()
        }
    }

    private fun notEmpty(): Boolean = logInEmail.isNotEmpty() && logInPassword.isNotEmpty()

    private fun loginUser() {
        logInEmail = binding.editTextTextEmailAddress.text.toString().trim()
        logInPassword = binding.editTextTextPassword.text.toString().trim()

        if (notEmpty()) {
            firebaseAuth.signInWithEmailAndPassword(logInEmail, logInPassword)
                .addOnCompleteListener { signIn ->
                    if (signIn.isSuccessful) {
                        startActivity(Intent(this, HomeActivity::class.java))
                        toast("Signed in successfully")
                        finish()
                    } else {
                        toast("Sign in failed")
                    }
                }
        } else {
            logInInputsArray.forEach { input ->
                if (input.isEmpty()) {
                    toast("Please enter an email or password")
                }
            }
        }
    }
}