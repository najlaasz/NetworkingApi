package com.example.networkingapi.Repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


data class PetDTO(
    val id: Int?,
    val name: String?,
    val age: Int?,
    val adopted: Boolean?,
    val gender: String?,
    val image: String?
)

object RetrofitHelper{
    fun getInstance():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://coded-pets-api-crud.eapi.joincoded.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

interface PetClient {

    @GET("pets")
    suspend fun getAllPets(): List<PetDTO>

}





