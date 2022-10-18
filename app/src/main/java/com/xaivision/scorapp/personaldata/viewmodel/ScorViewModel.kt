package com.xaivision.scorapp.personaldata.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.xaivision.scorapp.personaldata.view.NavigateBack
import com.xaivision.scorapp.personaldata.view.ScorSideEffect
import com.xaivision.scorapp.personaldata.view.ScorDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ScorViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ContainerHost<ScorDataState, ScorSideEffect>, ViewModel() {

    override val container = container<ScorDataState, ScorSideEffect>(
        initialState = ScorDataState(""),
        savedStateHandle = savedStateHandle
    ) {
        loadData()
    }

    fun loadData() {
        //todo load personal data
    }

    fun onAction(action: ScorAction) {
        when (action) {
            is BackActionClick -> {
                navigateBack()
            }
            is InputTitleActionClick -> {
                onTitleInput(action.input)
            }
            is InputDateActionClick-> {
                onDateInput(action.input)
            }
            is InputDescriptionActionClick -> {
                onDescriptionScoreInput(action.input)
            }
        }
    }

    fun onTitleInput(title: String) = intent {
        reduce {
            state.copy(
                title = title
            )
        }
    }

    fun onDescriptionScoreInput(description: String) = intent {
        reduce {
            state.copy(
                description = description
            )
        }
    }

    fun onDateInput(date: String) = intent {
        reduce {
            state.copy(
                date = date
            )
        }
    }

    fun navigateBack() = intent {
        postSideEffect(NavigateBack)
    }
}
