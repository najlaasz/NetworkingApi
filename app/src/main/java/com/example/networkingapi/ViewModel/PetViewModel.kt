package com.example.networkingapi.ViewModel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.networkingapi.Repository.PetClient
import com.example.networkingapi.Repository.PetDTO
import com.example.networkingapi.Repository.RetrofitHelper
import kotlinx.coroutines.launch

class PetViewModel:  ViewModel() {

    init {
        viewModelScope.launch{
            getAllPet()
        }
    }

    var clientList by mutableStateOf(emptyList<PetDTO>())




    suspend fun getAllPet(){

        val client = RetrofitHelper.getInstance().create<PetClient>(PetClient::class.java)
        clientList = client.getAllPets()


    }
}