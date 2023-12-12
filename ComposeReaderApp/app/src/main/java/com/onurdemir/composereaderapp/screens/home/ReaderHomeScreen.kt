package com.onurdemir.composereaderapp.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.LibraryBooks
import androidx.compose.material.icons.filled.Logout
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
import com.google.firebase.auth.FirebaseAuth
import com.onurdemir.composereaderapp.model.MBook
import com.onurdemir.composereaderapp.navigation.ReaderScreens

@Preview
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavController = NavController(LocalContext.current)) {
    Scaffold(topBar = {
                      ReaderAppBar(title = "Reader App", navController = navController)
    },
        floatingActionButton = {
            
            FABContent{}

    }) {
        //content
        Surface(modifier = Modifier.fillMaxSize()) {
            //home content
            HomeContent(navController)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReaderAppBar(
    title: String,
    showProfile: Boolean = true,
    navController: NavController
) {

    TopAppBar(title = {
                      Row(verticalAlignment = Alignment.CenterVertically) {
                          if (showProfile) {
                              Icon(
                                  imageVector = Icons.Default.LibraryBooks,
                                  contentDescription = "Logo Icon",
                                  tint = Color(0xFF92CBDF).copy(alpha = 0.6f),
                                  modifier = Modifier
                                      .clip(RoundedCornerShape(12.dp))
                                      .scale(0.9f)

                              )
                          }
                          Text(text = title, color = Color.Red.copy(alpha = 0.6f),
                          style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
                          )
                      }
    },
        actions = {
                  IconButton(onClick = {
                      FirebaseAuth.getInstance().signOut().run {
                          navController.navigate(ReaderScreens.LoginScreen.name)
                      }
                  }) {
                      Icon(imageVector = Icons.Default.Logout,
                          contentDescription = "Logout",
                          tint = Color(0xFF92CBDF).copy(alpha = 0.6f))

                  }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.LightGray.copy(alpha = 0.1f)))


}
@Composable
fun HomeContent(navController: NavController) {

    val currentUser = if (!FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()) {
        FirebaseAuth.getInstance().currentUser?.email?.split('@')?.get(0)
    }else {
        "N/A"
    }

    Column(modifier = Modifier.padding(2.dp), verticalArrangement = Arrangement.SpaceEvenly) {
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
fun TitleSection(modifier: Modifier = Modifier, label: String) {
    Surface(modifier.padding(start = 4.dp, top = 2.dp)) {
        Column {
            Text(text = label,
            fontSize = 20.sp,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Left)
        }
    }
}

@Composable
fun ReadingBookRightNow(books: List<MBook>, navController: NavController) {

}

@Composable
fun FABContent(onTap: () -> Unit) {
    FloatingActionButton(onClick = {onTap()},
        shape = RoundedCornerShape(50.dp),
        containerColor = Color(0xFF92CBDF)) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add a Book",
                tint = Color.White
            )
    }

}
