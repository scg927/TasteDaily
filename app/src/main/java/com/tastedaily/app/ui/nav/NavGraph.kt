package com.tastedaily.app.ui.nav

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tastedaily.app.ui.browse.BrowseScreen
import com.tastedaily.app.ui.calendar.CalendarScreen
import com.tastedaily.app.ui.detail.DetailScreen
import com.tastedaily.app.ui.immersive.ImmersiveCookingScreen
import com.tastedaily.app.ui.meal.MealDetailScreen
import com.tastedaily.app.ui.shopping.ShoppingListScreen
import com.tastedaily.app.ui.video.VideoScreen

private data class BottomTab(
    val route: String,
    val label: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
)

private val bottomTabs = listOf(
    BottomTab(Routes.CALENDAR, "日历", Icons.Default.CalendarMonth),
    BottomTab(Routes.BROWSE, "菜谱", Icons.Default.MenuBook),
)

@Composable
fun TasteDailyNavGraph() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    val showBottomBar = currentRoute in bottomTabs.map { it.route }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    bottomTabs.forEach { tab ->
                        val selected = backStackEntry?.destination?.hierarchy?.any { it.route == tab.route } == true
                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                navController.navigate(tab.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = { Icon(tab.icon, contentDescription = tab.label) },
                            label = { Text(tab.label) },
                        )
                    }
                }
            }
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.CALENDAR,
            modifier = Modifier.fillMaxSize().padding(innerPadding),
        ) {
            composable(Routes.CALENDAR) {
                CalendarScreen(
                    onMealClick = { date, mealType -> navController.navigate(Routes.mealDetail(date, mealType)) },
                    onDishClick = { id -> navController.navigate(Routes.detail(id)) },
                )
            }

            composable(Routes.BROWSE) {
                BrowseScreen(
                    onDishClick = { id -> navController.navigate(Routes.detail(id)) },
                )
            }

            composable(Routes.HOME) {
                CalendarScreen(
                    onMealClick = { date, mealType -> navController.navigate(Routes.mealDetail(date, mealType)) },
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
                    onShoppingList = { id -> navController.navigate(Routes.shoppingList(id)) },
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

            composable(
                route = Routes.MEAL_DETAIL,
                arguments = listOf(
                    navArgument("date") { type = NavType.StringType },
                    navArgument("mealType") { type = NavType.StringType },
                ),
            ) { backStackEntry ->
                val date = backStackEntry.arguments?.getString("date").orEmpty()
                val mealType = backStackEntry.arguments?.getString("mealType").orEmpty()
                MealDetailScreen(
                    dateStr = date,
                    mealTypeStr = mealType,
                    onBack = { navController.popBackStack() },
                    onDishClick = { id -> navController.navigate(Routes.detail(id)) },
                )
            }

            composable(
                route = Routes.SHOPPING_LIST,
                arguments = listOf(navArgument("dishId") { type = NavType.StringType }),
            ) { backStackEntry ->
                val dishId = backStackEntry.arguments?.getString("dishId").orEmpty()
                ShoppingListScreen(
                    dishId = dishId,
                    onBack = { navController.popBackStack() },
                )
            }
        }
    }
}
