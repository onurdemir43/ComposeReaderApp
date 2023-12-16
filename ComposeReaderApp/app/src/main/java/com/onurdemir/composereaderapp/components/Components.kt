package com.onurdemir.composereaderapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LibraryBooks
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.google.firebase.auth.FirebaseAuth
import com.onurdemir.composereaderapp.model.MBook
import com.onurdemir.composereaderapp.navigation.ReaderScreens

@Composable
fun ReaderLogo(modifier: Modifier = Modifier) {
    Text(
        text = "Reader App",
        modifier = modifier.padding(bottom = 16.dp),
        fontSize = 40.sp,
        color = Color.Red.copy(alpha = 0.5f)
    )
}

@Composable
fun EmailInput(
    modifier: Modifier = Modifier,
    emailState: MutableState<String>,
    labelId: String = "Email",
    enabled: Boolean = true,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    InputField(
        modifier = modifier,
        valueState = emailState,
        labelId = labelId,
        enabled = enabled,
        keyboardType = KeyboardType.Email,
        imeAction = imeAction,
        onAction = onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    labelId: String,
    enabled: Boolean,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(value = valueState.value, onValueChange = {
        valueState.value = it },
        label = { Text(text = labelId)},
        singleLine = isSingleLine,
        textStyle = TextStyle(fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onBackground),
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType,
            imeAction = imeAction),
        keyboardActions = onAction)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInput(
    modifier: Modifier,
    passwordState: MutableState<String>,
    labelId: String,
    enabled: Boolean,
    passwordVisibility: MutableState<Boolean>,
    imeAction: ImeAction = ImeAction.Done,
    onAction: KeyboardActions = KeyboardActions.Default
) {

    val visualTransformation = if (passwordVisibility.value) {
        VisualTransformation.None
    }else {
        PasswordVisualTransformation()
    }

    OutlinedTextField(value = passwordState.value, onValueChange = {
        passwordState.value = it
    },
        label = { Text(text = labelId)},
        singleLine = true,
        textStyle = TextStyle(fontSize = 20.sp, color = MaterialTheme.colorScheme.onBackground),
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction),
        visualTransformation = visualTransformation,
        trailingIcon = {PasswordVisibility(passwordVisibility = passwordVisibility)},
        keyboardActions = onAction)
}

@Composable
fun PasswordVisibility(passwordVisibility: MutableState<Boolean>) {

    val visible = passwordVisibility.value
    IconButton(onClick = {passwordVisibility.value = !visible}) {
        Icons.Default.Close
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
                text = book.title.toString(),
                modifier = Modifier.padding(5.dp),
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(text = book.authors.toString(),
                modifier = Modifier.padding(5.dp),
                fontSize = 15.sp)


            RoundedButton(label = "Reading", radius = 70)
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