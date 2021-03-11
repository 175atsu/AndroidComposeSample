package com.example.androidcomposesample

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.androidcomposesample.MainDestinations.INDEX_LABEL_KEY
import com.example.androidcomposesample.grid.GridDetailPage
import com.example.androidcomposesample.grid.GridPage

object MainDestinations {
    const val PLAYGROUND_ROUTE = "playground"
    const val BASICS_PAGE_ROUTE = "basisPage"
    const val LIST_PAGE_ROUTE = "listPage"
    const val GRID_PAGE_ROUTE = "gridPage"
    const val GRID_DETAIL_PAGE_ROUTE = "gridDetailPage"
    const val INDEX_LABEL_KEY = "indexLabel"
}

@Composable
fun NavGraph(startDestination: String = MainDestinations.PLAYGROUND_ROUTE) {
    val navController = rememberNavController()
    val actions = remember(navController) { MainActions(navController) }

    NavHost(navController = navController, startDestination = startDestination) {
        composable(MainDestinations.PLAYGROUND_ROUTE) {
            Playground(
                actionToBasics = actions.toBasicsPage,
                actionToList = actions.toListPage,
                actionToGrid = actions.toGridPage
            )
        }
        composable(MainDestinations.BASICS_PAGE_ROUTE) {
            BasicsPage()
        }
        composable(MainDestinations.LIST_PAGE_ROUTE) {
            ListPage()
        }
        composable(
            MainDestinations.GRID_PAGE_ROUTE,
        ) {
            GridPage(actions.toGridDetailPage)
        }
        composable(
            "${MainDestinations.GRID_DETAIL_PAGE_ROUTE}/{$INDEX_LABEL_KEY}",
            arguments = listOf(navArgument(INDEX_LABEL_KEY) { type = NavType.StringType })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            GridDetailPage(arguments)
        }
    }
}

class MainActions(navController: NavHostController) {
    val toBasicsPage: () -> Unit = {
        navController.navigate(MainDestinations.BASICS_PAGE_ROUTE)
    }
    val toListPage: () -> Unit = {
        navController.navigate(MainDestinations.LIST_PAGE_ROUTE)
    }
    val toGridPage: () -> Unit = {
        navController.navigate(MainDestinations.GRID_PAGE_ROUTE)
    }
    val toGridDetailPage: (String) -> Unit = { label: String ->
        navController.navigate("${MainDestinations.GRID_DETAIL_PAGE_ROUTE}/$label")
    }
}