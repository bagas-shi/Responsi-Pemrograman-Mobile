package com.pemmob.digimon.data.remote

import com.pemmob.digimon.data.model.Digimon
import com.pemmob.digimon.data.model.DigimonListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DigimonApi {

    @GET("digimon")
    suspend fun getDigimons(
        @Query("page") page: Int = 0,
        @Query("pageSize") pageSize: Int = 20
    ): DigimonListResponse

    @GET("digimon/{id}")
    suspend fun getDigimonById(
        @Path("id") id: Int
    ): Digimon
}