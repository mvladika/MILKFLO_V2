package com.milkflo.app.fragments

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.milkflo.app.FirebaseUtils
import com.milkflo.app.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onStart()
    {
        super.onStart()
        val table = view?.findViewById<View>(R.id.laclogListHome) as TableLayout
        val dbHome = Firebase.firestore
        val noLogID = View.generateViewId()
        val noLogs = "No Current Entries"
        val authUserIDHome = FirebaseUtils.firebaseAuth.currentUser?.uid.toString()

        dbHome.collection("Users")
            .whereEqualTo("Authenticated User ID", authUserIDHome)
            .get()
            .addOnSuccessListener { results ->
                val documentHome = results.firstOrNull()
                val userDocumentReferenceHome = documentHome?.reference
                dbHome.collection("Table Info")
                    .whereEqualTo("User", userDocumentReferenceHome)
                    .orderBy("Year", Query.Direction.DESCENDING)
                    .orderBy("Month", Query.Direction.DESCENDING)
                    .orderBy("Day", Query.Direction.DESCENDING)
                    .orderBy("Hour", Query.Direction.DESCENDING)
                    .orderBy("Minute", Query.Direction.DESCENDING)
                    .limit(1)
                    .get()
                    .addOnSuccessListener { results ->
                        for (document in results) {
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
                            logTimeText.textSize = 28F
                            logTimeText.gravity = Gravity.CENTER
                            logTimeText.setPadding(35, 15, 0, 0)
                            tableRow.addView(logTimeText)


                            val monthInput = document["Month"] as CharSequence?

                            val dayInput = document["Day"] as CharSequence?

                            val yearInput = document["Year"] as CharSequence?


                            val leftInput = TextView(view?.context)
                            leftInput.text = document["Left"] as CharSequence?
                            leftInput.setTextColor(resources.getColor(R.color.milkfloPurple))
                            leftInput.gravity = Gravity.CENTER
                            leftInput.textSize = 28F
                            leftInput.setPadding(60, 15, 0, 0)
                            tableRow.addView(leftInput)

                            val rightInput = TextView(view?.context)
                            rightInput.text = document["Right"] as CharSequence?
                            rightInput.setTextColor(resources.getColor(R.color.milkfloPurple))
                            rightInput.gravity = Gravity.CENTER
                            rightInput.textSize = 28F
                            rightInput.setPadding(60, 15, 0, 0)
                            tableRow.addView(rightInput)

                            val table = view?.findViewById<View>(R.id.laclogListHome) as TableLayout
                            tableRow.setPadding(15, 40, 0, 0)
                            table.addView(tableRow)
                            Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                        }
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
            noLogText.setPadding(0,20, 0, 0)
            table.addView(noLogText)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}