package com.vunlph30245.lab6_ph30245

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun ScreenNavigation() {
    val navController = rememberNavController()
    val mainViewModel: MainViewModel = viewModel()
    val moviesState = mainViewModel.movies.observeAsState(initial = emptyList())

    NavHost(
        navController = navController,
        startDestination = Screens.LOGIN.route,
    ) {
        composable(Screens.LOGIN.route) { LoginScreen(navController) }
        composable(Screens.MOVIE_SCREEN.route) { MovieScreen(moviesState.value) }
        composable(Screens.SCREEN1.route) { Screen1(navController) }
        composable(Screens.SCREEN2.route) { Screen2(navController) }
        composable(Screens.SCREEN3.route) { Screen3(navController) }
    }
}

@Composable
fun Screen1(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
            .clickable {
                navController.navigate(Screens.SCREEN2.route) {
                    popUpTo(Screens.SCREEN1.route) { inclusive = true }
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Text("Screen 1", color = Color.White, fontSize = 20.sp)
    }
}

@Composable
fun Screen2(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow)
            .clickable { navController.navigate(Screens.SCREEN3.route) },
        contentAlignment = Alignment.Center
    ) {
        Text("Screen 2", color = Color.White, fontSize = 20.sp)
    }
}

@Composable
fun Screen3(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green)
            .clickable { navController.navigate(Screens.SCREEN1.route) },
        contentAlignment = Alignment.Center
    ) {
        Text("Screen 3", color = Color.White, fontSize = 20.sp)
    }
}



