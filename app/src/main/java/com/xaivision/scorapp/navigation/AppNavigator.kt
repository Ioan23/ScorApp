package com.xaivision.scorapp.navigation

import androidx.annotation.VisibleForTesting
import androidx.navigation.NavHostController
import com.xaivision.scorapp.main.viewmodel.HomeNavigation
import com.xaivision.scorapp.personaldata.viewmodel.ScorNavigation

open class AppNavigator constructor(
    val navController: NavHostController,
) : HomeNavigation, ScorNavigation {

    override fun moveBack() {
        navigateBack()
    }

    override fun navigateToScorViewScreen() {
        navigateTo(Destination.ScorsView)
    }

    private fun navigateTo(
        destination: Destination,
        singleTop: Boolean = false,
        vararg args: Pair<String, String?>
    ) {
        navControllerNavigateTo(destination, singleTop, *args)
    }

    private fun navigateBack() {
        navControllerPopBackStack()
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    open fun navControllerNavigateTo(
        destination: Destination,
        singleTop: Boolean,
        vararg args: Pair<String, String?>
    ) {
        navController.navigate(destination.route) {
            launchSingleTop = singleTop
        }
        putArgsIfNecessary(*args)
    }

    private fun putArgsIfNecessary(vararg args: Pair<String, String?>) {
        if (args.isNotEmpty()) {
            navController.currentBackStackEntry?.arguments.apply {
                args.forEach { (key: String, arg: String?) ->
                    this?.putString(key, arg)
                }
            }
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    open fun navControllerPopBackStack() {
        navController.popBackStack()
    }
}
