package com.example.marvelapp

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.marvelapp.ui.theme.AppTheme

@Composable
fun HeroDetail(heroId: Int, onClick: () -> Unit) {
    val hero = getHeroById(heroId)

    Box(modifier = Modifier.fillMaxSize()) {
        if (hero != null) {
            AsyncImage(
                model = hero.imageUrl,
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
                .padding(top = 25.dp),
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
                    .padding(horizontal = 20.dp)
            ) {
                Column(Modifier.padding(bottom = 35.dp)) {
                    Text(
                        text = stringResource(id = hero.name),
                        style = AppTheme.TextStyle.Default_34,
                        color = AppTheme.TextColors.white,
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = stringResource(id = hero.info),
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
fun HeroDetailPreview() {
    HeroDetail(heroId = 3, onClick = {})
}