package com.example.littlelemon.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ContainerGreenComponent(
    modifier: Modifier = Modifier,
    content: @Composable ()-> Unit
){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer )
            .padding(vertical = 36.dp, horizontal = 16.dp),
        contentAlignment = Alignment.Center
        ){
        content()
    }
}