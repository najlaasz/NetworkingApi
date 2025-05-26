package com.example.networkingapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

//                    MyPet(pemodifier = Modifier.padding(innerPadding))
                    PetList(modifier = Modifier
                        .padding(innerPadding)
                        )
                }
            }
        }
    }
}
@Composable
fun MyPet(pet: PetDTO, modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .padding(8.dp)

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
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                var text = pet.name ?: " "
                Text(text = text)
                Text(text = pet.gender.toString())
            }
        }
    }
}




@Composable
fun PetList(modifier: Modifier, viewModel: PetViewModel = viewModel()){
    val petList =  viewModel.clientList
    LazyColumn(){
        items(petList) {MyPet(it)  }
    }
}

