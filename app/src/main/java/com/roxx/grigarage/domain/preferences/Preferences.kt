package com.roxx.grigarage.domain.preferences


interface Preferences {
    // get
    fun getUserName(): String
    fun getTotalBeerCount(): Int
    fun getTotalBottleCount(): Int
    fun getWeeklyGoal(): Int
    fun getCurrentWeekBeerCount(): Int
    fun getMostPopularBeer(): String

    // save
    fun setUserName(name: String)
    fun setTotalBeerCount(count: Int)
    fun setTotalBottleCount(count: Int)
    fun setWeeklyGoal(goal: Int)
    fun setCurrentWeekBeerCount(count: Int)
    fun setMostPopularBeer(beer: String)

    // onboarding
    fun saveShouldShowOnboarding(shouldShow: Boolean)
    fun loadShouldShowOnboarding(): Boolean

    companion object {
        const val KEY_USER_NAME = "user_name"
        const val KEY_TOTAL_BEER_COUNT = "total_beer_count"
        const val KEY_TOTAL_BOTTLE_COUNT = "total_bottle_count"
        const val KEY_WEEKLY_GOAL = "weekly_goal"
        const val KEY_CURRENT_WEEK_BEER_COUNT = "current_week_beer_count"
        const val KEY_MOST_POPULAR_BEER = "most_popular_beer"
        const val KEY_SHOULD_SHOW_ONBOARDING = "should_show_onboarding"
    }
}