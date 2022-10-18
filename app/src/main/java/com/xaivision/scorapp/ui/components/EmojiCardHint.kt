package com.xaivision.scorapp.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.xaivision.scorapp.ui.theme.ScoreAppThemeO

@Composable
fun EmojiCardHint(
    modifier: Modifier = Modifier,
    emoji: String = "\uD83D\uDE4B\uD83C\uDFFB\u200D♂️",
    title: String,
) {
    Row(modifier = modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.fillMaxHeight().align(Alignment.Bottom),
            text = emoji,
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.width(ScoreAppThemeO.dimens.grid_2))
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            backgroundColor = ScoreAppThemeO.colors.secondary,
            elevation = 2.dp
        ) {
            Row(
                modifier = Modifier.padding(top = ScoreAppThemeO.dimens.grid_1,
                    bottom = ScoreAppThemeO.dimens.grid_1_5,
                    end = ScoreAppThemeO.dimens.grid_1_5,
                    start = ScoreAppThemeO.dimens.grid_1_5).fillMaxSize(),
               verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    color = ScoreAppThemeO.colors.onPrimary,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }

}
