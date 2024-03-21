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
import com.example.marvelapp.screens.DetailScreen
import com.example.marvelapp.screens.MarvelScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val scrollState = rememberLazyListState()

    val context = LocalContext.current

    val onBackPressedDispatcher = (context as MainActivity).onBackPressedDispatcher

    val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {}
    }
    onBackPressedDispatcher.addCallback(callback)


    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route

    ) {
        composable(Screen.MainScreen.route) {
            MarvelScreen(scrollState) { heroId ->
                navController.navigate(Screen.DetailScreen.route + "/$heroId")
            }
        }

        composable(
            Screen.DetailScreen.route + "/{heroId}",
            arguments = listOf(navArgument("heroId") { type = NavType.IntType })
        ) { backStackEntry ->
            val heroId = backStackEntry.arguments?.getInt("heroId")
            if (heroId != null) {
                DetailScreen(heroId = heroId) {
                    navController.navigate(Screen.MainScreen.route)
                }

                val callback = object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        navController.navigate(Screen.MainScreen.route)
                    }
                }
                onBackPressedDispatcher.addCallback(callback)
            }
        }
    }
}

sealed class Screen(val route: String) {
    data object MainScreen : Screen("main_screen")
    data object DetailScreen : Screen("detail_screen")
}