package com.xaivision.scorapp.personaldata.view

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.xaivision.scorapp.R
import com.xaivision.scorapp.personaldata.viewmodel.*
import com.xaivision.scorapp.ui.components.BottomAppBarView
import com.xaivision.scorapp.ui.components.TopBar
import com.xaivision.scorapp.ui.theme.ScorAppTheme
import com.xaivision.scorapp.ui.theme.ScoreAppThemeO

@Composable
fun ScorScreenView(
    scorNavigation: ScorNavigation,
    viewModel: ScorViewModel = hiltViewModel()
) {
    ScorAppTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { TopBar(title = stringResource(R.string.score_screen)) },
            bottomBar = {
                BottomAppBarView(
                    onBackScreen = {viewModel.onAction(BackActionClick) },
                    onNextScreen = { },
                    imageVectorBack = Icons.Filled.ArrowBack
                )
            },
            content = { padding ->
                ScorContent(
                    modifier = Modifier
                        .background(ScoreAppThemeO.colors.background)
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(
                            top = ScoreAppThemeO.dimens.grid_1_5,
                            start = ScoreAppThemeO.dimens.grid_1_5,
                            end = ScoreAppThemeO.dimens.grid_1_5,
                            bottom = padding.calculateBottomPadding()
                        ),
                    onInputTitleChange = {viewModel.onAction(InputTitleActionClick(it))},
                    onInputDescriptionChange = {viewModel.onAction(InputDescriptionActionClick(it))},
                    onInputDateChange ={viewModel.onAction(InputDateActionClick(it))},
                    viewModel = viewModel
                )
            }
        )
    }
    LaunchedEffect(null) {
        viewModel.container.sideEffectFlow.collect {
            when (it) {
                NavigateBack -> {
                    scorNavigation.moveBack()
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ScorContent(
    modifier: Modifier,
    viewModel: ScorViewModel,
    onInputTitleChange: (String) -> Unit,
    onInputDescriptionChange: (String) -> Unit,
    onInputDateChange: (String) -> Unit,
) {

    val viewState = viewModel.container.stateFlow.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleCard(
            modifier = Modifier.fillMaxWidth(),
            title = "Description of scor",
            content = {

                val maxCharTitle = 12
                TextField(
                    modifier = Modifier
                        .imePadding()
                        .fillMaxWidth(),
                    value = viewState.value.title,
                    onValueChange = { if (it.length <= maxCharTitle) onInputTitleChange(it) },
                    labelText = stringResource(R.string.title),
                    helperText = "",
                    isError = false,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Characters,
                        autoCorrect = false,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                    autofillTypes = listOf(
                        AutofillType.PersonLastName
                    ),
                    backgroundColor = ScoreAppThemeO.colors.background
                )

                val maxCharDescription = 40
                TextField(
                    modifier = Modifier
                        .imePadding()
                        .fillMaxWidth(),
                    value = viewState.value.description,
                    onValueChange = { if (it.length <= maxCharDescription) onInputDescriptionChange(it) },
                    labelText =stringResource(R.string.description),
                    helperText = "",
                    isError = false,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Characters,
                        autoCorrect = false,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                    autofillTypes = listOf(
                        AutofillType.PersonLastName
                    ),
                    backgroundColor = ScoreAppThemeO.colors.background
                )
                val maxCharDate = 8
                TextField(
                    modifier = Modifier
                        .imePadding()
                        .fillMaxWidth(),
                    value = viewState.value.date,
                    onValueChange = { if (it.length <= maxCharDate) onInputDateChange(it) },
                    labelText = stringResource(R.string.date),
                    helperText = "",
                    isError = false,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Characters,
                        autoCorrect = false,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                    autofillTypes = listOf(
                        AutofillType.PersonLastName
                    ),
                    backgroundColor = ScoreAppThemeO.colors.background
                )
            }
        )
        Spacer(modifier = Modifier.height(ScoreAppThemeO.dimens.grid_1_5))
    }
}

@Composable
private fun TitleCard(
    modifier: Modifier,
    title: String,
    content: @Composable (() -> Unit)
) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = ScoreAppThemeO.dimens.grid_1_5,
                    start = ScoreAppThemeO.dimens.grid_1_5,
                    end = ScoreAppThemeO.dimens.grid_1_5
                ),
        ) {
            Text(
                modifier = Modifier.padding(start = ScoreAppThemeO.dimens.grid_1),
                text = title,
                style = MaterialTheme.typography.subtitle2,
                color = ScoreAppThemeO.colors.secondary
            )
            Spacer(modifier = Modifier.height(ScoreAppThemeO.dimens.grid_1_5))

            content()
        }
    }
}
private val TEXT_FIELD_BORDER_WIDTH = 1.5.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextField(
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
                        isTextFieldFocused.value = focusState.isFocused
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
                colors = textFieldColors(backgroundColor),
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
fun textFieldColors(backgroundColor: Color) =
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



