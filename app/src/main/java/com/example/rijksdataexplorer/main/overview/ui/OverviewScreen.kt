package com.example.rijksdataexplorer.main.overview.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.rijksdataexplorer.core.data.response.ArtObjectDto
import com.example.rijksdataexplorer.main.overview.OverviewScreenContract
import com.example.rijksdataexplorer.main.ui.ErrorView
import com.example.rijksdataexplorer.main.ui.LoadingView
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

private const val LIMIT_OFFSET = 5
private const val DEFAULT_VALUE = -10

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OverviewScreen(
    state: OverviewScreenContract.State,
    effectFlow: Flow<OverviewScreenContract.Effect>?,
    onEventSent: (event: OverviewScreenContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: OverviewScreenContract.Effect.Navigation) -> Unit
) {

    LaunchedEffect(true) {
        effectFlow?.onEach { effect ->
            when (effect) {
                is OverviewScreenContract.Effect.Navigation.ToItemDetails -> onNavigationRequested(
                    effect
                )
            }
        }?.collect()
    }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isLoading,
        onRefresh = { onEventSent(OverviewScreenContract.Event.Refreshing) }
    )

    Box(
        modifier = Modifier.pullRefresh(pullRefreshState)
    ) {
        ArtObjectsList(state, onEventSent)

        if (state.isLoading) {
            LoadingView()
        }

        if (state.isError) {
            ErrorView {
                onEventSent(OverviewScreenContract.Event.Refreshing)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ArtObjectsList(
    state: OverviewScreenContract.State,
    onEventSent: (event: OverviewScreenContract.Event) -> Unit
) {
    val listState = rememberLazyListState()

    val lastVisibleIdx = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: DEFAULT_VALUE
    val limitIdx = listState.layoutInfo.totalItemsCount - LIMIT_OFFSET

    val shouldStartPaginate = state.canPaginate && (lastVisibleIdx >= limitIdx)

    LaunchedEffect(key1 = shouldStartPaginate) {
        if (
            shouldStartPaginate
            && state.listState == OverviewScreenContract.ListState.IDLE
        ) {
            onEventSent(OverviewScreenContract.Event.Paginate)
        }
    }

    val dataItems = state.items.groupBy { it.principalOrFirstMaker }

    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxHeight()
    ) {
        dataItems.forEach { (author, artObjects) ->
            stickyHeader {
                Text(
                    text = author,
                    color = Color.White,
                    modifier = Modifier
                        .background(Color.Gray)
                        .padding(5.dp)
                        .fillMaxWidth()
                )
            }

            items(
                count = artObjects.size,
                key = { index -> artObjects[index].objectNumber },
                contentType = { index -> artObjects[index] }
            ) {
                ArtObjectViewHolder(
                    data = artObjects[it],
                    onItemClick = { artObject ->
                        onEventSent(
                            OverviewScreenContract.Event.ItemSelection(artObject.objectNumber)
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun ArtObjectViewHolder(
    data: ArtObjectDto,
    onItemClick: (ArtObjectDto) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(PaddingValues(8.dp))
            .clip(shape = RoundedCornerShape(8.dp, 8.dp, 0.dp, 0.dp))
            .background(Color.LightGray)
            .clickable { onItemClick(data) }
    ) {
        AsyncImage(
            model = data.headerImage.url,
            contentDescription = null
        )
        Text(
            modifier = Modifier.height(75.dp),
            text = data.title
        )
    }
}