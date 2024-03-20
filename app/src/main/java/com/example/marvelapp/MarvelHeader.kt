package com.example.marvelapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.marvelapp.ui.theme.AppTheme

@Composable
fun MarvelHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 35.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.marvel_studio),
            contentDescription = "",
            modifier = Modifier.size(width = 190.dp, height = 35.dp)
        )
        Spacer(modifier = Modifier.height(35.dp))
        Text(
            text = stringResource(id = R.string.header),
            style = AppTheme.TextStyle.Default_28,
            color = AppTheme.TextColors.white,
        )
    }
}


@Preview
@Composable
fun MarvelHeaderPreview() {
    MarvelHeader()
}