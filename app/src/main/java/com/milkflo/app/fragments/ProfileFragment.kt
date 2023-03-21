package com.milkflo.app.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.milkflo.app.*
import com.milkflo.app.my_info
import com.milkflo.app.notifications

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class profileFragment : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val myInfo = view.findViewById<TextView>(R.id.myInfoButton)
        val noti = view.findViewById<TextView>(R.id.notificationButton)
        val comMile = view.findViewById<TextView>(R.id.completedMilestones)
        val signOut = view.findViewById<TextView>(R.id.signOut)

        myInfo.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, my_info::class.java)
            startActivity(intent)
        })

        noti.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, notifications::class.java)
            startActivity(intent)
        })

        comMile.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, milestones_completed::class.java)
            startActivity(intent);

        })

        signOut.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, LogInScreen::class.java)
            FirebaseUtils.firebaseAuth.signOut()
            startActivity(intent)
        })

        return view
    }


}