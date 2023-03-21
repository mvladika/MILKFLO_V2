package com.milkflo.app.fragments

import android.R.attr.*
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.milkflo.app.CalendarPopUp
import com.milkflo.app.FirebaseUtils.firebaseAuth
import com.milkflo.app.LactationListAddPopUpScreen
import com.milkflo.app.LactationListEditPopUpScreen
import com.milkflo.app.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import android.widget.TextView
import androidx.core.view.isEmpty
import androidx.core.view.isInvisible
import androidx.core.view.marginTop
import org.w3c.dom.Text
import java.time.LocalDate
import java.util.*



// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [LacListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LacListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    //val myTextView = view?.findViewById<TextView>(R.id.testing


    @SuppressLint("SimpleDateFormat", "NewApi")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        /*val dateTimeDisplay = view?.findViewById<TextView>(R.id.dateText)
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("MM/dd/yy")
        val date = dateFormat.format(calendar.time)
        dateTimeDisplay?.text = date.toString()*/
        /*val dateTimeDisplay = view?.findViewById<TextView>(R.id.dateText)
        val now = LocalDateTime.now()
        val newFormat = DateTimeFormatter.ofPattern("MM/dd/yy")
        val date = now.format(newFormat)*/


    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val dateTime: String
        var dateTimePrevious: String
        val view = inflater.inflate(R.layout.fragment_lac_list, container, false)
        val context = view.context
        val table = view?.findViewById<View>(R.id.laclogList) as TableLayout
        val dateText: TextView = view.findViewById(R.id.dateText)
        val calendar: Calendar = Calendar.getInstance()
        val simpleDateFormat: SimpleDateFormat = SimpleDateFormat("MM/dd/yy", Locale.US)
        dateTime = simpleDateFormat.format(calendar.time).toString()
        dateText.text = dateTime

        val backBtn = view.findViewById<Button>(R.id.backButton)
        backBtn.setOnClickListener(View.OnClickListener {
            val dateTextString: String = dateText.text.toString()
            val dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yy", Locale.US)
            val date = LocalDate.parse(dateTextString, dateFormatter)
            val calendarPrevious: Calendar = Calendar.getInstance()
            val day = date.dayOfMonth
            val month = date.monthValue - 1
            val year = date.year
            calendarPrevious.set(Calendar.DAY_OF_MONTH, day)
            calendarPrevious.set(Calendar.MONTH, month)
            calendarPrevious.set(Calendar.YEAR, year)
            calendarPrevious.add(Calendar.DATE, -1)
            dateTimePrevious = simpleDateFormat.format(calendarPrevious.time).toString()
            dateText.text = dateTimePrevious

            table.removeAllViews()
            displayLogs(dateTimePrevious)
        })

        val addLogButton = view.findViewById<Button>(R.id.addButton)
        addLogButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, LactationListAddPopUpScreen::class.java)
            startActivity(intent)
        })

        val editLogButton = view.findViewById<Button>(R.id.editButton)
        editLogButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, LactationListEditPopUpScreen::class.java)
            startActivity(intent)
        })

        val calendarBtn = view.findViewById<ImageButton>(R.id.calendarButton)
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        calendarBtn.setOnClickListener(View.OnClickListener {
            val dpf = DatePickerDialog(context, DatePickerDialog.OnDateSetListener { datePicker, mYear, mMonth, mDay ->

                var monthText = ""
                var dayText = ""

                if((mMonth + 1) < 10)
                {
                    monthText = "0" + (mMonth + 1).toString()
                }
                else
                {
                    monthText = (mMonth + 1).toString()
                }

                if(mDay < 10)
                {
                    dayText = "0" + mDay.toString()
                }
                else
                {
                    dayText = mDay.toString()
                }

                val yearText = (mYear - 2000).toString()
                val selectedDate = "$monthText/$dayText/$yearText"
                dateText.text = selectedDate
                table.removeAllViews()
                displayLogs(selectedDate)

            }, year, month, day)
            dpf.show()
        })

        return view
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun displayLogs(dateTime: String) {

        val db = Firebase.firestore
        val table = view?.findViewById<View>(R.id.laclogList) as TableLayout
        val authUserID = firebaseAuth.currentUser?.uid.toString()


        db.collection("Users")
            .whereEqualTo("Authenticated User ID", authUserID)
            .get()
            .addOnSuccessListener { results ->

                val document = results.first()
                val userDocumentReference = document.reference

                db.collection("Table Info")
                    .whereEqualTo("User", userDocumentReference)
                    .get()
                    .addOnSuccessListener { results ->
                        for (document in results) {

                            val monthInput = document["Month"] as CharSequence?
                            val dayInput = document["Day"] as CharSequence?
                            val yearInput = document["Year"] as CharSequence?
                            val logDate = "$monthInput/$dayInput/$yearInput"

                            if(logDate == dateTime)
                            {
                                val tableRow = TableRow(activity)
                                val hourInput = document["Hour"] as CharSequence
                                val minuteInput = document["Minute"] as CharSequence
                                hourInput.toString()
                                minuteInput.toString()
                                val logTime = "$hourInput:$minuteInput"

                                val logTimeText = TextView(view?.context)
                                logTimeText.text = logTime
                                logTimeText.setTextColor(resources.getColor(R.color.milkfloPurple))
                                logTimeText.textSize = 20F
                                logTimeText.gravity = Gravity.CENTER
                                logTimeText.setPadding(120, 10, 0, 0)
                                tableRow.addView(logTimeText)

                                val leftInput = TextView(view?.context)
                                leftInput.text = document["Left"] as CharSequence?
                                leftInput.setTextColor(resources.getColor(R.color.milkfloPurple))
                                leftInput.gravity = Gravity.CENTER
                                leftInput.textSize = 20F
                                leftInput.setPadding(160, 10, 0, 0)
                                tableRow.addView(leftInput)

                                val rightInput = TextView(view?.context)
                                rightInput.text = document["Right"] as CharSequence?
                                rightInput.setTextColor(resources.getColor(R.color.milkfloPurple))
                                rightInput.gravity = Gravity.CENTER
                                rightInput.textSize = 20F
                                rightInput.setPadding(140, 10, 0, 0)
                                tableRow.addView(rightInput)


                                tableRow.setPadding(25, 10, 0, 0)
                                table.addView(tableRow)
                                Log.d(TAG, "${document.id} => ${document.data} added by LacListFrag")
                            }
                        }

                    }
                    .addOnFailureListener { exception ->
                        Log.w(TAG, "Error getting documents.", exception)
                    }
            }

    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onStart() {
        super.onStart()

        val noLogID = View.generateViewId()
        val noLogs = "No Current Entries"
        val db = Firebase.firestore
        val table = view?.findViewById<View>(R.id.laclogList) as TableLayout
        val authUserID = firebaseAuth.currentUser?.uid.toString()

        val calendar: Calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("MM/dd/yy", Locale.US)
        var todaysDate = simpleDateFormat.format(calendar.time).toString()

        table.removeAllViews()

            db.collection("Users")
                .whereEqualTo("Authenticated User ID", authUserID)
                .get()
                .addOnSuccessListener { results ->

                    val document = results.first()
                    val userDocumentReference = document.reference

                    db.collection("Table Info")
                        .whereEqualTo("User", userDocumentReference)
                        .get()
                        .addOnSuccessListener { results ->
                            for (document in results) {

                                val monthInput = document["Month"] as CharSequence?
                                val dayInput = document["Day"] as CharSequence?
                                val yearInput = document["Year"] as CharSequence?
                                val logDate = "$monthInput/$dayInput/$yearInput"

                                if(logDate == todaysDate)
                                {
                                    val noLogsTV = view?.findViewById<TextView>(noLogID)
                                    table.removeView(noLogsTV)

                                    val tableRow = TableRow(activity)
                                    val hourInput = document["Hour"] as CharSequence
                                    val minuteInput = document["Minute"] as CharSequence
                                    hourInput.toString()
                                    minuteInput.toString()
                                    val logTime = "$hourInput:$minuteInput"

                                    val logTimeText = TextView(view?.context)
                                    logTimeText.text = logTime
                                    logTimeText.setTextColor(resources.getColor(R.color.milkfloPurple))
                                    logTimeText.textSize = 20F
                                    logTimeText.gravity = Gravity.CENTER
                                    logTimeText.setPadding(120, 10, 0, 0)
                                    tableRow.addView(logTimeText)

                                    val leftInput = TextView(view?.context)
                                    leftInput.text = document["Left"] as CharSequence?
                                    leftInput.setTextColor(resources.getColor(R.color.milkfloPurple))
                                    leftInput.gravity = Gravity.CENTER
                                    leftInput.textSize = 20F
                                    leftInput.setPadding(160, 10, 0, 0)
                                    tableRow.addView(leftInput)

                                    val rightInput = TextView(view?.context)
                                    rightInput.text = document["Right"] as CharSequence?
                                    rightInput.setTextColor(resources.getColor(R.color.milkfloPurple))
                                    rightInput.gravity = Gravity.CENTER
                                    rightInput.textSize = 20F
                                    rightInput.setPadding(140, 10, 0, 0)
                                    tableRow.addView(rightInput)

                                    tableRow.setPadding(25, 10, 0, 0)
                                    table.addView(tableRow)
                                    Log.d(TAG, "${document.id} => ${document.data} added by LacListFrag")
                                }

                            }

                        }
                        .addOnFailureListener { exception ->
                            Log.w(TAG, "Error getting documents.", exception)
                        }

        }


        if(false)
        {
            val noLogText = TextView(view?.context)
            noLogText.id = noLogID
            noLogText.text = noLogs
            noLogText.textSize = 36F
            noLogText.setTextColor(resources.getColor(R.color.milkfloPurple))
            noLogText.gravity = Gravity.CENTER
            noLogText.setPadding(0,400, 0, 0)
            table.addView(noLogText)
        }
    }

/*
                val noLogText = TextView(view?.context)
                noLogText.text = noLogs
                noLogText.textSize = 48F
                noLogText.setTextColor(resources.getColor(R.color.milkfloPurple))
                noLogText.gravity = Gravity.CENTER
                table.addView(noLogText)
 */




    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LacListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LacListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}