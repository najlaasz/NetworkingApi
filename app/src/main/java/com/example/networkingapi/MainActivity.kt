package com.example.networkingapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.networkingapi.Repository.PetDTO
import com.example.networkingapi.ViewModel.PetViewModel
import com.example.networkingapi.ui.theme.NetworkingApiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NetworkingApiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(colorResource(R.color.purple_500))
                            .padding(innerPadding)
                    ) {
                        PetList()
                    }
                }
            }
        }
    }



//    val LocalCustomColors = staticCompositionLocalOf {
//        CustomColorsProvider(Color.Black, Color.White)
//    }
}

@Composable
fun MyPet(pet: PetDTO, modifier: Modifier = Modifier) {

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.Gray // White card background
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            AsyncImage(
                model = pet.image,
                contentDescription = null,
                placeholder = painterResource(R.drawable.loading),
                error = painterResource(R.drawable.error),
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                val name = pet.name ?: "Unknown"
                Text(text = name)
                Text(text = "Gender: ${pet.gender}")
            }
        }
    }
}

@Composable
fun PetList(viewModel: PetViewModel = viewModel()) {
    val petList = viewModel.clientList

    
    LazyColumn {
        items(petList) { pet ->
            MyPet(pet = pet)
        }
    }
}