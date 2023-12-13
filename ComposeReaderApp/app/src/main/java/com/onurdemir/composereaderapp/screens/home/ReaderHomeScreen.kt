package com.onurdemir.composereaderapp.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import com.onurdemir.composereaderapp.components.FABContent
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
                ListCard()

            }
        })
    
}


@Composable
fun HomeContent(navController: NavController) {

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
    }
}



@Composable
fun ReadingBookRightNow(books: List<MBook>, navController: NavController) {

}

@Preview
@Composable
fun ListCard(book: MBook = MBook("asd","running","me", "hello world"),
onPressDetails: (String) -> Unit = {}) {

    val context = LocalContext.current

    val resources = context.resources

    val displayMetrics = resources.displayMetrics

    val screenWidth = displayMetrics.widthPixels / displayMetrics.density

    val spacing = 10.dp


    Card(
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .padding(15.dp)
            .height(250.dp)
            .width(200.dp)
            .clickable {
                onPressDetails.invoke(book.title.toString())
            }) {

        Column(modifier = Modifier.width(screenWidth.dp - (spacing * 2)),
        horizontalAlignment = Alignment.Start) {

            Row(horizontalArrangement = Arrangement.Center) {

                Image(painter = rememberImagePainter(data = "http://books.google.com/books/content?id=73RpCQAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"),
                    contentDescription = "book image",
                modifier = Modifier
                    .height(140.dp)
                    .width(100.dp)
                    .padding(5.dp))

                Column(modifier = Modifier.padding(25.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {

                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        tint = Color.Black,
                        contentDescription = "Fav Icon",
                        modifier = Modifier.padding(bottom = 2.dp)
                    )

                    BookRating(score = 4.5)


                }
            }

                    Text(
                        text = "Book Title",
                        modifier = Modifier.padding(5.dp),
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(text = "Authors: All...",
                        modifier = Modifier.padding(5.dp),
                        fontSize = 15.sp)


                RoundedButton(label = "Reading", radius = 70)
            }
        }
    }





@Composable
fun BookRating(score: Double = 4.5) {
        Surface(modifier = Modifier
            .height(70.dp)
            .padding(5.dp),
        shape = RoundedCornerShape(55.dp),
            shadowElevation = 5.dp,
            color = Color.White) {

            Column(modifier = Modifier.padding(5.dp)) {
                Icon(imageVector = Icons.Default.StarBorder, contentDescription = "Star",
                modifier = Modifier.padding(2.dp))
                Text(text = score.toString(), fontSize = 15.sp)
            }

        }

}

@Composable
fun RoundedButton(
    label: String,
    radius: Int = 30,
    onPress: () -> Unit = {}) {
    
    Surface(
        modifier = Modifier.clip(
            RoundedCornerShape(
                bottomStartPercent = radius,
                topEndPercent = radius)),
        color = Color(0xFF92CBDF)) {
            
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .width(90.dp)
                    .heightIn(40.dp)
                    .clickable { onPress.invoke() }) {
                Text(text = label, color = Color.White, fontSize = 15.sp)
        }
        
    }

}


