package com.xaivision.scorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.xaivision.scorapp.main.view.HomeScreenView
import com.xaivision.scorapp.navigation.AppNavigator
import com.xaivision.scorapp.navigation.Destination
import com.xaivision.scorapp.personaldata.view.ScorScreenView
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var navController: NavController? = null

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            R.string.app_name
            ScorApp(appNavigator = AppNavigator(rememberAnimatedNavController()))
        }
    }

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    fun ScorApp(appNavigator: AppNavigator) {
        navController = appNavigator.navController
        val startDestination = Destination.HomeScreen.route
        AnimatedNavHost(
            appNavigator.navController,
            startDestination = startDestination
        ) {
            composable(route = Destination.HomeScreen.route) {
                HomeScreenView(appNavigator = appNavigator)
            }
            composable(route = Destination.ScorsView.route) {
                ScorScreenView(scorNavigation = appNavigator)
            }
        }
    }
}
