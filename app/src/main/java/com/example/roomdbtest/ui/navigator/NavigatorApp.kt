package com.example.roomdbtest.ui.navigator

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.roomdbtest.ui.datakontak.DetailKontakScreen
import com.example.roomdbtest.ui.datakontak.EditKontakDestination
import com.example.roomdbtest.ui.datakontak.EditKontakScreen
import com.example.roomdbtest.ui.datakontak.InsertKontakDestination
import com.example.roomdbtest.ui.datakontak.InsertKontakScreen
import com.example.roomdbtest.ui.datakontak.ItemDetailsDestination
import com.example.roomdbtest.ui.home.HomeDestination
import com.example.roomdbtest.ui.home.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigatorApp(
    navHostController: NavHostController = rememberNavController()
) {

    NavHost(
        navController = navHostController,
        startDestination = HomeDestination.route
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToItemEntry = { navHostController.navigate(InsertKontakDestination.route) },
                navigateToItemUpdate = {
                    navHostController.navigate(
                        "${ItemDetailsDestination.route}/${it}"
                    )

                }
            )

        }
        composable(route = InsertKontakDestination.route) {
            InsertKontakScreen(
                onNavigateBack = { navHostController.navigateUp() }
            )
        }

        composable(route = ItemDetailsDestination.routeWithArgs,
            arguments = listOf(
                navArgument(ItemDetailsDestination.itemIdArg) {
                    type = NavType.IntType
                }
            )
        ) {
            DetailKontakScreen(
                navigateToEditItem = {
                    navHostController.navigate("${EditKontakDestination.route}/$it")
                },
                onBack = { navHostController.popBackStack() }


            )
        }

        composable(route = EditKontakDestination.routeWithArgs,
            arguments = listOf(
                navArgument(EditKontakDestination.itemIdArg) {
                    type = NavType.IntType
                }
            )
        ) {
            EditKontakScreen(navigateBack = { /*TODO*/ }, onNavigateUp = {
                navHostController.popBackStack()
            })
        }
    }

}