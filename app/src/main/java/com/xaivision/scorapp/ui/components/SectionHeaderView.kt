package com.xaivision.scorapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SectionHeaderView(
    modifier: Modifier = Modifier,
    title: String,
    buttonText: String,
    onButtonClick: () -> Unit,
    isButtonEnabled: Boolean = true,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.body1,
        )
        SmallTextButton(
            onClick = onButtonClick,
            text = buttonText,
            buttonType = ButtonType.VARIANT,
            enabled = isButtonEnabled
        )
    }
}

@Preview
@Composable
fun SectionHeaderPreview() {
    SectionHeaderView(
        title = "Activitate Principala",
        buttonText = "Adauga",
        onButtonClick = { /*TODO*/ })
}
