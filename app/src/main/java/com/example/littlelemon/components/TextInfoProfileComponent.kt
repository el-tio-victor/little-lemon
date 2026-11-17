package com.example.littlelemon.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextInfoProfileComponent(
    modifier: Modifier = Modifier,
    text: String,
){
    Text(
        text = text ,
        fontSize = 16.sp,
        modifier = Modifier.padding(horizontal = 10.dp)
    )
}
