package com.milkflo.app


import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.milkflo.app.Extensions.toast
import com.milkflo.app.FirebaseUtils.firebaseAuth
import com.milkflo.app.FirebaseUtils.firebaseUser
import com.milkflo.app.databinding.ActivityRegistrationBinding
import com.milkflo.app.fragments.HomeFragment
import com.milkflo.app.fragments.LacListFragment


class Registration : AppCompatActivity() {

    private lateinit var binding:  ActivityRegistrationBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.confirmCA.setOnClickListener {
            createAccount()
            val intent = Intent(this,  HomeActivity::class.java)
            startActivity(intent)
            toast("Account Created")
            finish()
        }

        binding.loginCA.setOnClickListener{
            val intent = Intent(this,  LogInScreen::class.java)
            startActivity(intent)
            toast("Sign into your account")
            finish()
        }

    }

    override fun onStart() {
        super.onStart()
        val user: FirebaseUser? = firebaseAuth.currentUser
        user?.let {
            startActivity(Intent(this, HomeActivity::class.java))
            toast("Welcome back")
        }
    }

    private fun createAccount(){
        //create a user
        val firstName = binding.firstNameText.text.toString()
        val lastName = binding.lastNameText.text.toString()
        val age = binding.ageText.text.toString()
        val email = binding.emailText.text.toString()
        val password = binding.passwordText.text.toString()

        val db = Firebase.firestore

        val userInfo = hashMapOf(
            "First Name" to firstName,
            "Last Name" to lastName,
            "Age" to age,
            "Email" to email
        )

        if(checkAll(userInfo))
        {
            //emailVerification()

         firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                task -> if(task.isSuccessful) {
             Log.d(ContentValues.TAG, "Authentication Successful! Attempting to Create User")

                    userInfo["Authenticated User ID"] = firebaseAuth.currentUser?.uid.toString()

                db.collection("Users")
                    .add(userInfo)
                    .addOnSuccessListener { documentReference ->
                        Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                    }
                    .addOnFailureListener { e ->
                        Log.w(ContentValues.TAG, "Error adding document", e)
                    }
                Log.d(ContentValues.TAG, "User Created by Default")
            }
             else
         {
             Log.d(ContentValues.TAG, "Authentication FAILED")
         }
        }

        }
    }

    //Send verification email to the new user.
    private fun emailVerification() {
        val email = binding.emailText.text.toString()

        firebaseUser?.let {
            it.sendEmailVerification().addOnCompleteListener {
                    task -> if(task.isSuccessful)
            {
                toast("Verification email sent to $email")
            }
            }
        }
    }

    private fun checkAll(map: HashMap<String, String>):Boolean
    {
        if (map["First Name"] == null)
        {
            return false
        }

        if(!checkInput(map["Last Name"] as String))
        {
            return false
        }

        if (!checkInput(map["Age"] as String))
        {
            return false
        }

        if(!checkInput(map["Email"] as String))
        {
            return false
        }

        return true
    }

    private fun checkInput(s: String): Boolean {
        if(s == null || s.isEmpty() || s.isBlank())
        {
            Toast.makeText(this, "One or More Empty Fields", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

}

