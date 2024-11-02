package com.roxx.grigarage

import android.content.SharedPreferences
import com.roxx.grigarage.data.preferences.PreferencesImpl
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PreferencesTest {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var preferences: PreferencesImpl

    companion object {
        const val KEY_USER_NAME = "user_name"
        const val KEY_TOTAL_BEER_COUNT = "total_beer_count"
        const val KEY_TOTAL_BOTTLE_COUNT = "total_bottle_count"
        const val KEY_WEEKLY_GOAL = "weekly_goal"
        const val KEY_CURRENT_WEEK_BEER_COUNT = "current_week_beer_count"
        const val KEY_MOST_POPULAR_BEER = "most_popular_beer"
        const val KEY_SHOULD_SHOW_ONBOARDING = "should_show_onboarding"
    }

    @Before
    fun setUp() {
        sharedPreferences = mockk(relaxed = true)
        preferences = PreferencesImpl(sharedPreferences)
    }

    @Test
    fun testGetUserName_ReturnsDefaultWhenNoValueSet() {
        every { sharedPreferences.getString(KEY_USER_NAME, "bob") } returns null
        val userName = preferences.getUserName()
        assertEquals("bob", userName)
    }

    @Test
    fun testGetTotalBeerCount_ReturnsDefaultWhenNoValueSet() {
        every { sharedPreferences.getInt(KEY_TOTAL_BEER_COUNT, 0) } returns 0
        val count = preferences.getTotalBeerCount()
        assertEquals(0, count)
    }

    @Test
    fun testGetTotalBottleCount_ReturnsDefaultWhenNoValueSet() {
        every { sharedPreferences.getInt(KEY_TOTAL_BOTTLE_COUNT, 0) } returns 0
        val count = preferences.getTotalBottleCount()
        assertEquals(0, count)
    }

    @Test
    fun testGetWeeklyGoal_ReturnsDefaultWhenNoValueSet() {
        every { sharedPreferences.getInt(KEY_WEEKLY_GOAL, 0) } returns 0
        val goal = preferences.getWeeklyGoal()
        assertEquals(0, goal)
    }

    @Test
    fun testGetCurrentWeekBeerCount_ReturnsDefaultWhenNoValueSet() {
        every { sharedPreferences.getInt(KEY_CURRENT_WEEK_BEER_COUNT, 0) } returns 0
        val count = preferences.getCurrentWeekBeerCount()
        assertEquals(0, count)
    }

    @Test
    fun testGetMostPopularBeer_ReturnsDefaultWhenNoValueSet() {
        every { sharedPreferences.getString(KEY_MOST_POPULAR_BEER, "Bud") } returns null
        val beer = preferences.getMostPopularBeer()
        assertEquals("Bud", beer)
    }

    @Test
    fun testSetUserName_SavesCorrectly() {
        preferences.setUserName("Alice")
        verify { sharedPreferences.edit().putString(KEY_USER_NAME, "Alice").apply() }
    }

    @Test
    fun testSetTotalBeerCount_SavesCorrectly() {
        preferences.setTotalBeerCount(5)
        verify { sharedPreferences.edit().putInt(KEY_TOTAL_BEER_COUNT, 5).apply() }
    }

    @Test
    fun testSetTotalBottleCount_SavesCorrectly() {
        preferences.setTotalBottleCount(10)
        verify { sharedPreferences.edit().putInt(KEY_TOTAL_BOTTLE_COUNT, 10).apply() }
    }

    @Test
    fun testSetWeeklyGoal_SavesCorrectly() {
        preferences.setWeeklyGoal(15)
        verify { sharedPreferences.edit().putInt(KEY_WEEKLY_GOAL, 15).apply() }
    }

    @Test
    fun testSetCurrentWeekBeerCount_SavesCorrectly() {
        preferences.setCurrentWeekBeerCount(20)
        verify { sharedPreferences.edit().putInt(KEY_CURRENT_WEEK_BEER_COUNT, 20).apply() }
    }

    @Test
    fun testSetMostPopularBeer_SavesCorrectly() {
        preferences.setMostPopularBeer("IPA")
        verify { sharedPreferences.edit().putString(KEY_MOST_POPULAR_BEER, "IPA").apply() }
    }

    @Test
    fun testSaveShouldShowOnboarding_SavesCorrectly() {
        preferences.saveShouldShowOnboarding(true)
        verify { sharedPreferences.edit().putBoolean(KEY_SHOULD_SHOW_ONBOARDING, true).apply() }
    }

    @Test
    fun testLoadShouldShowOnboarding_ReturnsDefaultWhenNoValueSet() {
        every { sharedPreferences.getBoolean(KEY_SHOULD_SHOW_ONBOARDING, true) } returns true
        val shouldShow = preferences.loadShouldShowOnboarding()
        assertEquals(true, shouldShow)
    }
}