package com.xaivision.scorapp.navigation

sealed class Destination(val route: String) {
    object HomeScreen : Destination("HomeScreen")
    object ScorsView: Destination("ScorsScreen")
}
