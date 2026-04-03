package com.merabills.question3

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.metrics.performance.JankStats
import java.util.concurrent.atomic.AtomicInteger

class MainActivity : AppCompatActivity() {

    //region Jank stats tracking - do not modify
    private lateinit var jankStatsTracker: JankStats
    private val totalJankyFrames = AtomicInteger(0)
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Your code goes here

        //region Jank stats tracking - do not modify
        val totalJankyFramesLiveData = MutableLiveData(0)
        findViewById<Button>(R.id.resetButton).setOnClickListener {
            totalJankyFrames.set(0)
            totalJankyFramesLiveData.value = totalJankyFrames.get()
        }

        val jankStatsListener = JankStats.OnFrameListener { frameData ->
            if (frameData.isJank)
                totalJankyFramesLiveData.postValue(totalJankyFrames.incrementAndGet())
        }
        jankStatsTracker = JankStats.createAndTrack(window, jankStatsListener)

        val jankStatsTextView = findViewById<TextView>(R.id.jankStatsTextView)
        totalJankyFramesLiveData.observe(this) { jankyFrames ->
            jankStatsTextView.text = getString(R.string.jank_stats, jankyFrames)
        }
        //endregion
    }

    override fun onResume() {
        super.onResume()

        //region Jank stats tracking - do not modify
        if (::jankStatsTracker.isInitialized) {
            jankStatsTracker.isTrackingEnabled = true
        }
        //endregion
    }

    override fun onPause() {
        super.onPause()

        //region Jank stats tracking - do not modify
        if (::jankStatsTracker.isInitialized) {
            jankStatsTracker.isTrackingEnabled = false
        }
        //endregion
    }
}
