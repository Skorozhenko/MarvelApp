package com.example.marvelapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.marvelapp.retrofit.DetailViewModel
import com.example.marvelapp.ui.theme.AppTheme

//@Composable
//fun DetailScreen(viewModel: DetailViewModel = viewModel(), onClick: () -> Unit) {
//    val heroItem by viewModel.heroDataModel.collectAsState()
//    val hero = heroItem.heroModel?.data?.results?.first()
//
//    Box(modifier = Modifier.fillMaxSize()) {
//        if (hero != null) {
//            AsyncImage(
//                model = hero.thumbnail.path + hero.thumbnail.extension,
//                contentDescription = null,
//                modifier = Modifier.fillMaxSize(),
//                contentScale = ContentScale.Crop
//            )
//
//            Button(
//                onClick = { onClick() },
//                modifier = Modifier
//                    .align(Alignment.TopStart)
//                    .padding(AppTheme.Paddings.HeroDetailButton),
//                colors = ButtonDefaults.buttonColors(Color.Transparent),
//                shape = CircleShape
//            ) {
//                Icon(
//                    imageVector = Icons.Default.ArrowBack,
//                    contentDescription = null,
//                    tint = AppTheme.ButtonColor.buttonColor
//                )
//            }
//
//            Box(
//                modifier = Modifier
//                    .align(Alignment.BottomStart)
//                    .padding(AppTheme.Paddings.HeroDetailHorizontalPadding)
//            ) {
//                Column(Modifier.padding(AppTheme.Paddings.HeroDetailTextPadding)) {
//                    Text(
//                        text = hero.name,
//                        style = AppTheme.TextStyle.Default_34,
//                        color = AppTheme.TextColors.white,
//                    )
//                    Spacer(modifier = Modifier.height(AppTheme.Paddings.HeroDetailSpacer))
//                    Text(
//                        text = hero.description,
//                        style = AppTheme.TextStyle.Default_24,
//                        color = AppTheme.TextColors.white,
//                    )
//                }
//            }
//        }
//    }
//}

@Composable
fun DetailScreen(viewModel: DetailViewModel = viewModel(), onClick: () -> Unit) {

    val heroItem by viewModel.heroDataModel.collectAsState()
    val hero = heroItem.heroModel?.data?.results?.first()


    Box(modifier = Modifier.fillMaxSize()) {
        if (hero != null) {
            AsyncImage(
                model = hero.thumbnail.path + hero.thumbnail.extension,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Button(
            onClick = { onClick() },
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
        if (hero != null) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(AppTheme.Paddings.HeroDetailHorizontalPadding)
            ) {
                Column(Modifier.padding(AppTheme.Paddings.HeroDetailTextPadding)) {
                    Text(
                        text = hero.name,
                        style = AppTheme.TextStyle.Default_34,
                        color = AppTheme.TextColors.white,
                    )
                    Spacer(modifier = Modifier.height(AppTheme.Paddings.HeroDetailSpacer))
                    Text(
                        text = hero.description,
                        style = AppTheme.TextStyle.Default_24,
                        color = AppTheme.TextColors.white,
                    )
                }
            }
        }
    }
}
@Preview
@Composable
fun DetailScreenPreview() {
    //DetailScreen(heroId = 3, onClick = {})
}