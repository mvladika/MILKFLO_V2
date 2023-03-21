package com.milkflo.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.R
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Build
import android.util.AndroidRuntimeException
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.milkflo.app.Extensions.toast
import com.milkflo.app.fragments.HomeFragment
import com.milkflo.app.fragments.LacListFragment
import java.util.*
import kotlin.collections.HashMap


class LactationListAddPopUpScreen : AppCompatActivity() {
    var yearCompare = 0;
    var monthCompare = 0;
    var dayCompare = 0;
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.milkflo.app.R.layout.activity_lactation_list_add_popup_screen)

        val hourEditText = findViewById<EditText>(com.milkflo.app.R.id.hourEdit)
        val minEditText = findViewById<EditText>(com.milkflo.app.R.id.minuteEdit)
        val monthEditText = findViewById<EditText>(com.milkflo.app.R.id.monthEdit)
        val dayEditText = findViewById<EditText>(com.milkflo.app.R.id.dayEdit)
        val yearEditText = findViewById<EditText>(com.milkflo.app.R.id.yearEdit)
        val leftAmount = findViewById<EditText>(com.milkflo.app.R.id.leftNum)
        val rightAmount = findViewById<EditText>(com.milkflo.app.R.id.rightNum)

        val addButtonNewEntry = findViewById<Button>(com.milkflo.app.R.id.addButtonNewEntry)
        val cancelNewEntryButton = findViewById<Button>(com.milkflo.app.R.id.cancelNewEntry)

        val leftMeasure = findViewById<Spinner>(com.milkflo.app.R.id.leftMeasurement)
        val rightMeasure = findViewById<Spinner>(com.milkflo.app.R.id.rightMeasurement)
        val measurements = resources.getStringArray(com.milkflo.app.R.array.measurements)
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, measurements)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        leftMeasure.adapter = adapter
        rightMeasure.adapter = adapter

        val calendar: Calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("MM/dd/yy", Locale.US)
        var todaysDate = simpleDateFormat.format(calendar.time).toString()
        val dateArray : List<String> = todaysDate.split("/")
        yearCompare = dateArray[2].toInt()
        monthCompare = dateArray[0].toInt()
        dayCompare = dateArray[1].toInt()
        monthEditText.setText(dateArray[0])
        dayEditText.setText(dateArray[1])
        yearEditText.setText(dateArray[2])

        addButtonNewEntry.setOnClickListener(View.OnClickListener {

            val db = Firebase.firestore
            val hour = hourEditText.text.toString()
            val minute = minEditText.text.toString()
            val month = monthEditText.text.toString()
            val day = dayEditText.text.toString()
            val year = yearEditText.text.toString()
            val left = leftAmount.text.toString() + " " + leftMeasure.selectedItem.toString()
            val right = rightAmount.text.toString() + " " + rightMeasure.selectedItem.toString()

            val authenticatedUserID = FirebaseUtils.firebaseAuth.currentUser?.uid.toString()

            db.collection("Users")
                .whereEqualTo("Authenticated User ID", authenticatedUserID)
                .get()
                .addOnSuccessListener { results ->
                    val document = results.first()
                    val userDocumentReference = document.reference

                    val tableInfo = hashMapOf(
                        "User" to userDocumentReference,
                        "Hour" to hour,
                        "Minute" to minute,
                        "Month" to month,
                        "Day" to day,
                        "Year" to year,
                        "Left" to left,
                        "Right" to right,
                    )

                    if (checkAll(tableInfo)) {
                        db.collection("Table Info")
                            .add(tableInfo)
                            .addOnSuccessListener { documentReference ->
                                Log.d(TAG,"DocumentSnapshot added with ID: ${documentReference.id}")
                            }
                            .addOnFailureListener { e ->
                                Log.w(TAG, "Error adding document", e)
                            }
                        finish()
                    }
                }
            })

        cancelNewEntryButton.setOnClickListener(View.OnClickListener {
            finish()
        })
    }

    private fun checkAll(map: HashMap <String, Any>):Boolean
    {
        if (map["User"] == null)
        {
            return false
        }

        val hour = map["Hour"] as String
        if(!checkInput(hour) || hour.length > 2)
        {
            toast("Two Digit Max for Time Input")
            return false
        }

        val minute = map["Minute"] as String
        if (!checkInput(minute) || minute.length > 2)
        {
            toast("Two Digit Max for Time Input")
            return false
        }

        val month = map["Month"] as String
        if(!checkInput(month) || month.length != 2)
        {
            toast("Please Enter 2 Digits for Month")
            return false
        }

        if(month.toInt() > monthCompare)
        {
            toast("Month must be less than or equal to current month")
            return false
        }

        val day = map["Day"] as String
        if (!checkInput(day) || day.length != 2)
        {
            toast("Please Enter 2 Digits for Day")
            return false
        }

        if(day.toInt() > dayCompare)
        {
            toast("Day must be less than or equal to current day")
            return false
        }

        val year = map["Year"] as String
        if(!checkInput(year) || year.length != 2)
        {
            toast("Please Enter 2 Digits for Year")
            return false
        }

        if(year.toInt() > yearCompare)
        {
            toast("Year must be less than or equal to current year")
            return false
        }

        val left = map["Left"] as String
        if (!checkInput(left) || left.length <= 4)
        {
            toast("Measurement Input Must Be Less Than 4 Digits")
            return false
        }

        val right = map["Right"] as String
        if(!checkInput(right) || right.length <= 4)
        {
            toast("Measurement Input Must Be Less Than 4 Digits")
            return false
        }

        return true
    }

    private fun checkInput(s: String): Boolean {
        if(s == null || s.isEmpty() || s.isBlank())
        {
            return false
        }

        return true
    }

}
