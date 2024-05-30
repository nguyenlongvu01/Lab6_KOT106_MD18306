package com.vunlph30245.lab6_ph30245.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.vunlph30245.lab6_ph30245.ui.theme.ui.screens.CinemaSeatBookingScreen
import com.vunlph30245.lab6_ph30245.ui.theme.ui.screens.createTheaterSeating


class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CinemaSeatBookingScreen(
                createTheaterSeating(
                    totalRows = 12,
                    totalSeatsPerRow = 9,
                    aislePositionInRow = 4,
                    aislePositionInColumn = 5
                ), totalSeatsPerRow = 9
            )
        }
    }
}