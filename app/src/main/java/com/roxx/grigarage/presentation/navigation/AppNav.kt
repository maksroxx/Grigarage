package com.roxx.grigarage.presentation.navigation

import android.annotation.SuppressLint
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
import com.roxx.grigarage.presentation.onboarding.goal.GoalScreen
import com.roxx.grigarage.presentation.onboarding.info.InfoScreen
import com.roxx.grigarage.presentation.onboarding.name.NameScreen
import com.roxx.grigarage.presentation.onboarding.welcome.WelcomeScreen

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun AppNav() {
    val navController = rememberNavController()
    val snackBarHostState = remember { SnackbarHostState() }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(snackBarHostState)
        },
        content = {
            NavHost(
                navController = navController,
                startDestination = Route.WELCOME
            ) {
                composable(Route.WELCOME) {
                    WelcomeScreen(navController::navigate)
                }
                composable(Route.INFO) {
                    InfoScreen(navController::navigate)
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

                }
                composable(Route.SEARCH) {

                }
                composable(Route.PROFILE) {

                }
                composable(Route.WISH) {

                }
                composable(Route.FAVORITE) {

                }
            }
        }
    )
}