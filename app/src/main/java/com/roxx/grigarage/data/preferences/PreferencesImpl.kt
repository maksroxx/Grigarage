package com.roxx.grigarage.data.preferences

import android.content.SharedPreferences
import com.roxx.grigarage.domain.preferences.Preferences
import com.roxx.grigarage.domain.preferences.Preferences.Companion.KEY_CURRENT_WEEK_BEER_COUNT
import com.roxx.grigarage.domain.preferences.Preferences.Companion.KEY_MOST_POPULAR_BEER
import com.roxx.grigarage.domain.preferences.Preferences.Companion.KEY_TOTAL_BEER_COUNT
import com.roxx.grigarage.domain.preferences.Preferences.Companion.KEY_TOTAL_BOTTLE_COUNT
import com.roxx.grigarage.domain.preferences.Preferences.Companion.KEY_USER_NAME
import com.roxx.grigarage.domain.preferences.Preferences.Companion.KEY_WEEKLY_GOAL

class PreferencesImpl(
    private val sharedPreferences: SharedPreferences
) : Preferences {
    override fun getUserName(): String {
        return sharedPreferences.getString(KEY_USER_NAME, "bob")!!
    }

    override fun getTotalBeerCount(): Int {
        return sharedPreferences.getInt(KEY_TOTAL_BEER_COUNT, 0)
    }

    override fun getTotalBottleCount(): Int {
        return sharedPreferences.getInt(KEY_TOTAL_BOTTLE_COUNT, 0)
    }

    override fun getWeeklyGoal(): Int {
        return sharedPreferences.getInt(KEY_WEEKLY_GOAL, 0)
    }

    override fun getCurrentWeekBeerCount(): Int {
        return sharedPreferences.getInt(KEY_CURRENT_WEEK_BEER_COUNT, 0)
    }

    override fun getMostPopularBeer(): String {
        return sharedPreferences.getString(KEY_MOST_POPULAR_BEER, "Bud")!!
    }

    override fun setUserName(name: String) {
        sharedPreferences.edit()
            .putString(KEY_USER_NAME, name)
            .apply()
    }

    override fun setTotalBeerCount(count: Int) {
        sharedPreferences.edit()
            .putInt(KEY_TOTAL_BEER_COUNT, count)
            .apply()
    }

    override fun setTotalBottleCount(count: Int) {
        sharedPreferences.edit()
            .putInt(KEY_TOTAL_BOTTLE_COUNT, count)
            .apply()
    }

    override fun setWeeklyGoal(goal: Int) {
        sharedPreferences.edit()
            .putInt(KEY_WEEKLY_GOAL, goal)
            .apply()
    }

    override fun setCurrentWeekBeerCount(count: Int) {
        sharedPreferences.edit()
            .putInt(KEY_CURRENT_WEEK_BEER_COUNT, count)
            .apply()
    }

    override fun setMostPopularBeer(beer: String) {
        sharedPreferences.edit()
            .putString(KEY_MOST_POPULAR_BEER, beer)
            .apply()
    }

    override fun saveShouldShowOnboarding(shouldShow: Boolean) {
        sharedPreferences.edit()
            .putBoolean(Preferences.KEY_SHOULD_SHOW_ONBOARDING, shouldShow)
            .apply()
    }

    override fun loadShouldShowOnboarding(): Boolean {
        return sharedPreferences.getBoolean(
            Preferences.KEY_SHOULD_SHOW_ONBOARDING,
            true
        )
    }
}