package com.tastedaily.app.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tastedaily.app.ui.detail.DetailScreen
import com.tastedaily.app.ui.home.HomeScreen
import com.tastedaily.app.ui.immersive.ImmersiveCookingScreen
import com.tastedaily.app.ui.video.VideoScreen

@Composable
fun TasteDailyNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.HOME) {

        composable(Routes.HOME) {
            HomeScreen(
                onDishClick = { id -> navController.navigate(Routes.detail(id)) },
            )
        }

        composable(
            route = Routes.DETAIL,
            arguments = listOf(navArgument("dishId") { type = NavType.StringType }),
        ) { backStackEntry ->
            val dishId = backStackEntry.arguments?.getString("dishId").orEmpty()
            DetailScreen(
                dishId = dishId,
                onBack = { navController.popBackStack() },
                onStartImmersive = { navController.navigate(Routes.immersive(dishId)) },
                onPlayVideo = { videoId -> navController.navigate(Routes.video(videoId)) },
            )
        }

        composable(
            route = Routes.IMMERSIVE,
            arguments = listOf(navArgument("dishId") { type = NavType.StringType }),
        ) { backStackEntry ->
            val dishId = backStackEntry.arguments?.getString("dishId").orEmpty()
            ImmersiveCookingScreen(
                dishId = dishId,
                onExit = { navController.popBackStack() },
            )
        }

        composable(
            route = Routes.VIDEO,
            arguments = listOf(navArgument("videoId") { type = NavType.StringType }),
        ) { backStackEntry ->
            val videoId = backStackEntry.arguments?.getString("videoId").orEmpty()
            VideoScreen(
                videoId = videoId,
                onBack = { navController.popBackStack() },
            )
        }
    }
}
