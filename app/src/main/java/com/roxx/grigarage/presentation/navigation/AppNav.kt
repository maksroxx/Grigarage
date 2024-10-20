package com.roxx.grigarage.presentation.navigation

import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.padding
import com.roxx.grigarage.presentation.screens.onboarding.goal.GoalScreen
import com.roxx.grigarage.presentation.screens.onboarding.info.InfoScreen
import com.roxx.grigarage.presentation.screens.onboarding.name.NameScreen
import com.roxx.grigarage.presentation.screens.onboarding.welcome.WelcomeScreen
import com.roxx.grigarage.presentation.screens.tracker.capture.CaptureScreen
import com.roxx.grigarage.presentation.screens.tracker.main.MainScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
                startDestination = Route.MAIN
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
                    MainScreen(
                        navController::navigate
                    )
                }
                composable(Route.DETAIL) {

                }
                composable(Route.CAPTURE) {
                    CaptureScreen(
                        snackBarHostState = snackBarHostState,
                        navController::navigate
                    )
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