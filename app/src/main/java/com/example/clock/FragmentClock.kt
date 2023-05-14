package com.example.clock

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.clock.databinding.FragmentClockBinding
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class FragmentClock : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_clock, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentClockBinding.bind(view)

        val handler = Handler()

        val runnableTime: java.lang.Runnable = object : java.lang.Runnable {
            override fun run() {
                val calendar = Calendar.getInstance()
                val date = Date()
                val tz = TimeZone.getDefault()
                val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
                val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                val currentTime = timeFormat.format(calendar.time)
                val currentDate = dateFormat.format(date)
                binding.tvCurrentTime.text = currentTime
                binding.tvCurrentDate.text = currentDate
                binding.tvCurrentTimeZone.text = tz.getDisplayName(Locale.getDefault())
                handler.postDelayed(this, 1000)
            }
        }

        handler.post(runnableTime)
    }

    companion object {}
}