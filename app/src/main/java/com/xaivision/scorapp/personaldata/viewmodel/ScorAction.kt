package com.xaivision.scorapp.personaldata.viewmodel

sealed class ScorAction

object BackActionClick: ScorAction()
data class InputFirstNameActionClick(val input: String): ScorAction()
data class InputSecondNameActionClick(val input: String): ScorAction()
