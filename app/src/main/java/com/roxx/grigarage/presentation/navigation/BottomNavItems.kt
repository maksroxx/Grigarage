package com.roxx.grigarage.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CenterFocusStrong
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.CenterFocusStrong
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItems(
    val route: String,
    val title: String,
    val icon: ImageVector,
    val iconFocused: ImageVector
) {
    object ListScreen: BottomNavItems(
        route = Route.MAIN,
        title = "Home",
        icon = Icons.Outlined.Home,
        iconFocused = Icons.Filled.Home
    )
    object SearchScreen: BottomNavItems(
        route = Route.SEARCH,
        title = "Search",
        icon = Icons.Outlined.Search,
        iconFocused = Icons.Filled.Search
    )
    object CaptureScreen: BottomNavItems(
        route = Route.CAPTURE,
        title = "Capture",
        icon = Icons.Outlined.CenterFocusStrong,
        iconFocused = Icons.Filled.CenterFocusStrong
    )
    object ProfileScreen: BottomNavItems(
        route = Route.PROFILE,
        title = "Profile",
        icon = Icons.Outlined.Person,
        iconFocused = Icons.Filled.Person
    )
}