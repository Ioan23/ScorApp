@file:JvmName("TextFieldKt")

package com.xaivision.scorapp.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.Autofill
import androidx.compose.ui.autofill.AutofillNode
import androidx.compose.ui.autofill.AutofillType
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalAutofill
import androidx.compose.ui.platform.LocalAutofillTree
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xaivision.scorapp.ui.theme.ScoreAppThemeO


private val TEXT_FIELD_BORDER_WIDTH = 1.5.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun InstantDocsTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    labelText: String? = null,
    isError: Boolean = false,
    readOnly: Boolean = false,
    helperText: String? = null,
    singleLine: Boolean = true,
    placeholder: @Composable (() -> Unit)? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    autofillTypes: List<AutofillType> = emptyList(),
    backgroundColor: Color = ScoreAppThemeO.colors.surface,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
) {
    Column(modifier = modifier) {
        val isTextFieldFocused = remember { mutableStateOf(false) }
        val borderColor = animateColorAsState(
            if (isTextFieldFocused.value) {
                if (isError) {
                    ScoreAppThemeO.colors.error
                } else {
                    ScoreAppThemeO.colors.secondary
                }
            } else {
                Color.Transparent
            }
        )
        val autofill: Autofill? = LocalAutofill.current
        val autofillNode = AutofillNode(onFill = onValueChange, autofillTypes = autofillTypes)
        LocalAutofillTree.current += autofillNode

        Column(
            modifier = Modifier.border(
                border = BorderStroke(TEXT_FIELD_BORDER_WIDTH, color = borderColor.value),
                shape = MaterialTheme.shapes.small
            )
        ) {
            TextField(
                value = value,
                singleLine = singleLine,
                readOnly = readOnly,
                onValueChange = onValueChange,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                placeholder = placeholder,
                interactionSource = interactionSource,
                modifier = modifier
                    .onGloballyPositioned { autofillNode.boundingBox = it.boundsInWindow() }
                    .onFocusChanged { focusState ->
                        // Update focus state value for outline animation
                        isTextFieldFocused.value = focusState.isFocused

                        // Request autofill when focused
                        if (autofill != null) {
                            if (focusState.isFocused) {
                                autofill.requestAutofillForNode(autofillNode)
                            } else {
                                autofill.cancelAutofillForNode(autofillNode)
                            }
                        }
                    },
                isError = isError,
                textStyle = MaterialTheme.typography.h3,
                label = if (labelText != null) {
                    { HelperText(text = labelText, isError = isError) }
                } else {
                    null
                },
                shape = MaterialTheme.shapes.small,
                colors = instantDocsTextFieldColors(backgroundColor),
                trailingIcon = trailingIcon
            )
        }

        if (helperText != null) {
            Spacer(Modifier.height(1.dp))
            HelperText(
                text = helperText,
                isError = isError
            )
        }
    }
}

@Composable
fun instantDocsTextFieldColors(backgroundColor: Color) =
    TextFieldDefaults.textFieldColors(
        backgroundColor = backgroundColor,
        textColor = ScoreAppThemeO.colors.onSurface,
        unfocusedIndicatorColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        errorIndicatorColor = Color.Transparent,
        errorCursorColor = ScoreAppThemeO.colors.primary,
    )

@Composable
private fun HelperText(
    text: String,
    isError: Boolean,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.caption,
        maxLines = 1,
        modifier = modifier,
        color = if (isError) ScoreAppThemeO.colors.error else ScoreAppThemeO.colors.primary,
    )
}


@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun InstantDocsTextFields() {
    val value = "TEXT HERE"
    InstantDocsTextField(value = value, onValueChange = {})


}
