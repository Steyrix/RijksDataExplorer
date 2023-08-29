package com.example.rijksdataexplorer.main.item_details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.example.rijksdataexplorer.main.item_details.ItemDetailsScreen
import com.example.rijksdataexplorer.main.item_details.ItemDetailsScreenContract
import com.example.rijksdataexplorer.main.item_details.ItemDetailsViewModel

@Composable
fun ItemDetailsScreenDestination(
    navController: NavController,
    viewModel: ItemDetailsViewModel,
    itemId: String
) {

    val hasSetNumber = remember {
        mutableStateOf(false)
    }

    if (!hasSetNumber.value) {
        viewModel.setObjectNumber(itemId)
        hasSetNumber.value = true
    }

    ItemDetailsScreen(
        state = viewModel.getState().value,
        effectFlow = viewModel.currentEffect,
        onEventSent = { event -> viewModel.onEventReceived(event) },
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is ItemDetailsScreenContract.Effect.Navigation.ToOverview) {
                navController.popBackStack()
            }
        }
    )
}