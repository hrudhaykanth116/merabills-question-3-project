package com.merabills.question3.utils

import android.graphics.Color
import kotlin.random.Random

class RandomColorGenerator {

    fun getRandomColor(): Int {

        // Using 240 instead of 255 to avoid white color since the background is white and could potentially confuse the user.
        return Color.rgb(
            Random.nextInt(240),
            Random.nextInt(240),
            Random.nextInt(240),
        )
    }

}