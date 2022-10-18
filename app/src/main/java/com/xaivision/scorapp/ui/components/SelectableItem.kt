package com.xaivision.scorapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.xaivision.scorapp.ui.theme.ScoreAppThemeO

@Composable
fun SelectableRow(
    emoji: String? = null,
    title: String,
    subtitle: String? = null,
    plusIconId: Int? = null,
    naceCode: String? = null,
    minusIconId: Int? = null,
    onRemoveItem: () -> Unit = {},
    content: @Composable () -> Unit = {},

    ) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 16.dp),
        contentAlignment = Alignment.CenterEnd
    )
    {

        Row(
            modifier = Modifier
                .padding(
                    start = ScoreAppThemeO.dimens.padding_Icon,
                    bottom = ScoreAppThemeO.dimens.padding_Icon,
                    top = ScoreAppThemeO.dimens.padding_Icon,
                    end = ScoreAppThemeO.dimens.grid_5
                )
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Box {
                emoji?.let {
                    if (it.isNotEmpty()) {
                        Text(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(end = ScoreAppThemeO.dimens.padding_Icon),
                            text = it,
                            style = MaterialTheme.typography.h1,
                        )
                    }
                }

                plusIconId?.let {
                    Icon(
                        painter = painterResource(id = it),
                        contentDescription = "",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(end = ScoreAppThemeO.dimens.padding_Icon)
                            .size(35.dp, 35.dp),
                        tint = Color.Black,
                    )
                }
            }

            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier,
                    text = title,
                    style = MaterialTheme.typography.body1,
                )

                subtitle?.let {
                    if (it.isNotEmpty()) {
                        Text(
                            modifier = Modifier,
                            text = it,
                            style = MaterialTheme.typography.body2,
                        )
                    }
                }
            }


        }
        minusIconId?.let {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                contentAlignment = Alignment.CenterEnd,
            ) {
                Icon(
                    painter = painterResource(id = it),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .clickable { onRemoveItem() }
                        .size(24.dp, 24.dp),
                    tint = ScoreAppThemeO.colors.error,
                )
            }
        }
        content()
    }
}



