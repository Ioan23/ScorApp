package com.xaivision.scorapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.xaivision.scorapp.R
import com.xaivision.scorapp.ui.theme.ScoreAppThemeO

@Composable
fun OverflowMenuView(
    modifier: Modifier = Modifier,
    onEditClick: () -> Unit,
    onRemoveClick: () -> Unit,
) {
    
    Spacer(modifier = Modifier)
    Box(
        contentAlignment = Alignment.CenterEnd
    ) {
        var showMenu by remember { mutableStateOf(false) }

        IconButton(onClick = { showMenu = !showMenu }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "",
            )
        }
        DropdownMenu(
            modifier = Modifier
                .background(
                    color = ScoreAppThemeO.colors.background,
                    RoundedCornerShape(ScoreAppThemeO.dimens.grid_1)
                )
                .width(150.dp),
            expanded = showMenu,
            onDismissRequest = { showMenu = false }
        ) {
            DropdownMenuItem(onClick = {
                onEditClick()
                showMenu = false
        }) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "",
                )
                Text(
                    text = stringResource(id = R.string.overflow_menu_edit),
                    modifier = Modifier.padding(start = ScoreAppThemeO.dimens.grid_1)
                )
            }
            DropdownMenuItem(onClick = {
                onRemoveClick()
               showMenu = false
            })
            {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "",
                )
                Text(
                    text = stringResource(id = R.string.overflow_menu_remove),
                    modifier = Modifier.padding(start = ScoreAppThemeO.dimens.grid_1)
                )
            }
        }
    }
}