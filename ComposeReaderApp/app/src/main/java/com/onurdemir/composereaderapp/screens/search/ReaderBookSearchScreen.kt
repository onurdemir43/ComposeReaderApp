package com.onurdemir.composereaderapp.screens.search

import android.annotation.SuppressLint
import android.renderscript.ScriptGroup.Input
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.onurdemir.composereaderapp.components.InputField
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
            navController.popBackStack()
        }
    }) {
        Surface(modifier = Modifier.padding(top = 60.dp)) {
            Column {
                SearchForm(
                    modifier = Modifier.fillMaxWidth().padding(10.dp)
                )
            }
        }
    }
    
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchForm(
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    hint: String = "Search",
    onSearch: (String) -> Unit = {}) {
        Column {
            val searchQueryState = rememberSaveable { mutableStateOf("") }
            val keyboardController = LocalSoftwareKeyboardController.current
            val valid = remember(searchQueryState.value) {
                searchQueryState.value.trim().isNotEmpty()
            }
            InputField(
                valueState = searchQueryState,
                labelId = "Search",
                enabled = true,
                onAction = KeyboardActions {
                    if (!valid) return@KeyboardActions
                    onSearch(searchQueryState.value.trim())
                    searchQueryState.value = ""
                    keyboardController?.hide()
                })

        }
}