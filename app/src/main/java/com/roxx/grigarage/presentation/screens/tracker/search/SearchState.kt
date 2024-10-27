package com.roxx.grigarage.presentation.screens.tracker.search

data class SearchState(
    val query: String = "",
    val isHintVisible: Boolean = false,
    val isSearching: Boolean = false
)