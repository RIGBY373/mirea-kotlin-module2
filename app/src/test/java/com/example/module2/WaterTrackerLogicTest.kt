package com.example.module2

import com.example.module2.tasks.nextStreak
import org.junit.Assert.assertEquals
import org.junit.Test

class WaterTrackerLogicTest {

    @Test
    fun goalReached_streakIncreases() {
        assertEquals(1, nextStreak(waterCount = 1500, currentStreak = 0))
        assertEquals(4, nextStreak(waterCount = 2000, currentStreak = 3))
    }

    @Test
    fun goalMissed_streakResets() {
        assertEquals(0, nextStreak(waterCount = 1250, currentStreak = 5))
        assertEquals(0, nextStreak(waterCount = 0, currentStreak = 0))
    }
}
