package com.xaivision.scorapp.main.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.xaivision.scorapp.main.model.models.HomeModel
import com.xaivision.scorapp.main.model.models.Scor
import com.xaivision.scorapp.main.model.repository.HomeRepository
import com.xaivision.scorapp.main.view.HomeSideEffect
import com.xaivision.scorapp.main.view.HomeState
import com.xaivision.scorapp.main.view.NavigateToPersonalData
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val mainRepository: HomeRepository,
): ContainerHost<HomeState, HomeSideEffect>, ViewModel() {

    override val container = container<HomeState, HomeSideEffect>(
        initialState = HomeState(
            homeModel = HomeModel(),
            currentScor = Scor()
            ),
        savedStateHandle = savedStateHandle
    ) {
        loadData()
    }

    private fun loadData() = intent {
        val scorList = mainRepository.fetchScorList()
        reduce {
            state.copy(
                homeModel = state.homeModel.copy(
                    scorsList = scorList
                )
            )
        }
    }

//    fun onAction(action: MainAction) {
//        when (action) {
//            is NextActionClick -> { moveToPersonalDataScreen() }
//            is SelectedCarActionClick -> onCarSelected(action.car)
//        }
//    }

    fun moveToPersonalDataScreen() = intent {
        postSideEffect(NavigateToPersonalData)
    }




    fun onScorSelected(scor: Scor) = intent{
        reduce {
            state.copy(
                homeModel = state.homeModel.copy(
                    selectedScor = scor
                )
            )
        }
    }


    fun onInputTitle(title: String) = intent {
        reduce {
            state.copy(
                currentScor = state.currentScor.copy(
                    title = title
                )
            )
        }
    }

    fun onInputDescription(description: String) = intent {
        reduce {
            state.copy(
                currentScor = state.currentScor.copy(
                    description = description
                )
            )
        }
    }

    fun onInputDate(date: String) = intent {
        reduce {
            state.copy(
                currentScor = state.currentScor.copy(
                    date = date
                )
            )
        }
    }


    fun onScorCreated(index: Int) = intent {
        reduce {
            val workPlaceList = state.homeModel.scorsList.toMutableList()
                .apply {
                    if (!contains(state.currentScor)) {
                        if(index != -1) {
                            add(index, state.currentScor)
                        } else {
                            add(state.currentScor)
                        }
                    }
                }
            state.copy(
                homeModel = state.homeModel.copy(
                    scorsList = workPlaceList,
                ),
                currentScor = Scor()
            )
        }
    }

    fun onRemoveScor(scor: Scor) = intent {
        val scorList = state.homeModel.scorsList
        val deleteScor = scorList.toMutableList()
        reduce {
            state.copy(
                homeModel = state.homeModel.copy(
                    scorsList = deleteScor.apply { remove(scor) }
                )
            )
        }
    }

    fun onEditScor(scor: Scor) = intent {
        val scorList = state.homeModel.scorsList.toMutableList()
        reduce {
            state.copy(
                currentScor =  scor,
//                homeModel = state.homeModel.copy(
//                    scorsList = scorList.apply { remove(scor) }
//                )
            )
        }
    }
}

