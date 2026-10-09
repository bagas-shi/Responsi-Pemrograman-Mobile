package com.pemmob.digimon.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pemmob.digimon.ui.screen.DetailScreen
import com.pemmob.digimon.ui.screen.HomeScreen

@Composable
fun DigimonNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable(
            route = "home"
        ) {
            HomeScreen(
                onDigimonClick = { digimonId ->
                    navController.navigate(
                        "detail/$digimonId"
                    )
                }
            )
        }

        composable(
            route = "detail/{digimonId}",
            arguments = listOf(
                navArgument("digimonId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val digimonId =
                backStackEntry.arguments?.getInt("digimonId") ?: 0

            DetailScreen(
                digimonId = digimonId,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}