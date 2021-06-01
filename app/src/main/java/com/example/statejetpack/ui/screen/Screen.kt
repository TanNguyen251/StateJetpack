package com.example.statejetpack.ui.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen (
    val route: String,
    val label: String,
    val icon: ImageVector
        ){
    object Greeting: Screen("greeting","NameInput", Icons.Default.Home)
    object Detail: Screen("details","Details",Icons.Default.Person)
}