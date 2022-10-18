package com.xaivision.scorapp.main.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.xaivision.scorapp.R
import com.xaivision.scorapp.main.model.models.Scor
import com.xaivision.scorapp.main.viewmodel.HomeNavigation
import com.xaivision.scorapp.main.viewmodel.HomeViewModel
import com.xaivision.scorapp.ui.components.*
import com.xaivision.scorapp.ui.theme.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreenView(
    appNavigator: HomeNavigation,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val modalBottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetShape = BottomSheetShape,
        sheetContent = {
            BottomSheetContent(
                viewModel = viewModel,
                title = stringResource(id = R.string.home_screen_title),
                onDoneButton = {
                    viewModel.onScorCreated(it)
                    coroutineScope.launch { modalBottomSheetState.hide() }
                },
                onInputTitle = {
                    viewModel.onInputTitle(it)
                },
                onInputDescription = {
                    viewModel.onInputDescription(it)
                },
                onInputDate = {
                    viewModel.onInputDate(it)
                },
            )
        },
    )
    {
        ScorAppTheme {
            Scaffold(
                topBar = {
                    TopBar(title = stringResource(id = R.string.home_screen_title))
                },
                floatingActionButton = {
                    FloatingButtonView(
                        modifier = Modifier,
                        onClick = {
                            coroutineScope.launch {
                                modalBottomSheetState.animateTo(ModalBottomSheetValue.Expanded)
                            }
                        }
                    )
                },
                isFloatingActionButtonDocked = true,
                floatingActionButtonPosition = FabPosition.Center,
                bottomBar = {
                    BottomAppBarView(
                        onBackScreen = {
                        },
                        onNextScreen = {
                        appNavigator.navigateToScorViewScreen()
                        },
                        imageVectorNext = Icons.Filled.ArrowForward,
                    )
                },
                content = { padding ->
                    Column(
                        modifier = Modifier
                            .background(ScoreAppThemeO.colors.background)
                            .fillMaxSize()
                            .padding(
                                top = ScoreAppThemeO.dimens.grid_1_5,
                                start = ScoreAppThemeO.dimens.grid_1_5,
                                end = ScoreAppThemeO.dimens.grid_1_5,
                                bottom = padding.calculateBottomPadding()
                            ),
                    ) {
                        ScorContent(
                            viewModel = viewModel,
                            onRemoveClick = {
                                viewModel.onRemoveScor(it)
                            },
                            onEditClick = {
                                viewModel.onEditScor(it)
                                coroutineScope.launch {
                                    modalBottomSheetState.animateTo(ModalBottomSheetValue.Expanded)
                                }
                            }
                        )
                    }
                }
            )
        }
    }
}

@Composable
fun ScorContent(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier,
    onEditClick: (Scor) -> Unit,
    onRemoveClick: (Scor) -> Unit,
) {
    val viewState = viewModel.container.stateFlow.collectAsState()
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        if (viewState.value.homeModel.scorsList.isEmpty()) {
            EmojiCardHint(
                modifier = Modifier.height(ScoreAppThemeO.dimens.custom_dimens_emoji_card_hint),
                title = stringResource(id = R.string.emoji_card_hint),
            )
            Spacer(modifier = Modifier.height(ScoreAppThemeO.dimens.grid_2))

        } else {
            LazyColumn(
                modifier = Modifier
                    .background(color = Color.Transparent)
                    .padding(all = ScoreAppThemeO.dimens.grid_0_5),
                verticalArrangement = Arrangement.Center
            ) {
                items(viewState.value.homeModel.scorsList) {
                    SelectableCardItem(
                        modifier = Modifier,
                        title = it.asFormattedScor(),
                        emoji = "\uD83C\uDFE0",
                        content = {
                            OverflowMenuView(
                                onEditClick = { onEditClick(it) },
                                onRemoveClick = { onRemoveClick(it) }
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(ScoreAppThemeO.dimens.grid_2))
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun BottomSheetContent(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier,
    title: String,
    onDoneButton: (Int) -> Unit,
    onInputTitle: (String) -> Unit,
    onInputDescription: (String) -> Unit,
    onInputDate: (String) -> Unit,
) {
    val viewState = viewModel.container.stateFlow.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current
    ScorAppTheme() {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(ScoreAppThemeO.colors.background)
                .padding(ScoreAppThemeO.dimens.grid_1_5)
        ) {
            Spacer(modifier = Modifier.height(ScoreAppThemeO.dimens.grid_1))

            Spacer(modifier = Modifier.height(ScoreAppThemeO.dimens.grid_1_5))

            TitleCard(
                modifier = modifier.fillMaxWidth(),
                title = title
            ) {
                InstantDocsTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .imePadding(),
                    value = viewState.value.currentScor.title,
                    onValueChange = onInputTitle,
                    labelText = stringResource(id = R.string.title),
                    helperText = "",
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Characters,
                        autoCorrect = false,
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Text
                    ),
                    keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                    backgroundColor = ScoreAppThemeO.colors.background
                )
                InstantDocsTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .imePadding(),
                    value = viewState.value.currentScor.description,
                    onValueChange = onInputDescription,
                    labelText = stringResource(id = R.string.description),
                    helperText = "",
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Characters,
                        autoCorrect = false,
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Text
                    ),
                    keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                    backgroundColor = ScoreAppThemeO.colors.background
                )

                InstantDocsTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .imePadding(),
                    value = viewState.value.currentScor.date,
                    onValueChange = onInputDate,
                    labelText = stringResource(id = R.string.date),
                    helperText = "",
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Characters,
                        autoCorrect = false,
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Text
                    ),
                    keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                    backgroundColor = ScoreAppThemeO.colors.background
                )

            }
            Spacer(modifier = Modifier.padding(ScoreAppThemeO.dimens.grid_1))
            val index = viewState.value.homeModel.scorsList.indexOf(viewState.value.currentScor)
            CustomButton(
                onClick = {onDoneButton(index)},
                text = stringResource(id = R.string.custom_button),
                buttonType = ButtonType.SECONDARY,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = ScoreAppThemeO.dimens.grid_0_5)
            )
        }
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
            Spacer(modifier = Modifier.height(ScoreAppThemeO.dimens.grid_2))
            content()
        }
    }
}

