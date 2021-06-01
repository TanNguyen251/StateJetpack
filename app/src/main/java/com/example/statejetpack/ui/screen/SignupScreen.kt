package com.example.statejetpack.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.statejetpack.viewmodel.ProfileViewModel

@Composable
fun NameInput(
    firstName: String,
    lastName: String,
    onFirstNameChange: (String) -> Unit,
    onLastNameChange:(String) -> Unit,
) {
    val defaultModifier = Modifier.padding(10.dp,10.dp)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(
                Color.Cyan
            )
    ) {


        Text(
            "Signup",
            modifier = defaultModifier
                .align(Alignment.CenterHorizontally),
            color = Color.Red,
            fontSize = 30.sp
        )
        TextField(
            value = firstName,
            onValueChange = onFirstNameChange,
            placeholder = { Text(
                text = "First Name",
                fontStyle = FontStyle.Italic
            )},
            modifier = defaultModifier
        )
        TextField(
            value = lastName,
            onValueChange = onLastNameChange,
            placeholder = { Text(
                text = "Last Name",
                fontStyle = FontStyle.Italic
            )},
            modifier = defaultModifier
        )
    }
}

@Composable
fun AgeInput(
    age: String,
    onAgeChange: (String) -> Unit
){
    val defaultModifier = Modifier.padding(10.dp,10.dp)
    TextField(
        value = age,
        onValueChange = onAgeChange,
        placeholder = { Text(
            text = "Age",
            fontStyle = FontStyle.Italic
        )},
        modifier = defaultModifier
    )
}

@Composable
fun SchoolInput(
    school: String,
    onSchoolChange: (String) -> Unit
){
    val defaultModifier = Modifier.padding(10.dp,10.dp)
    TextField(
        value = school,
        onValueChange = onSchoolChange,
        placeholder = { Text(
            text = "School",
            fontStyle = FontStyle.Italic
        )},
        modifier = defaultModifier
    )
}

@Composable
fun SignupScreen(navController: NavHostController, profileViewModel: ProfileViewModel) {
    val firstName: String = profileViewModel.firstName.observeAsState(initial = "").value
    val lastName: String = profileViewModel.lastName.observeAsState(initial = "").value
    val age: String = profileViewModel.age.observeAsState(initial = "").value
    val school: String = profileViewModel.school.observeAsState(initial = "").value
    val signUpSuccessful = remember{mutableStateOf(false)}

    fun navigateToDetails(){
        navController.navigate(Screen.Detail.route)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color.Cyan
            )
    ) {
        NameInput(
            firstName = firstName,
            lastName = lastName,
            onFirstNameChange = { profileViewModel.updateFirstName(it) },
            onLastNameChange = { profileViewModel.updateLastName(it) },
        )
        AgeInput(
            age = age,
            onAgeChange = { profileViewModel.updateAge(it)}
        )
        SchoolInput(
            school = school,
            onSchoolChange = {profileViewModel.updateSchool(it)}
        )
        Button(
            onClick = {
                signUpSuccessful.value = firstName!=""&&lastName!=""&&age!=""&&school!=""
                      },
            modifier = Modifier
                .padding(10.dp, 10.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text("Sign Up")
        }
        if (signUpSuccessful.value){
            Text(
                "You successfully singed up",
                fontStyle = FontStyle.Italic
            )
            Button(
                onClick = { navigateToDetails() },
                modifier = Modifier
                    .padding(10.dp, 10.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text("View Profile")
            }
        }
    }

}