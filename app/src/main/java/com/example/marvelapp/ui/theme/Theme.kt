package com.example.marvelapp.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object AppTheme {
    object TextColors {
        val white = Color(255,255,255)
    }

    object ButtonColor {
        val buttonColor = Color(255, 255, 255)
    }

    object BgColor {
        val bgColor = Color(41, 37, 41, 255)
        val triangleColor = Color(114, 17, 19, 255)
        val transparent = Color.Transparent
        val hover = Color.Red.copy(alpha = 0.5f)
    }

    object TextStyle {
        val Default_28
            @Composable get() =
                TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.W800,
                    fontSize = 28.sp
                )

        val Default_34
            @Composable get() =
                TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.W800,
                    fontSize = 34.sp
                )

        val Default_24
            @Composable get() =
                TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.W700,
                    fontSize = 24.sp
                )
    }
}