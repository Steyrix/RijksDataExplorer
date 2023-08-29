package com.example.rijksdataexplorer.main.item_details

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.rijksdataexplorer.core.data.response.ObjectDetailsDto
import com.example.rijksdataexplorer.main.ui.ErrorView
import com.example.rijksdataexplorer.main.ui.LoadingView
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun ItemDetailsScreen(
    state: ItemDetailsScreenContract.State,
    effectFlow: Flow<ItemDetailsScreenContract.Effect>?,
    onEventSent: (event: ItemDetailsScreenContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: ItemDetailsScreenContract.Effect.Navigation) -> Unit
) {

    LaunchedEffect(true) {
        effectFlow?.onEach { effect ->
            when (effect) {
                ItemDetailsScreenContract.Effect.Navigation.ToOverview -> {
                    onNavigationRequested(ItemDetailsScreenContract.Effect.Navigation.ToOverview)
                }
            }
        }?.collect()
    }

    BackHandler(true) {
        onEventSent(ItemDetailsScreenContract.Event.BackNavigation)
    }

    if (state.isLoading) {
        LoadingView()
    }

    if (state.isError) {
        ErrorView {
            onEventSent(ItemDetailsScreenContract.Event.Refreshing)
        }
    }

    state.item?.let {
        ItemDetails(state = it)
    }
}

@Composable
fun ItemDetails(
    state: ObjectDetailsDto
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = state.principalMaker,
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White,
            modifier = Modifier
                .background(Color.Gray)
                .padding(5.dp)
                .fillMaxWidth()
        )

        AsyncImage(
            model = state.webImage.url,
            contentDescription = state.titles.firstOrNull()
        )

        Text(
            text = state.titles.firstOrNull() ?: "",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = state.description
        )
    }
}