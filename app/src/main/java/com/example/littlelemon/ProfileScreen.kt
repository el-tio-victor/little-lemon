package com.example.littlelemon

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.components.HeaderComponent
import com.example.littlelemon.components.LabelInfoProfileComponent
import com.example.littlelemon.components.TextInfoProfileComponent

@Composable
fun ProfileScreen(navController: NavHostController) {
    val context = LocalContext.current
    val sharedPref = context.getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE)
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(20.dp)
    ) {
        HeaderComponent()
        Text(
            text = "Personal information",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF495E57),
            modifier =  Modifier.padding(vertical = 10.dp)

        )
        LabelInfoProfileComponent(text = "Name")
        TextInfoProfileComponent(text =  sharedPref.getString("first_name","")?:"")

        LabelInfoProfileComponent(text = "Last Name")
        TextInfoProfileComponent(text =  sharedPref.getString("last_name","")?:"")

        LabelInfoProfileComponent(text = "Email")
        TextInfoProfileComponent(text =  sharedPref.getString("email","")?:"")

        Button(
            onClick = {
                sharedPref.edit{

                    putString("first_name","")
                    putString("last_name","")
                    putString("email","")
                }
                navController.navigate(Onboarding.route)
            },
            modifier = Modifier
                    .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp)
                .height(48.dp),
            shape = RoundedCornerShape(12.dp),                 // Border radius
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF4CE14),            // Amarillo Little Lemon
                contentColor = Color(0xFF333333)               // Texto gris oscuro
            )
        ) {
            Text(text = "LOG OUT")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview(){
    ProfileScreen(navController = rememberNavController())
}