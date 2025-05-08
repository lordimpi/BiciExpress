package edu.unicauca.apimovil.biciexpress.network

import edu.unicauca.apimovil.biciexpress.model.Bicycle
import edu.unicauca.apimovil.biciexpress.model.LoginRequest
import edu.unicauca.apimovil.biciexpress.model.LoginResponse
import edu.unicauca.apimovil.biciexpress.model.RegisterRequest
import retrofit2.http.*
import retrofit2.Response

interface ApiService {
    // --- Auth ---
    @POST("api/Auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("api/Auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<Void>

    // --- Bicycles ---
    @GET("api/bicycles")
    suspend fun getAllBicycles(): Response<List<Bicycle>>

    @GET("api/bicycles/available")
    suspend fun getAvailableBicycles(): Response<List<Bicycle>>

    @GET("api/bicycles/mine")
    suspend fun getMyBicycles(@Header("Authorization") token: String): Response<List<Bicycle>>

    @GET("api/bicycles/{id}")
    suspend fun getBicycleById(@Path("id") id: Int): Response<Bicycle>

    @POST("api/bicycles")
    suspend fun createBicycle(
        @Header("Authorization") token: String,
        @Body bicycle: Bicycle
    ): Response<Void>

    @PUT("api/bicycles/{id}")
    suspend fun updateBicycle(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body bicycle: Bicycle
    ): Response<Void>

    @DELETE("api/bicycles/{id}")
    suspend fun deleteBicycle(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<Void>

    @PUT("api/bicycles/rent/{id}")
    suspend fun rentBicycle(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<Void>

    @PUT("api/bicycles/return/{id}")
    suspend fun returnBicycle(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<Void>
}