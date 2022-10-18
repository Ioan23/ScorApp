package com.xaivision.scorapp.main.viewmodel

import com.xaivision.scorapp.main.model.models.Scor


sealed class HomeAction

object NextActionClick: HomeAction()
data class SelectedScorActionClick(val scor: Scor) : HomeAction()

