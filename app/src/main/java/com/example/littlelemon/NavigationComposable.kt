package com.example.littlelemon

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MyNavigation(){
    val navController = rememberNavController()

    val context = LocalContext.current

    val firstName = remember {
        val sharedPref = context.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)
        sharedPref.getString("first_name", "") ?: ""
    }

    val lastName = remember {
        val sharedPref = context.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)
        sharedPref.getString("last_name", "") ?: ""
    }

    val email = remember {
        val sharedPref = context.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)
        sharedPref.getString("email", "") ?: ""
    }

    val hayDatos = email != "" && firstName != "" && lastName != ""

    NavHost(navController = navController, startDestination =  if(hayDatos ) Home.route else Onboarding.route){
        composable(Home.route){
            HomeScreen(navController)
        }
        composable(Profile.route){
            ProfileScreen(navController)
        }
        composable(Onboarding.route){
            OnboardingScreen(navController)
        }
    }
}