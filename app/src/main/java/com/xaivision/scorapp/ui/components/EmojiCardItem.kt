package com.xaivision.scorapp.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.xaivision.scorapp.ui.theme.ScoreAppThemeO.dimens

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EmojiCardItem(
    modifier: Modifier = Modifier,
    emoji: String,
    title: String,
    onClicks: () -> Unit
) {
    Card(
        onClick = onClicks,
        modifier = modifier.fillMaxWidth(),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,

            modifier = Modifier.padding(
                vertical = dimens.grid_1,
                horizontal = dimens.grid_1,

            )
        ) {
            Text(
                text = emoji,
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.width(dimens.grid_1_5))

            Text(
                text = title,
                textAlign = TextAlign.Justify,
                style = MaterialTheme.typography.body1
            )
            Spacer(modifier = Modifier.width(dimens.grid_1_5))
        }
    }
}
