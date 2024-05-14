package com.example.marvelapp.navigation

import androidx.activity.OnBackPressedCallback
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.marvelapp.MainActivity
import com.example.marvelapp.ui.screens.marvel.MarvelViewModel
import com.example.marvelapp.ui.screens.marvel.MarvelScreen
import com.example.marvelapp.ui.screens.detail.DetailViewModel
import com.example.marvelapp.ui.screens.detail.DetailScreen

@Composable
fun Navigation(
    marvelViewModel: MarvelViewModel,
    detailViewModel: DetailViewModel
) {
    val navController = rememberNavController()

    val context = LocalContext.current
    val onBackPressedDispatcher = (context as MainActivity).onBackPressedDispatcher

    val lazyListState = rememberLazyListState()

    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route
    ) {
        composable(Screen.MainScreen.route) {
            MarvelScreen(marvelViewModel, lazyListState) { heroId ->
                navController.navigate(Screen.DetailScreen.route + "/$heroId")
            }
        }

        composable(
            route = "${Screen.DetailScreen.route}/{heroId}",
            arguments = listOf(navArgument("heroId") { type = NavType.IntType })
        ) { backStackEntry ->
            val heroId = backStackEntry.arguments?.getInt("heroId") ?: -1
            DetailScreen(navController, detailViewModel, heroId)
            val callback = object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    navController.navigate(Screen.MainScreen.route)
                }
            }
            onBackPressedDispatcher.addCallback(callback)
        }
    }
}

sealed class Screen(val route: String) {
    data object MainScreen : Screen("main_screen")
    data object DetailScreen : Screen("detail_screen")
}