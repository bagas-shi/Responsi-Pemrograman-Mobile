package com.pemmob.digimon.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pemmob.digimon.ui.component.DigimonCard
import com.pemmob.digimon.viewmodel.DigimonUiState
import com.pemmob.digimon.viewmodel.DigimonViewModel

import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun HomeScreen(
    onDigimonClick: (Int) -> Unit,
    viewModel: DigimonViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    when (val state = uiState) {

        // =========================
        // LOADING
        // =========================
        is DigimonUiState.Loading -> {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .safeDrawingPadding(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                CircularProgressIndicator()

                Text(
                    text = "Memuat data Digimon...",
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }

        // =========================
        // SUCCESS
        // =========================
        is DigimonUiState.Success -> {

            var searchQuery by remember {
                mutableStateOf("")
            }

            val filteredDigimons = remember(
                state.digimons,
                searchQuery
            ) {

                state.digimons.filter { digimon ->

                    digimon.name.contains(
                        searchQuery,
                        ignoreCase = true
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .safeDrawingPadding()
                    .padding(horizontal = 16.dp)
            ) {

                // HEADER
                Text(
                    text = "Digimon Explorer",
                    style = MaterialTheme.typography.headlineLarge
                )

                Text(
                    text = "Jelajahi berbagai Digimon",
                    style = MaterialTheme.typography.bodyLarge
                )

                // SEARCH
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = {
                        searchQuery = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    shape = RoundedCornerShape(20.dp),
                    label = {
                        Text("Cari Digimon")
                    },
                    placeholder = {
                        Text("Masukkan nama Digimon")
                    },
                    singleLine = true
                )

                // GRID
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    items(
                        items = filteredDigimons,
                        key = { digimon ->
                            digimon.id
                        }
                    ) { digimon ->

                        DigimonCard(
                            digimon = digimon,
                            onClick = {
                                onDigimonClick(digimon.id)
                            }
                        )
                    }
                }
            }
        }

        // =========================
        // ERROR
        // =========================
        is DigimonUiState.Error -> {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .safeDrawingPadding()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "Gagal mengambil data",
                    style = MaterialTheme.typography.headlineMedium
                )

                Text(
                    text = state.message,
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
        }
    }
}