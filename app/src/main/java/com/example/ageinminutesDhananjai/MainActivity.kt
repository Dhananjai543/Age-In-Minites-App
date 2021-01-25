package com.example.ageinminutesDhananjai

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener {view ->
            clickDatePicker(view)
           }
    }

    fun clickDatePicker(view: View){

        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
                view, selectedYear, selectedMonth, selectedDayOfMonth ->
            Toast.makeText(this,"The chosen year is $selectedYear, the month is ${selectedMonth+1} " +
                    "and the day is $selectedDayOfMonth",Toast.LENGTH_SHORT).show()

            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            //for some reason selected month starts from 0 eg Jan=0, feb=1, etc.

            tvSelectedDate.setText(selectedDate)

            //simple date format
            val sdf = SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)

            val selectedDateInMinutes = theDate.time / 60000
            //val selectedDateInDays = theDate.time / (60000*1440)

            //theDate.time returns a millisecond value.
            // Convert it into seconds by dividing by 1000
            // Convert it into minutes by dividing by 60000
            //getTime() returns tne number of milliseconds since january 1, 1970 represented by this date object.

            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            //For calculating age in minutes
            val currentDateInMinutes = currentDate.time / 60000

            //For calculating age in days
            //val currentDateInDays = currentDate.time / (60000*1440)


            val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
            //val differenceInDays = currentDateInDays - selectedDateInDays
            tvSelectedDateInMinutes.setText(differenceInMinutes.toString())

        } ,year ,month, day)
        dpd.datePicker.setMaxDate(Date().time - 86400000)
        //86400000 is 1 day milliseconds
        dpd.show()
    }
}