package com.example.pocketpilot

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {

    private lateinit var cardContainer: LinearLayout
    private lateinit var totalDistanceCovered: TextView
    private lateinit var totalDistanceRemaining: TextView
    private lateinit var unitToggleButton: ToggleButton
    private lateinit var nextStopButton: Button
    private var isKm = true
    private val stops = mutableListOf<Stop>()
    private var currentStopIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cardContainer = findViewById(R.id.cardContainer)
        totalDistanceCovered = findViewById(R.id.totalDistanceCovered)
        totalDistanceRemaining = findViewById(R.id.totalDistanceRemaining)
        unitToggleButton = findViewById(R.id.unitToggleButton)
        nextStopButton = findViewById(R.id.nextStopButton)

        // Example data for stops
        stops.addAll(
            listOf(
                Stop("Stop 1", false, "Visa not required", 100.0, 2.0),
                Stop("Stop 2", false, "Visa required", 200.0, 4.0),
                Stop("Stop 3", false, "Visa not required", 300.0, 6.0)
            )
        )

        // Add cards dynamically
        for (stop in stops) {
            addCard(stop)
        }

        // Update progress bar and distances
        updateProgressBar()
        updateDistances()

        // Toggle button listener
        unitToggleButton.setOnCheckedChangeListener { _, isChecked ->
            isKm = isChecked
            updateDistances()
            updateCards()
        }

        // Next Stop button listener
        nextStopButton.setOnClickListener {
            if (currentStopIndex < stops.size) {
                stops[currentStopIndex].reached = true
                updateProgressBar()
                updateDistances()
                updateCards()
                currentStopIndex++
            }
        }
    }

    private fun addCard(stop: Stop) {
        val cardView = LayoutInflater.from(this).inflate(R.layout.card_stop, cardContainer, false) as CardView
        val stopNameView: TextView = cardView.findViewById(R.id.stopName)
        val reachedView: TextView = cardView.findViewById(R.id.reached)
        val visaRequirementsView: TextView = cardView.findViewById(R.id.visaRequirements)
        val remainingDistanceView: TextView = cardView.findViewById(R.id.remainingDistance)
        val remainingTimeView: TextView = cardView.findViewById(R.id.remainingTime)

        stopNameView.text = stop.name
        reachedView.text = "Reached: ${if (stop.reached) "Yes" else "No"}"
        visaRequirementsView.text = "Visa Requirements: ${stop.visaRequirements}"
        remainingDistanceView.text = "Remaining Distance: ${formatDistance(stop.remainingDistance)}"
        remainingTimeView.text = "Remaining Time: ${stop.remainingTime} hours"

        if (stop.reached) {
            cardView.setCardBackgroundColor(Color.GREEN)
        } else {
            cardView.setCardBackgroundColor(Color.RED)
        }

        cardContainer.addView(cardView)
    }

    private fun updateProgressBar() {
        val progressBar = findViewById<android.widget.ProgressBar>(R.id.progressBar)
        val totalStops = stops.size
        val reachedStops = stops.count { it.reached }
        val progress = (reachedStops.toFloat() / totalStops) * 100
        progressBar.progress = progress.toInt()
    }

    private fun updateDistances() {
        val totalCovered = stops.filter { it.reached }.sumOf { it.remainingDistance }
        val totalRemaining = stops.filter { !it.reached }.sumOf { it.remainingDistance }

        totalDistanceCovered.text = "Total Distance Covered: ${formatDistance(totalCovered)}"
        totalDistanceRemaining.text = "Total Distance Remaining: ${formatDistance(totalRemaining)}"
    }

    private fun updateCards() {
        for (i in 0 until cardContainer.childCount) {
            val cardView = cardContainer.getChildAt(i) as CardView
            val remainingDistanceView: TextView = cardView.findViewById(R.id.remainingDistance)
            val stop = stops[i]
            remainingDistanceView.text = "Remaining Distance: ${formatDistance(stop.remainingDistance)}"
        }
    }

    private fun formatDistance(distance: Double): String {
        return if (isKm) {
            "${distance} km"
        } else {
            "${distance * 0.62137} miles"
        }
    }
}

// Data class for Stop
data class Stop(
    val name: String,
    var reached: Boolean,
    val visaRequirements: String,
    val remainingDistance: Double,
    val remainingTime: Double
)