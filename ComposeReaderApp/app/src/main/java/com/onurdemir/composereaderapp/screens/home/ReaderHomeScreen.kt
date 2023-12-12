package com.onurdemir.composereaderapp.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.LibraryBooks
import androidx.compose.material.icons.filled.Logout
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
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

    }) {
        //content
        Surface(modifier = Modifier.fillMaxSize()) {
            //home content
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
                                  tint = Color.LightGray,
                                  modifier = Modifier
                                      .clip(RoundedCornerShape(12.dp))
                                      .scale(0.9f)

                              )
                          }
                          Text(text = title, color = Color.Red.copy(alpha = 0.7f),
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
                      Icon(imageVector = Icons.Default.Logout, contentDescription = "Logout")

                  }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFFFFF8F0)))


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
