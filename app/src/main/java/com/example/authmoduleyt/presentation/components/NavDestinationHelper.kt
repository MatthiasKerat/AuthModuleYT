package com.example.authmoduleyt.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun NavDestinationHelper(
    shouldNavigate:()->Boolean,
    destination:()->Unit
) {
    LaunchedEffect(key1 = shouldNavigate()){
        if(shouldNavigate()){
            destination()
        }
    }
}