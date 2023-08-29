package com.example.rijksdataexplorer.main.overview

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.rijksdataexplorer.main.navigateToItem
import com.example.rijksdataexplorer.main.overview.ui.OverviewScreen

@Composable
fun OverviewScreenDestination(
    navController: NavController,
    viewModel: OverviewViewModel
) {
    OverviewScreen(
        state = viewModel.getState().value,
        effectFlow = viewModel.currentEffect,
        onEventSent = { event -> viewModel.onEventReceived(event) },
        onNavigationRequested = { navigationEffect ->  
            if (navigationEffect is OverviewScreenContract.Effect.Navigation.ToItemDetails) {
                navController.navigateToItem(navigationEffect.itemId)
            }
        }
    )
}