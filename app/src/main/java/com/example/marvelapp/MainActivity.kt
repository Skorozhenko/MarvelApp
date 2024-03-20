package com.example.marvelapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.core.view.WindowCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val navController = rememberNavController()
            val scrollState = rememberLazyListState()

            NavHost(
                navController = navController,
                startDestination = "screen_1"
            ) {
                composable("screen_1") {
                    MarvelScreen(scrollState) { heroId ->
                        navController.navigate("screen_2/$heroId")
                    }
                }

//                composable(
//                    "Screen_2/{heroId}",
//                    arguments = listOf(navArgument("heroId") { type = NavType.IntType })
//                ) { backStackEntry ->
//                    val heroId = backStackEntry.arguments?.getInt("heroId")
//                    if (heroId != null) {
//                        HeroDetail(heroId = heroId) {
//                            navController.navigate("screen_1")
//                        }
//                    }
//                }
            }
        }
    }
}