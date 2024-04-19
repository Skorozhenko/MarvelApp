package com.example.marvelapp.screens.components

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.marvelapp.ui.theme.AppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScrollCard(scrollState: LazyListState, heroes: DataModel, onClick: (Int) -> Unit) {
    Log.d("HeroesList", "Received heroes: $heroes")

    LazyRow(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        state = scrollState,
        flingBehavior = rememberSnapFlingBehavior(lazyListState = scrollState),
        content = {
            items(heroes.results) { hero ->
                HeroCard(hero = hero, onClick = onClick)
            }
        }
    )
}

@Composable
fun HeroCard(hero: ResultsModel, onClick: (Int) -> Unit = {}) {
    val isHover = remember { mutableStateOf(false) }

    val backgroundColor = if (isHover.value) {
        AppTheme.BgColor.hover
    } else {
        AppTheme.BgColor.transparent
    }

    Box(
        modifier = Modifier
            .size(
                height = AppTheme.Size.HeroCardHeight,
                width = AppTheme.Size.HeroCardWidth
            )
            .padding(AppTheme.Paddings.HeroCardPadding)
            .clip(shape = RoundedCornerShape(15.dp))
            .clickable {
                isHover.value = !isHover.value
                onClick(hero.id)
            }
            .background(backgroundColor),
        contentAlignment = Alignment.BottomStart,
    ) {
        AsyncImage(
            model = hero.thumbnail.path + hero.thumbnail.extension,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
        Text(
            text = hero.name,
            style = AppTheme.TextStyle.Default_28,
            color = AppTheme.TextColors.white,
            modifier = Modifier
                .padding(AppTheme.Paddings.PaddingValues_HeroCard)
        )
    }
}