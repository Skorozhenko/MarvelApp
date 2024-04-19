package com.example.marvelapp.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.marvelapp.navigation.Screen
import com.example.marvelapp.retrofit.MarvelViewModel
import com.example.marvelapp.screens.components.MarvelHeader
import com.example.marvelapp.screens.components.ScrollCard
import com.example.marvelapp.ui.theme.AppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun MarvelScreen(navController: NavController) {
    val viewModel: MarvelViewModel = viewModel()
    val heroItems by viewModel.heroDataModel.collectAsState()
    val scrollState = rememberLazyListState()

    ApplySystemBarColors()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.BgColor.bgColor)
    ) {
        Canvas(
            modifier = Modifier
                .size(AppTheme.Size.CanvasSize)
                .align(Alignment.BottomStart)
        ) {
            val path = Path()
            path.moveTo(size.width, 0f)
            path.lineTo(size.width, size.height)
            path.lineTo(0f, size.height)
            path.close()

            drawPath(path, color = AppTheme.BgColor.triangleColor)
        }
    }

    Column {
        MarvelHeader()

        heroItems.heroModel?.data?.let { data ->
            ScrollCard(scrollState = scrollState, heroes = data) { heroId ->
                navController.navigate(Screen.DetailScreen.route + "/$heroId")
            }
        }
    }
}


@Composable
private fun ApplySystemBarColors() {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(color = AppTheme.BgColor.transparent)
        systemUiController.setNavigationBarColor(color = AppTheme.BgColor.transparent)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMarvelScreen() {
    MarvelScreen(navController = rememberNavController())
}