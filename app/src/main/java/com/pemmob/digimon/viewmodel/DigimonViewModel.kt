package com.pemmob.digimon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pemmob.digimon.data.remote.RetrofitInstance
import com.pemmob.digimon.data.repository.DigimonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DigimonViewModel : ViewModel() {

    private val repository =
        DigimonRepository(
            RetrofitInstance.api
        )

    private val _uiState =
        MutableStateFlow<DigimonUiState>(
            DigimonUiState.Loading
        )

    val uiState: StateFlow<DigimonUiState> =
        _uiState.asStateFlow()

    private val _detailState =
        MutableStateFlow<DetailUiState>(
            DetailUiState.Loading
        )

    val detailState: StateFlow<DetailUiState> =
        _detailState.asStateFlow()

    init {
        getDigimons()
    }

    private fun getDigimons() {

        viewModelScope.launch {

            _uiState.value =
                DigimonUiState.Loading

            try {

                val digimons = repository.getDigimonsWithDetails(
                    page = 0,
                    pageSize = 20
                )

                _uiState.value =
                    DigimonUiState.Success(
                        digimons = digimons
                    )

            } catch (e: Exception) {

                _uiState.value =
                    DigimonUiState.Error(
                        message =
                            e.message ?: "Terjadi kesalahan"
                    )
            }
        }
    }

    fun getDigimonDetail(id: Int) {

        viewModelScope.launch {

            _detailState.value =
                DetailUiState.Loading

            try {

                val digimon =
                    repository.getDigimonById(id)

                _detailState.value =
                    DetailUiState.Success(
                        digimon = digimon
                    )

            } catch (e: Exception) {

                _detailState.value =
                    DetailUiState.Error(
                        message =
                            e.message
                                ?: "Terjadi kesalahan"
                    )
            }
        }
    }
}