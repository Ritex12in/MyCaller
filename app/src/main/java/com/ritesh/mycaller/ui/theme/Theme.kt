package com.ritesh.mycaller.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColors = darkColorScheme(
    primary = Purple80,
    secondary = NavItemBgLight,
    tertiary = NavItemBgDark,
    onPrimary = TopBarBgDark,
    onSecondary = Background2Dark,
    onTertiary = Background3Dark,
    surfaceTint = ProfileBgDark

)

private val LightColors = lightColorScheme(
    primary = Purple40,
    secondary = NavItemBgDark,
    tertiary = NavItemBgLight,
    onPrimary = TopBarBgLight,
    onSecondary = Background2Light,
    onTertiary = Background3Light,
    surfaceTint = ProfileBgLight

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun MyCallerTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (!useDarkTheme) {
        LightColors
    } else {
        DarkColors
    }


    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}