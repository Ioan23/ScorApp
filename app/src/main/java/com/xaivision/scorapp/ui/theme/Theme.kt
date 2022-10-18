package com.xaivision.scorapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration

private val DarkColorPalette = ScorappColors(
    primary = white,
    disabled = disabledDark,
    highlight = mainBlueDark,
    highlightOrange = orangeDark,
    highlightDark = red20,
    highlightVariant = secondaryBlueDark,
    secondary = purple,
    secondaryVariant = purpleVariant,
    error = mainRedDark,
    errorVariant = secondaryRedDark,
    success = mainGreenDark,
    successVariant = secondaryGreenDark,
    divider = dividerGrayDark,
    background = backgroundGrayDark,
    surface = surfaceBlack,
    onPrimary = surfaceBlack,
    onSurface = white,
    onSurfaceVariant = grayComponentDark,
    onBackground = white,
    purple = purple20,
    gray = standardDarkGray ,
    lightPurple = purple30
)

private val LightColorPalette = ScorappColors(
    primary = black,
    disabled = disabled,
    highlight = mainBlue,
    highlightOrange = orange,
    highlightDark = red20,
    secondary = purple,
    secondaryVariant = purpleVariant,
    highlightVariant = secondaryBlue,
    error = mainRed,
    errorVariant = secondaryRed,
    success = mainGreen,
    successVariant = secondaryGreen,
    divider = dividerGray,
    background = backgroundGray,
    surface = white,
    onPrimary = white,
    onSurface = black,
    onSurfaceVariant = grayComponent,
    onBackground = black,
    purple = purple20,
    gray = standardDarkGray,
    lightPurple = purple30
)






@Composable
fun ScorAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val configuration = LocalConfiguration.current
    val dimensions = if (configuration.screenWidthDp < SCREEN_WIDTH_THRESHOLD) defaultsDimensions else sw360Dimensions
    val colorPalette = if (isSystemInDarkTheme()) DarkColorPalette else LightColorPalette
    val defaultColors =
        if (isSystemInDarkTheme()) {
            darkColors(primary = colorPalette.primary)
        } else {
            lightColors(primary = colorPalette.primary)
        }

    ProvideUiCoreColors(colorPalette) {
        MaterialTheme(
            colors = defaultColors,
            typography = typography,
            shapes = MVIDemoShapes,
            content = {
                ProvideUiCoreDimens(
                    dimens = dimensions,
                    content = content,
                )
            }
        )
    }
}


object ScoreAppThemeO {
    val colors: ScorappColors
        @Composable
        get() = LocalScorAppColors.current

    val dimens: ScorAppDimensions
        @Composable
        get() = LocalDimens.current
}



class ScorappColors(
    primary: Color,
    disabled: Color,
    highlight: Color,
    highlightOrange: Color,
    highlightVariant: Color,
    highlightDark: Color,
    secondary: Color,
    secondaryVariant: Color,
    error: Color,
    errorVariant: Color,
    success: Color,
    successVariant: Color,
    divider: Color,
    background: Color,
    surface: Color,
    onPrimary: Color,
    onSurface: Color,
    onSurfaceVariant: Color,
    onBackground: Color,
    purple: Color,
    lightPurple: Color,
    gray: Color
) {
    var primary by mutableStateOf(primary)
        private set
    var disabled by mutableStateOf(disabled)
        private set
    var highlight by mutableStateOf(highlight)
        private set
    var highlightOrange by mutableStateOf(highlightOrange)
        private set
    var highlightVariant by mutableStateOf(highlightVariant)
        private set
    var secondary by mutableStateOf(secondary)
        private set
    var secondaryVariant by mutableStateOf(secondaryVariant)
        private set
    var error by mutableStateOf(error)
        private set
    var errorVariant by mutableStateOf(errorVariant)
        private set
    var success by mutableStateOf(success)
        private set
    var successVariant by mutableStateOf(successVariant)
        private set
    var divider by mutableStateOf(divider)
        private set
    var background by mutableStateOf(background)
        private set
    var surface by mutableStateOf(surface)
        private set
    var onPrimary by mutableStateOf(onPrimary)
        private set
    var onSurface by mutableStateOf(onSurface)
        private set
    var onSurfaceVariant by mutableStateOf(onSurfaceVariant)
        private set
    var onBackground by mutableStateOf(onBackground)
        private set
    var highlightDark by mutableStateOf(highlightDark)
        private set
    var purple by mutableStateOf(purple)
        private set
    var gray by mutableStateOf(gray)
        private set
    var lightPurple by mutableStateOf(lightPurple)
        private set


    fun update(other: ScorappColors) {
        primary = other.primary
        disabled = other.disabled
        highlight = other.highlight
        highlightOrange = other.highlightOrange
        highlightVariant = other.highlightVariant
        error = other.error
        errorVariant = other.errorVariant
        success = other.success
        successVariant = other.successVariant
        divider = other.divider
        background = other.background
        surface = other.surface
        onPrimary = other.onPrimary
        onSurface = other.onSurface
        onSurfaceVariant = other.onSurfaceVariant
        onBackground = other.onBackground
        purple = other.purple
        gray = other.gray
        lightPurple = other.lightPurple
    }
}

private val LocalScorAppColors = staticCompositionLocalOf {
    LightColorPalette
}

@Composable
fun ProvideUiCoreColors(
    colors: ScorappColors,
    content: @Composable () -> Unit,
) {
    val colorPalette = remember { colors }
    colorPalette.update(colors)
    CompositionLocalProvider(LocalScorAppColors provides colorPalette, content = content)
}

private val LocalDimens = staticCompositionLocalOf { defaultsDimensions }

@Composable
fun ProvideUiCoreDimens(
    dimens: ScorAppDimensions,
    content: @Composable() () -> Unit,
) {
    val dimensionSet = remember { dimens }
    CompositionLocalProvider(LocalDimens provides dimensionSet, content = content)
}

