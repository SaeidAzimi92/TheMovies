package com.saeed.themovie.ui.main.fragments

import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.saeed.themovie.R
import kotlinx.android.synthetic.main.fragment_filter_dialog.*
import java.text.SimpleDateFormat
import java.util.*

class FilterDialogFragment(
    fragmentManager: FragmentManager, var onDateSelected: (String, String) -> Unit
) : DialogFragment() {
    private var startDate: String? = null
    private var endDate: String? = null

    init {
        val transaction = fragmentManager.beginTransaction()
        transaction.addToBackStack(null)
        this.show(transaction, "SomeTag")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_filter_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val startCalendar: Calendar = Calendar.getInstance()
        val endCalendar: Calendar = Calendar.getInstance()

        startDateBtn.setOnClickListener {
            openDatePicker(DateState.START_DATE, startCalendar)
        }
        endDateBtn.setOnClickListener {
            openDatePicker(DateState.END_DATE, endCalendar)
        }
        buttonOk.setOnClickListener {
            if (startDate != null && endDate != null)
                onDateSelected(startDate!!, endDate!!)
            dialog?.dismiss()
        }
    }

    private fun openDatePicker(dateState: DateState, calendar: Calendar) {
        DatePickerDialog(
            context, OnDateSelectedListener(calendar) {
                updateDateLabel(dateState, it)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun updateDateLabel(dateState: DateState, date: String) {
        if (dateState == DateState.START_DATE) {
            tvStartDate.text = date
            startDate = date
        } else if (dateState == DateState.END_DATE) {
            tvEndDate.text = date
            endDate = date
        }
    }

    override fun onStart() {
        super.onStart()
        fixDialogSize()
    }

    private fun fixDialogSize() {
        val layoutParams = WindowManager.LayoutParams()
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        dialog?.window!!.attributes = layoutParams
        dialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }


    inner class OnDateSelectedListener(
        var calendar: Calendar,
        var onDateSelected: (String) -> Unit
    ) :
        DatePickerDialog.OnDateSetListener {

        override fun onDateSet(p0: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            onDateSelected(getDateString())
        }

        private fun getDateString(): String {
            val myFormat = "yyyy-MM-dd"
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            return sdf.format(calendar.time)
        }
    }
}

enum class DateState {
    START_DATE,
    END_DATE;
}