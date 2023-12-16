package com.onurdemir.composereaderapp.screens.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LibraryBooks
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.google.firebase.auth.FirebaseAuth
import com.onurdemir.composereaderapp.components.BookRating
import com.onurdemir.composereaderapp.components.FABContent
import com.onurdemir.composereaderapp.components.ListCard
import com.onurdemir.composereaderapp.components.ReaderAppBar
import com.onurdemir.composereaderapp.components.TitleSection
import com.onurdemir.composereaderapp.model.MBook
import com.onurdemir.composereaderapp.navigation.ReaderScreens


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavController) {


        Scaffold(topBar = {
            ReaderAppBar(title = "Reader App", navController = navController)
        },
            floatingActionButton = {

                FABContent{}

            },
        content = {
            //content
            Column(modifier = Modifier.fillMaxSize()) {
                //home content
                HomeContent(navController)

            }
        })

}


@Composable
fun HomeContent(navController: NavController) {

    val listOfBooks = listOf(
                MBook(id = "asd", title = "hello again", authors = "all of us", notes = null),
                MBook(id = "asd", title = "hello", authors = "all of us", notes = null),
    MBook(id = "asd", title = "hello world", authors = "all of us", notes = null),
    MBook(id = "asd", title = "hello android", authors = "all of us", notes = null)

    )

    val currentUser = if (!FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()) {
        FirebaseAuth.getInstance().currentUser?.email?.split('@')?.get(0)
    }else {
        "N/A"
    }

    Column(modifier = Modifier.padding(top = 65.dp, bottom = 5.dp, start = 5.dp, end = 5.dp), verticalArrangement = Arrangement.Top) {
        Row(modifier = Modifier.align(alignment = Alignment.Start)) {
            TitleSection(label = "Your reading \n" + "activity right now...")

            Spacer(modifier = Modifier.fillMaxWidth(0.7f))

            Column {
                Icon(imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Profile",
                    tint = Color(0xFF92CBDF).copy(alpha = 0.6f),
                modifier = Modifier
                    .size(45.dp)
                    .clickable {
                        navController.navigate(ReaderScreens.ReaderStatsScreen.name)
                    }
                )
                Text(text = currentUser!!,
                modifier = Modifier.padding(2.dp),
                color = Color.Red.copy(alpha = 0.6f),
                fontSize = 15.sp,
                maxLines = 1,
                overflow = TextOverflow.Clip)
                Divider()
            }
        }
        ReadingBookRightNow(books = listOf(), navController = navController)

        TitleSection(label = "Reading List")

        BookListArea(listOfBooks = listOfBooks, navController)
    }
}



@Composable
fun ReadingBookRightNow(books: List<MBook>, navController: NavController) {
    ListCard()


}

@Composable
fun BookListArea(listOfBooks: List<MBook>, navController: NavController) {

    HorizontalScrollableComponent(listOfBooks) {
        Log.d("TAG", "BookListArea: $it")
        //on card clicked navigate to details
    }
}

@Composable
fun HorizontalScrollableComponent(listOfBooks: List<MBook>, onCardPressed: (String) -> Unit) {

    val scrollState = rememberScrollState()
    
    Row(modifier = Modifier
        .fillMaxWidth()
        .heightIn(280.dp)
        .horizontalScroll(scrollState)) {

        for (book in listOfBooks) {
            ListCard(book) {
                onCardPressed(it)
            }
        }

    }
}




