package com.onurdemir.composereaderapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.onurdemir.composereaderapp.screens.ReaderSplashScreen
import com.onurdemir.composereaderapp.screens.details.BookDetailsScreen
import com.onurdemir.composereaderapp.screens.home.Home
import com.onurdemir.composereaderapp.screens.login.ReaderLoginScreen
import com.onurdemir.composereaderapp.screens.search.BookSearchViewModel
import com.onurdemir.composereaderapp.screens.search.SearchScreen
import com.onurdemir.composereaderapp.screens.stats.ReaderStatsScreen
import com.onurdemir.composereaderapp.screens.update.BookUpdateScreen

@Composable
fun ReaderNavigation() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ReaderScreens.SplashScreen.name) {

        composable(ReaderScreens.SplashScreen.name) {
            ReaderSplashScreen(navController = navController)
        }

        composable(ReaderScreens.ReaderHomeScreen.name) {
            Home(navController = navController)
        }

        composable(ReaderScreens.SearchScreen.name) {
            val viewModel = hiltViewModel<BookSearchViewModel>()
            SearchScreen(navController = navController, viewModel)
        }

        composable(ReaderScreens.DetailScreen.name) {
            BookDetailsScreen(navController = navController)
        }

        composable(ReaderScreens.LoginScreen.name) {
            ReaderLoginScreen(navController = navController)
        }

        composable(ReaderScreens.UpdateScreen.name) {
            BookUpdateScreen(navController = navController)
        }

        composable(ReaderScreens.ReaderStatsScreen.name) {
            ReaderStatsScreen(navController = navController)
        }
    }



}