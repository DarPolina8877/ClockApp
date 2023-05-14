package com.example.clock

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.clock.databinding.FragmentClockBinding
import com.example.clock.databinding.FragmentTimerBinding
import java.lang.Package.getPackage
import java.text.SimpleDateFormat
import java.time.Period
import java.time.ZoneId
import java.util.*

class FragmentTimer : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_timer, container, false)
    }

    private var doPlayNotStop: Boolean = true
    private var isGoing: Boolean = false
    private lateinit var timestamp: Date

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentTimerBinding.bind(view)

        binding.fabPlayStop.setOnClickListener {
            if (doPlayNotStop) {
                timestamp = Date()
                isGoing = true
                binding.fabPlayStop.setImageResource(
                    resources.getIdentifier(
                        "ic_baseline_stop_24",
                        "drawable",
                        "com.example.clock"
                    )
                )
            } else {
                isGoing = false
                binding.fabPlayStop.setImageResource(
                    resources.getIdentifier(
                        "ic_baseline_play_arrow_24",
                        "drawable",
                        "com.example.clock"
                    )
                )
            }
            doPlayNotStop = !doPlayNotStop
        }

        val handler = Handler()

        val runnableTime: java.lang.Runnable = object : java.lang.Runnable {
            override fun run() {
                if (isGoing) {
                    val calendar = Calendar.getInstance()
                    val timeDiff = Date().time - timestamp.time
                    val timeFormat = SimpleDateFormat("mm:ss.SS", Locale.getDefault())
                    binding.tvRecordedTime.text = timeFormat.format(timeDiff)
                }
                handler.postDelayed(this, 24)
            }
        }

        handler.post(runnableTime)
    }

    companion object {}
}