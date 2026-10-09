package com.pemmob.digimon.viewmodel

import com.pemmob.digimon.data.model.Digimon

sealed interface DigimonUiState {

    data object Loading : DigimonUiState

    data class Success(
        val digimons: List<Digimon>
    ) : DigimonUiState

    data class Error(
        val message: String
    ) : DigimonUiState
}