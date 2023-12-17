package com.onurdemir.composereaderapp.screens.search

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.onurdemir.composereaderapp.components.ReaderAppBar
import com.onurdemir.composereaderapp.navigation.ReaderScreens

@Preview
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController = NavController(LocalContext.current)) {
    
    Scaffold(topBar = {
        ReaderAppBar(title = "Search Books",
            icon = Icons.Default.ArrowBack,
            navController = navController,
            showProfile = false) {
            navController.navigate(ReaderScreens.ReaderHomeScreen.name)
        }
    }) {

    }
    
}