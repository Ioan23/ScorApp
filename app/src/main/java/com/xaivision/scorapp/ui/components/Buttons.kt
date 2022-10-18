package com.xaivision.scorapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xaivision.scorapp.ui.theme.ScoreAppThemeO


private val BUTTON_HEIGHT_LARGE = 48.dp
private val BUTTON_HEIGHT_SMALL = 24.dp

/**
 * Standard button for primary or secondary buttons of all sizes.
 * @param buttonType used to set the button type
 */
@Composable
fun CustomButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    buttonType: ButtonType = ButtonType.PRIMARY,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
) {
    // In case some properties on buttons need to be changed, they can be hoisted up as a parameter
    // with a reasonable default for most cases.

    Button(
        onClick = { onClick() },
        modifier = modifier
            .height(BUTTON_HEIGHT_LARGE)
            .defaultMinSize(minHeight = ScoreAppThemeO.dimens.minimumTappableArea),
        enabled = enabled,
        colors = buttonColors(buttonType),
        border = buttonBorderStroke(buttonType),
        contentPadding = contentPadding,
        shape = MaterialTheme.shapes.medium
    ) {
        ButtonText(text)
    }
}

@Composable
fun SmallTextButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    buttonType: ButtonType = ButtonType.SECONDARY,
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = ScoreAppThemeO.dimens.grid_1,
        vertical = ScoreAppThemeO.dimens.grid_0_5,
    ),
) {
    TextButton(
        onClick = onClick,
        modifier = modifier.height(BUTTON_HEIGHT_SMALL),
        enabled = enabled,
        colors = buttonColors(buttonType),
        border = buttonBorderStroke(buttonType),
        contentPadding = contentPadding
    ) {
        Text(
            text = text,
            fontSize = 13.sp,
            style = MaterialTheme.typography.button,
        )
    }
}

@Composable
private fun buttonBorderStroke(buttonType: ButtonType) =
    if (buttonType == ButtonType.SECONDARY) {
        BorderStroke(ScoreAppThemeO.dimens.grid_0_25, SolidColor(ScoreAppThemeO.colors.secondary))
    } else if (buttonType == ButtonType.DESTRUCTIVE) {
        BorderStroke(ScoreAppThemeO.dimens.grid_0_25, SolidColor(ScoreAppThemeO.colors.highlightDark))
    } else {
        null
    }

@Composable
private fun buttonColors(buttonType: ButtonType) =
    when (buttonType) {
        ButtonType.PRIMARY -> ButtonDefaults.buttonColors(
            backgroundColor = ScoreAppThemeO.colors.primary,
            contentColor = ScoreAppThemeO.colors.onPrimary
        )
        ButtonType.SECONDARY -> ButtonDefaults.buttonColors(
            backgroundColor = ScoreAppThemeO.colors.secondary,
            contentColor = ScoreAppThemeO.colors.onPrimary
        )
        ButtonType.VARIANT -> {
            ButtonDefaults.buttonColors(
                backgroundColor = ScoreAppThemeO.colors.secondaryVariant,
                contentColor = ScoreAppThemeO.colors.secondary
            )
        }
        ButtonType.DESTRUCTIVE -> {
            ButtonDefaults.buttonColors(
                backgroundColor = ScoreAppThemeO.colors.surface,
                contentColor = ScoreAppThemeO.colors.highlightDark
            )
        }
    }

@Composable
internal fun ButtonText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.button,
) {
    Text(
        modifier = modifier,
        text = text,
        style = style,
    )
}
enum class ButtonType {
    PRIMARY, SECONDARY, VARIANT, DESTRUCTIVE
}

