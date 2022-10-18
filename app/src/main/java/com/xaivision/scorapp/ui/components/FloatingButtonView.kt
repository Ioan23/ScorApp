package com.xaivision.scorapp.ui.components


import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.xaivision.scorapp.ui.theme.ScoreAppThemeO


@Composable
fun FloatingButtonView(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    FloatingActionButton(
        shape = RoundedCornerShape(ScoreAppThemeO.dimens.grid_5),
        backgroundColor = ScoreAppThemeO.colors.secondaryVariant,
        onClick = onClick)
    {
        Icon(Icons.Filled.Add, "")
    }
}