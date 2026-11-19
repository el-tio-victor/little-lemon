package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.components.ContainerGreenComponent
import com.example.littlelemon.components.HeaderComponent
import com.example.littlelemon.components.MenuItemsList


@Composable
fun HomeScreen(navController: NavHostController, database: MenuDatabase) {
    val menuItems by database.menuDao().getAllMenuItems()
        .observeAsState( emptyList())

    var phraseSearch  by remember { mutableStateOf<String>("") }
    var categoryFilterSelect  by remember { mutableStateOf<String>("") }
    val categoryFilter= menuItems.distinctBy{ item -> item.category}

    Column(
        modifier = Modifier.fillMaxSize()

    ) {
        HeaderComponent(showBtnProfile = true, navController = navController)
        ContainerGreenComponent(){
            Column() {
                Text(text = "Little Lemon ",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Row() {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = "Chicago",
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.White,
                            )
                        Text(
                            text = "We are a family  owned Mediterranean restaurant, focused on traditional recipes served with a modern twist",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White,
                        )
                    }
                    Image(
                        painter = painterResource(id = R.drawable.hero),
                        contentDescription = "Hero image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.padding(start = 5.dp)
                            .size(150.dp)
                            .clip(RoundedCornerShape(20.dp))

                    )
                }
                BasicTextField(
                    value = phraseSearch,
                    onValueChange = {
                        phraseSearch = it
                        categoryFilterSelect = ""
                                    },
                    singleLine = true,
                    textStyle = TextStyle(fontSize = 14.sp, color = Color.Black),

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                        .height(40.dp)
                        .background(Color.White, RoundedCornerShape(12.dp))
                        .border(1.dp, Color.Gray, RoundedCornerShape(12.dp))
                        .padding(horizontal = 12.dp),
                    decorationBox = { innerTextField ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.baseline_search_24),
                                contentDescription = null,
                                tint = Color.Gray,
                                modifier = Modifier.size(20.dp)
                            )

                            Box(modifier = Modifier.weight(1f)) {
                                if (phraseSearch.isEmpty()) Text("Enter phrase to search...", color = Color.Gray, fontSize = 14.sp)
                                innerTextField()
                            }
                        }
                    }
                )
            }
        }
        Text(
            text = "ORDER FOR DELIVERY",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start=10.dp, top=20.dp)
        )
        Row() {
            categoryFilter.forEach { item -> Text(
                text = item.category,
                color = Color.White,
                modifier = Modifier
                    .padding(horizontal=3.dp, vertical = 12.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(if(categoryFilterSelect == item.category) MaterialTheme.colorScheme.primary else Color(0xFFCCCCCC))
                    .clickable {
                        if(categoryFilterSelect == item.category){
                            categoryFilterSelect = ""
                            return@clickable
                        }
                        categoryFilterSelect = item.category
                        phraseSearch = ""
                    }
                    .padding(horizontal = 8.dp, vertical = 5.dp)
            ) }
        }
        MenuItemsList(items = if(phraseSearch.isEmpty() && categoryFilterSelect.isEmpty()) menuItems else  menuItems.filter {
           if(phraseSearch.isNotEmpty())
               it.title.contains( phraseSearch, ignoreCase = true)
            else
               it.category.contains( categoryFilterSelect, ignoreCase = true)
        })
    }
}

/*@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen(navController= rememberNavController(), database = MenuDatabase)
}*/
