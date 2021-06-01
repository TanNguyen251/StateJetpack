package com.example.statejetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.*
import com.example.statejetpack.ui.screen.ProfileScreen
import com.example.statejetpack.ui.screen.SignupScreen
import com.example.statejetpack.ui.screen.Screen
import com.example.statejetpack.viewmodel.ProfileViewModel
import com.example.statejetpack.ui.theme.StateJetpackTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StateJetpackTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    AppNavigator()
                }
            }
        }
    }
}
@Composable
fun AppNavigator(){
    val navController = rememberNavController()
    val profileViewModel = ProfileViewModel()
    val items = listOf(
        Screen.Greeting,
        Screen.Detail,
    )
    Scaffold(
        bottomBar = {
            BottomNavigation{
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon= { Icon(imageVector = screen.icon, contentDescription = screen.label)},
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigate(screen.route){
                                popUpTo(navController.graph.startDestinationRoute!!){
                                    inclusive = false
                                }
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.Greeting.route)
        {
            composable(Screen.Greeting.route){
                SignupScreen(navController, profileViewModel = profileViewModel)
            }
            composable(Screen.Detail.route){
                ProfileScreen(navController,profileViewModel = profileViewModel)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StateJetpackTheme {
        AppNavigator()
    }
}
