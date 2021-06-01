package com.example.statejetpack.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.statejetpack.R
import com.example.statejetpack.viewmodel.ProfileViewModel

@Composable
fun StudentProfile(
    firstName: String,
    lastName: String,
    age: String,
    school: String,
    navController: NavHostController
){
    val defaultModifier = Modifier.padding(10.dp, 10.dp)
    fun navigateToGreeting(){
        navController.navigate(Screen.Greeting.route)
    }
    Column(modifier = Modifier.background(Color.Gray)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Cyan, RoundedCornerShape(15.dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = defaultModifier
                    .align(Alignment.Start)
            ) {
                IconButton(onClick = { navigateToGreeting() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back to Sign up"
                    )
                }
            }
            val image = painterResource(id = R.drawable.tan)
            Image(
                painter = image,
                contentDescription = "Tan",
                modifier = defaultModifier
                    .size(100.dp)
                    .clip(CircleShape)
            )

            Text(
                text = "$firstName $lastName",
                modifier = defaultModifier,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Age: $age",
                modifier = defaultModifier
            )
            Text(
                text = "School: $school",
                modifier = defaultModifier,
                fontStyle = FontStyle.Italic,
                fontSize = 20.sp
            )
            Row(
                modifier = defaultModifier
            ) {
                Text(
                    text = "Friends:",
                    modifier = defaultModifier
                )
                Text(
                    text = "GPA:",
                    modifier = defaultModifier
                )
                Text(
                    text = "Courses Taken:",
                    modifier = defaultModifier
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
        ) {
            Image(
                painter = painterResource(id = R.drawable.tan),
                contentDescription = "Tan",
                modifier = defaultModifier.size(150.dp,300.dp)
            )
        }
    }
}

@Composable
fun ProfileScreen(navController: NavHostController, profileViewModel: ProfileViewModel){
    val firstName: String = profileViewModel.firstName.observeAsState(initial = "").value
    val lastName: String = profileViewModel.lastName.observeAsState(initial = "").value
    val age: String = profileViewModel.age.observeAsState(initial = "").value
    val school: String = profileViewModel.school.observeAsState(initial = "").value
    StudentProfile(firstName, lastName, age, school, navController)

}