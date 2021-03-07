package com.example.androidcomposesample

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController

object MainDestinations {
    const val PLAYGROUND_ROUTE = "playground"
    const val LIST_PAGE_ROUTE = "listPage"
}

@Composable
fun NavGraph(startDestination: String = MainDestinations.PLAYGROUND_ROUTE) {
    val navController = rememberNavController()
    val actions = remember(navController) { MainActions(navController) }

    NavHost(navController = navController, startDestination = startDestination) {
        composable(MainDestinations.PLAYGROUND_ROUTE) {
            Playground(actionToList = actions.toListPage)
        }
        composable(MainDestinations.LIST_PAGE_ROUTE) {
            BasicsPage()
        }
    }
}

class MainActions(navController: NavHostController) {
    val toListPage: () -> Unit = {
        navController.navigate(MainDestinations.LIST_PAGE_ROUTE)
    }
}