package com.pemmob.digimon.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pemmob.digimon.data.model.Digimon

@Composable
fun DigimonCard(
    digimon: Digimon,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
        shape = RoundedCornerShape(16.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // =========================
            // GAMBAR DIGIMON
            // =========================
            AsyncImage(
                model = digimon.image,
                contentDescription = digimon.name,
                modifier = Modifier
                    .size(120.dp)
                    .clip(
                        RoundedCornerShape(12.dp)
                    )
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            // =========================
            // NAMA
            // =========================
            Text(
                text = digimon.name,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            // =========================
            // LEVEL
            // =========================
            Text(
                text = "Level: ${digimon.level}",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )

            // =========================
            // ATTRIBUTE
            // =========================
            Text(
                text = "Attribute: ${digimon.attribute}",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )

            // =========================
            // TYPE
            // =========================
            Text(
                text = "Type: ${digimon.type}",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}