package com.example.littlelemon.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import com.example.littlelemon.Profile
import com.example.littlelemon.R

@Composable
fun HeaderComponent(
    modifier: Modifier = Modifier,
    navController: NavHostController? = null,
    showBtnProfile: Boolean = false
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo), // Tu recurso
            contentDescription = "Logo de Little Lemon",
            modifier = Modifier
                .align(Alignment.Center)
                .height(50.dp) // Controla la altura sin deformar el ancho
                .fillMaxWidth(0.6f), // Ocupa el 60% del ancho de la pantalla
            contentScale = ContentScale.Fit // Evita que la imagen se recorte o estire mal
        )
        if(showBtnProfile && navController != null)
        IconButton(
            onClick = {
                navController.navigate(Profile.route)
            },
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Icon(painter = painterResource(id= R.drawable.ic_profile), contentDescription = "Icon profile")
        }
    }
}