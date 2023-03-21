package com.milkflo.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class my_info : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_info)

        val authUserID = FirebaseUtils.firebaseAuth.currentUser?.uid.toString()
        val db = Firebase.firestore
        val firstNameTV = findViewById<TextView>(R.id.firstNameTextView)
        val lastNameTV = findViewById<TextView>(R.id.lastNameTextView)
        val ageTV = findViewById<TextView>(R.id.ageTextView)
        val emailTV = findViewById<TextView>(R.id.emailTextView)

        db.collection("Users")
            .whereEqualTo("Authenticated User ID", authUserID)
            .get()
            .addOnSuccessListener { results ->
                for(document in results)
                {
                    val firstName = document["First Name"]
                    val lastName = document["Last Name"]
                    val age = document["Age"]
                    val email = document["Email"]
                    firstNameTV.text = "First Name:\n $firstName"
                    lastNameTV.text = "Last Name:\n $lastName"
                    ageTV.text = "Age:\n $age"
                    emailTV.text = "Email:\n $email"
                }

            }
    }
}