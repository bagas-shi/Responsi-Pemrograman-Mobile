package com.pemmob.digimon.data.repository

import com.pemmob.digimon.data.model.Digimon
import com.pemmob.digimon.data.remote.DigimonApi
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class DigimonRepository(
    private val api: DigimonApi
) {

    suspend fun getDigimons(
        page: Int = 0,
        pageSize: Int = 20
    ): List<Digimon> {

        return api.getDigimons(
            page = page,
            pageSize = pageSize
        ).content
    }

    suspend fun getDigimonById(
        id: Int
    ): Digimon {

        return api.getDigimonById(id)
    }

    suspend fun getDigimonsWithDetails(
        page: Int = 0,
        pageSize: Int = 20
    ): List<Digimon> = coroutineScope {

        val digimons = getDigimons(
            page = page,
            pageSize = pageSize
        )

        digimons.map { digimon ->

            async {
                try {
                    getDigimonById(digimon.id)
                } catch (e: Exception) {
                    digimon
                }
            }
        }.awaitAll()
    }
}