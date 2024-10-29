package com.roxx.grigarage.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.padding
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.roxx.grigarage.presentation.screens.onboarding.goal.GoalScreen
import com.roxx.grigarage.presentation.screens.onboarding.logo.LogoScreen
import com.roxx.grigarage.presentation.screens.onboarding.name.NameScreen
import com.roxx.grigarage.presentation.screens.onboarding.welcome.WelcomeScreen
import com.roxx.grigarage.presentation.screens.tracker.capture.CaptureScreen
import com.roxx.grigarage.presentation.screens.tracker.detail.DetailScreen
import com.roxx.grigarage.presentation.screens.tracker.favorite.FavoriteScreen
import com.roxx.grigarage.presentation.screens.tracker.main.MainScreen
import com.roxx.grigarage.presentation.screens.tracker.profile.ProfileScreen
import com.roxx.grigarage.presentation.screens.tracker.search.SearchScreen

@Composable
fun AppNav() {
    val navController = rememberNavController()
    val snackBarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(snackBarHostState)
        },
        bottomBar = {
            BottomNavBar(navController)
        },
        content = {
            NavHost(
                modifier = Modifier.padding(it),
                navController = navController,
                startDestination = Route.LOGO
            ) {
                composable(Route.LOGO) {
                    LogoScreen(
                        navController::navigate
                    )
                }
                composable(Route.WELCOME) {
                    WelcomeScreen(navController::navigate)
                }
                composable(Route.NAME) {
                    NameScreen(
                        snackBarHostState = snackBarHostState,
                        navController::navigate
                    )
                }
                composable(Route.GOAL) {
                    GoalScreen(
                        snackBarHostState = snackBarHostState,
                        navController::navigate
                    )
                }
                composable(Route.MAIN) {
                    MainScreen(
                        navController::navigate
                    )
                }
                composable(
                    route = Route.DETAIL + "/{id}",
                    arguments = listOf(
                        navArgument("id") {
                            type = NavType.IntType
                        }
                    )
                ) {
                    val beerId = it.arguments?.getInt("id")!!
                    DetailScreen(
                        onNavigateUp = {
                            navController.navigateUp()
                        },
                        id = beerId
                    )
                }
                composable(Route.CAPTURE) {
                    CaptureScreen(
                        snackBarHostState = snackBarHostState,
                        navController::navigate
                    )
                }
                composable(
                    route = Route.SEARCH
                ) {
                    SearchScreen(
                        navController::navigate
                    )
                }
                composable(Route.PROFILE) {
                    ProfileScreen(
                        navController::navigate
                    )
                }
                composable(Route.FAVORITE) {
                    FavoriteScreen(
                        navController::navigate
                    )
                }
            }
        }
    )
}