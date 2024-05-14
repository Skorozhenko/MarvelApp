package com.example.marvelapp.ui.screens.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.marvelapp.data.model.ui.UiResultsModel
import com.example.marvelapp.navigation.Screen
import com.example.marvelapp.ui.components.MarvelApiStatus
import com.example.marvelapp.ui.theme.AppTheme

@Composable
fun DetailScreen(navController: NavController, viewModel: DetailViewModel, heroId: Int) {
    val heroData by viewModel.heroDataModel.observeAsState()
    val status by viewModel.status.observeAsState()

    LaunchedEffect(key1 = heroId) {
        viewModel.getHeroById(heroId)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (status) {
            MarvelApiStatus.LOADING -> {
                CircularProgressIndicator(
                    modifier = Modifier.size(50.dp)
                )
            }

            MarvelApiStatus.ERROR -> {
                Text(
                    text = "Error loading heroes",
                    modifier = Modifier.padding(16.dp),
                    color = Color.Red
                )
            }

            MarvelApiStatus.DONE -> {
                heroData?.let { HeroDetails(navController, it, heroId) }
            }

            else -> {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun HeroDetails(navController: NavController, heroData: UiResultsModel, heroId: Int) {
    if (heroData.id == heroId) {
        Box(modifier = Modifier.fillMaxSize()) {
            heroData.let { heroInfo ->
                AsyncImage(
                    model = heroInfo.thumbnail,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Button(
                    onClick = { navController.navigate(Screen.MainScreen.route) },
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(AppTheme.Paddings.HeroDetailButton),
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    shape = CircleShape
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = AppTheme.ButtonColor.buttonColor
                    )
                }

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(AppTheme.Paddings.HeroDetailHorizontalPadding)
                ) {
                    Column(Modifier.padding(AppTheme.Paddings.HeroDetailTextPadding)) {
                        Text(
                            text = heroInfo.name,
                            style = AppTheme.TextStyle.Default_34,
                            color = AppTheme.TextColors.white,
                        )
                        Spacer(modifier = Modifier.height(AppTheme.Paddings.HeroDetailSpacer))
                        Text(
                            text = heroInfo.description,
                            style = AppTheme.TextStyle.Default_24,
                            color = AppTheme.TextColors.white,
                        )
                    }
                }
            }
        }
    }
}
