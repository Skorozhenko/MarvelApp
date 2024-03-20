package com.example.marvelapp

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.marvelapp.ui.theme.AppTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScrollCard(onClick: (Int) -> Unit, scrollState: LazyListState) {
    LazyRow(
        modifier = Modifier
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        state = scrollState,
        flingBehavior = rememberSnapFlingBehavior(lazyListState = scrollState)
    ) {
        items(heroes) { hero ->
            HeroCard(hero = hero, onClick)
        }
    }
}


@Composable
fun HeroCard(hero: Hero, onClick: (Int) -> Unit = {}) {
    val isHover = remember { mutableStateOf(false) }

    val backgroundColor = if (isHover.value) {
        AppTheme.BgColor.hover
    } else {
        AppTheme.BgColor.transparent
    }

    Box(
        modifier = Modifier
            .height(550.dp)
            .width(300.dp)
            .padding(20.dp)
            .clip(shape = RoundedCornerShape(15.dp))
            .clickable { isHover.value = !isHover.value
                onClick(hero.id) }
            .background(backgroundColor),
        contentAlignment = Alignment.BottomStart,
    ) {
        Image(
            painter = painterResource(id = hero.image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds,
        )
        Text(
            text = stringResource(id = hero.name),
            style = AppTheme.TextStyle.Default_28,
            color = AppTheme.TextColors.white,
            modifier = Modifier
                .padding(start = 20.dp, bottom = 30.dp)
        )
    }
}


@Preview
@Composable
fun ScrollCardPreview() {
    ScrollCard(onClick = {}, scrollState = LazyListState())
}