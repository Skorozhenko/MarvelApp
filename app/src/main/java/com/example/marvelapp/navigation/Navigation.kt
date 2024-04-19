package com.example.marvelapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.marvelapp.retrofit.DetailViewModel
import com.example.marvelapp.screens.DetailScreen
import com.example.marvelapp.screens.MarvelScreen

@Preview(showBackground = true)
@Composable
fun Navigation() {
    val navController = rememberNavController()
//    val context = LocalContext.current
//    val onBackPressedDispatcher = (context as MainActivity).onBackPressedDispatcher
//
//    val callback = object : OnBackPressedCallback(true) {
//        override fun handleOnBackPressed() {}
//    }
//    onBackPressedDispatcher.addCallback(callback)


    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route

    ) {
        composable(Screen.MainScreen.route) {
            MarvelScreen(navController = navController)
        }

        composable(
            Screen.DetailScreen.route + "/{heroId}",
            arguments = listOf(navArgument("heroId") { type = NavType.IntType })
        ) { backStackEntry ->
            val heroId = backStackEntry.arguments?.getInt("heroId")
            heroId?.let {
                val viewModel: DetailViewModel = viewModel()
                viewModel.getHeroById(heroId)

                DetailScreen(viewModel = viewModel) {
                    //navController.popBackStack()
                    navController.navigate(Screen.MainScreen.route)
                }

//                val callback = object : OnBackPressedCallback(true) {
//                    override fun handleOnBackPressed() {
//                        navController.navigate(Screen.MainScreen.route)
//                    }
//                }
//                onBackPressedDispatcher.addCallback(callback)

            }
        }
    }
}

sealed class Screen(val route: String) {
    data object MainScreen : Screen("main_screen")
    data object DetailScreen : Screen("detail_screen")
}