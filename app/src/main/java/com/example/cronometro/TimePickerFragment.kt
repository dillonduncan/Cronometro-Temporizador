package com.example.cronometro

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.Calendar

class TimePickerFragment(val listener:(String) -> Unit): DialogFragment(), TimePickerDialog.OnTimeSetListener {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar= Calendar.getInstance()
        val hora=calendar.get(Calendar.HOUR_OF_DAY)
        val minutos=calendar.get(Calendar.MINUTE)
        val dialog =TimePickerDialog(activity as Context,this,hora,minutos,true)
        return dialog
    }

    override fun onTimeSet(view: TimePicker?, horas: Int, minutos: Int) {
        listener("$horas:$minutos")
    }
}