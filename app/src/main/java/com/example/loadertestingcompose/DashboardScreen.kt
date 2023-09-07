package com.example.loadertestingcompose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun DashboardScreen(sampleText: String?) = Box(
    Modifier
        .fillMaxWidth()
        .fillMaxHeight()
){
    Text(text = sampleText?:"Default")
}