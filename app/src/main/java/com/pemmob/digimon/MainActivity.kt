package com.pemmob.digimon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.pemmob.digimon.ui.navigation.DigimonNavigation
import com.pemmob.digimon.ui.theme.DigimonExplorerTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            DigimonExplorerTheme {

                DigimonNavigation()
            }
        }
    }
}