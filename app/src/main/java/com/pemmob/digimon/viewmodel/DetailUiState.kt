package com.pemmob.digimon.viewmodel

import com.pemmob.digimon.data.model.Digimon

sealed interface DetailUiState {

    data object Loading : DetailUiState

    data class Success(
        val digimon: Digimon
    ) : DetailUiState

    data class Error(
        val message: String
    ) : DetailUiState
}