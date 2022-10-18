package com.xaivision.scorapp.personaldata.viewmodel

sealed class ScorAction

object BackActionClick: ScorAction()
data class InputTitleActionClick(val input: String): ScorAction()
data class InputDescriptionActionClick(val input: String): ScorAction()
data class InputDateActionClick(val input: String): ScorAction()
