package com.example.littlelemon

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.edit
import androidx.navigation.NavHostController
import com.example.littlelemon.components.ContainerGreenComponent
import com.example.littlelemon.components.HeaderComponent

@Composable
fun OnboardingScreen(navController: NavHostController) {
    var email by remember { mutableStateOf<String>("") }
    var name by remember { mutableStateOf<String>("") }
    var lastName by remember { mutableStateOf<String>("") }

    val esValido = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    val hayError = email.isNotEmpty() && !esValido

    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderComponent()
        ContainerGreenComponent() {
            Text(
                text = "Let's get to know you",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium
            )
        }

        OutlinedTextField(
            value = name,
            onValueChange = { name = it},
            label={Text("First Name")},
            modifier = Modifier.fillMaxWidth()
                .background(color = Color.White)
                .padding(12.dp)
        )
        OutlinedTextField(
            value = lastName,
            onValueChange = {lastName = it},
            label={Text("Last Name")},
            modifier = Modifier.fillMaxWidth()
                .background(color = Color.White)
                .padding(12.dp)
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it},
            isError = hayError,
            singleLine = true,
            supportingText = {
                if (hayError) Text("Invalid email")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            label={Text("Email")},
            modifier = Modifier.fillMaxWidth()
                .background(color = Color.White)
                .padding(12.dp)
        )


        Button (
            onClick = {
                if(name.isBlank() || lastName.isBlank() || email.isBlank()){
                    Toast.makeText(context,"register fail, please fill all values.", Toast.LENGTH_LONG).show()
                }
                else{

                    val sharedPref = context.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)
                    sharedPref.edit{
                        putString("first_name",name)
                        putString("last_name",lastName)
                        putString("email",email)
                    }
                    Toast.makeText(context,"register success!!", Toast.LENGTH_LONG).show()

                    navController.navigate(Home.route)
                }
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
            Text(
                text = "Register",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

    }
}


