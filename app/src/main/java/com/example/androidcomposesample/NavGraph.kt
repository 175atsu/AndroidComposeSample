package com.example.androidcomposesample

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController


object MainDestinations {
    const val ONBOARDING_ROUTE = "onboarding"
    const val COURSES_ROUTE = "courses"
    const val COURSE_DETAIL_ROUTE = "course"
    const val COURSE_DETAIL_ID_KEY = "courseId"
}

@Composable
fun NavGraph(startDestination: String = MainDestinations.ONBOARDING_ROUTE) {
    val navController = rememberNavController()
    val actions = remember(navController) { MainActions(navController) }

    NavHost(navController = navController, startDestination = startDestination) {
        composable(MainDestinations.ONBOARDING_ROUTE) {
            Playground()
        }
    }
}

class MainActions(navController: NavHostController) {
    val onboardingComplete: () -> Unit = {
        navController.navigate(MainDestinations.COURSES_ROUTE)
    }
    val selectCourse: (Long) -> Unit = { courseId: Long ->
        navController.navigate("${MainDestinations.COURSE_DETAIL_ROUTE}/$courseId")
    }
    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}