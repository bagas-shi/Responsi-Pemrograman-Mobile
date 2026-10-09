package com.pemmob.digimon.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pemmob.digimon.viewmodel.DetailUiState
import com.pemmob.digimon.viewmodel.DigimonViewModel
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import coil.compose.AsyncImage

@Composable
fun DetailScreen(
    digimonId: Int,
    onBackClick: () -> Unit,
    viewModel: DigimonViewModel = viewModel()
) {

    val detailState by viewModel.detailState.collectAsState()

    LaunchedEffect(digimonId) {
        viewModel.getDigimonDetail(digimonId)
    }

    when (val state = detailState) {

        // =========================
        // LOADING
        // =========================
        is DetailUiState.Loading -> {

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                CircularProgressIndicator()

                Text(
                    text = "Memuat data...",
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }

        // =========================
        // SUCCESS
        // =========================
        is DetailUiState.Success -> {

            val digimon = state.digimon

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Detail Digimon",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                AsyncImage(
                    model = digimon.image,
                    contentDescription = digimon.name,
                    modifier = Modifier
                        .size(220.dp)
                        .clip(
                            RoundedCornerShape(16.dp)
                        )
                )

                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                Text(
                    text = digimon.name,
                    style = MaterialTheme.typography.headlineLarge
                )

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {

                        Text(
                            text = "Level",
                            style = MaterialTheme.typography.titleLarge
                        )

                        Text(
                            text = digimon.level,
                            style = MaterialTheme.typography.bodyLarge
                        )

                        Spacer(
                            modifier = Modifier.height(16.dp)
                        )

                        Text(
                            text = "Attribute",
                            style = MaterialTheme.typography.titleLarge
                        )

                        Text(
                            text = digimon.attribute,
                            style = MaterialTheme.typography.bodyLarge
                        )

                        Spacer(
                            modifier = Modifier.height(16.dp)
                        )

                        Text(
                            text = "Type",
                            style = MaterialTheme.typography.titleLarge
                        )

                        Text(
                            text = digimon.type,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                Button(
                    onClick = onBackClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Kembali"
                    )
                }
            }
        }
        // =========================
        // ERROR
        // =========================
        is DetailUiState.Error -> {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "Gagal mengambil data",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(
                    text = state.message
                )

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                Button(
                    onClick = onBackClick
                ) {
                    Text(
                        text = "Kembali"
                    )
                }
            }
        }
    }
}