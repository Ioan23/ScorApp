package com.xaivision.scorapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SelectableCardItem(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String? = null,
    emoji: String? = null,
    naceCode: String? = null,
    plusIconId: Int? = null,
    onItemClick: () -> Unit = {},
    minusIconId: Int?=null,
    onRemoveNaceClick: () -> Unit = {},
    content: @Composable () -> Unit = {},
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onItemClick() },
        elevation = 2.dp
    ) {
        SelectableRow(
            title = title, subtitle = subtitle, emoji = emoji,
            naceCode = naceCode, plusIconId = plusIconId,
            minusIconId = minusIconId, onRemoveItem = onRemoveNaceClick, content = content,
        )
    }
}

@Preview
@Composable
fun SelectableCardPreview() {
    SelectableCardItem(
        modifier = Modifier,
        title = "6202",
        subtitle = "Activitati de realizare a software-ului",
        emoji = "\uD83D\uDCBB",
        onItemClick = {},
    )
}
