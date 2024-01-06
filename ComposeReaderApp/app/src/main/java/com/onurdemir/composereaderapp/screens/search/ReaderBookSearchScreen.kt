package com.onurdemir.composereaderapp.screens.search

import android.annotation.SuppressLint
import android.renderscript.ScriptGroup.Input
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.onurdemir.composereaderapp.components.InputField
import com.onurdemir.composereaderapp.components.ReaderAppBar
import com.onurdemir.composereaderapp.model.MBook
import com.onurdemir.composereaderapp.navigation.ReaderScreens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController, viewModel: BookSearchViewModel = hiltViewModel()) {
    
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                            viewModel) {query ->
                        viewModel.searchBooks(query)

                }
                Spacer(modifier = Modifier.height(15.dp))
                BookList(navController, viewModel)
            }
        }
    }
    
}

@Composable
fun BookList(navController: NavController, viewModel: BookSearchViewModel) {

    if (viewModel.listOfBooks.value.loading == true) {
        Log.d("BOO", "BookList: loading...")
        CircularProgressIndicator()
    }else {
        Log.d("BOO", "BookList: ${viewModel.listOfBooks.value.data}")

    }

    Log.d("TAG", "BookList: ${viewModel.listOfBooks.value.data}")

    val listOfBooks = listOf(
        MBook(id = "asd", title = "hello again", authors = "all of us", notes = null),
        MBook(id = "asd", title = "hello", authors = "all of us", notes = null),
        MBook(id = "asd", title = "hello world", authors = "all of us", notes = null),
        MBook(id = "asd", title = "hello android", authors = "all of us", notes = null))

    LazyColumn(modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(15.dp)) {

        items(items = listOfBooks) {book ->
            BookRow(book, navController)
        }

    }
    
}

@Composable
fun BookRow(book: MBook,
            navController: NavController) {
    
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .padding(5.dp)
        .clickable {},
        shape = RectangleShape) {
        
        Row(modifier = Modifier.padding(5.dp),
        verticalAlignment = Alignment.Top) {

            val imageUrl = "http://books.google.com/books/content?id=73RpCQAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
            Image(painter = rememberImagePainter(data = imageUrl),
                contentDescription = "book image",
            modifier = Modifier
                .width(80.dp)
                .padding(end = 5.dp))

            Column {
                Text(text = book.title.toString(), overflow = TextOverflow.Ellipsis)
                Text(text = "Author: ${book.authors}", overflow = TextOverflow.Clip)
            }
        }

    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchForm(
    modifier: Modifier = Modifier,
    viewModel: BookSearchViewModel,
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