package com.example.rijksdataexplorer.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rijksdataexplorer.core.di.AppComponent
import com.example.rijksdataexplorer.core.util.daggerViewModel
import com.example.rijksdataexplorer.main.Navigation.Args.ITEM_ID
import com.example.rijksdataexplorer.main.Navigation.Error.ERROR_MSG
import com.example.rijksdataexplorer.main.item_details.ItemDetailsScreenDestination
import com.example.rijksdataexplorer.main.item_details.di.DaggerItemDetailsScreenComponent
import com.example.rijksdataexplorer.main.overview.OverviewScreenDestination
import com.example.rijksdataexplorer.main.overview.di.DaggerOverviewScreenComponent

@Composable
fun RijksDataExplorerApp(
    appComponent: AppComponent
) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Navigation.Routes.OVERVIEW,
    ) {
        composable(
            route = Navigation.Routes.OVERVIEW
        ) {
            val component = DaggerOverviewScreenComponent
                .builder()
                .appComponent(appComponent)
                .build()

            val viewModel = daggerViewModel {
                component.getViewModel()
            }

            OverviewScreenDestination(navController, viewModel)
        }

        composable(
            route = Navigation.Routes.ITEM_DETAILS,
            arguments = listOf(
                navArgument(ITEM_ID) {
                    type = NavType.StringType
                }
            )
        ) {
            val itemId = requireNotNull(it.arguments?.getString(ITEM_ID)) { ERROR_MSG }

            val component = DaggerItemDetailsScreenComponent
                .builder()
                .appComponent(appComponent)
                .build()

            val viewModel = daggerViewModel {
                component.getViewModel()
            }

            ItemDetailsScreenDestination(navController, viewModel, itemId)
        }
    }
}

object Navigation {

    object Args {
        const val ITEM_ID = "item_id"
    }

    object Routes {
        const val OVERVIEW = "overview"
        const val ITEM_DETAILS = "$OVERVIEW/{$ITEM_ID}"
    }

    object Error {
        const val ERROR_MSG = "Item id is required for navigation"
    }
}

fun NavController.navigateToItem(
    itemId: String
) {
    navigate(
        route = "${Navigation.Routes.OVERVIEW}/$itemId"
    )
}