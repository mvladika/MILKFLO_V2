package com.milkflo.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.TextView

class CalendarPopUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_pop_up)

        val calendarView = findViewById<CalendarView>(R.id.calendarView)
        val dateText = findViewById<TextView>(R.id.selectDate)

        calendarView.setOnDateChangeListener { calendarView, year, month, day ->
            val date = ((month + 1).toString()) + "/" + day + "/" + year
            dateText.text = date
        }
    }
}