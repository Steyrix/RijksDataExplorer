package com.example.rijksdataexplorer.main.overview

import androidx.lifecycle.viewModelScope
import com.example.rijksdataexplorer.core.data.repository.RijksRepository
import com.example.rijksdataexplorer.core.data.response.CollectionResponse
import com.example.rijksdataexplorer.core.view.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val PAGE_SIZE = 20

class OverviewViewModel
@Inject constructor(
    private val repository: RijksRepository
) : BaseViewModel<
    OverviewScreenContract.State,
    OverviewScreenContract.Event,
    OverviewScreenContract.Effect
    >() {

    init {
        getItems(1)
    }

    private fun getItems(page: Int) {
        viewModelScope.launch {
            val currState = getState().value
            val isIdle = currState.listState == OverviewScreenContract.ListState.IDLE

            if (page == 1 || currState.canPaginate && isIdle) {

                val newListState = if (page == 1) {
                    OverviewScreenContract.ListState.LOADING
                } else {
                    OverviewScreenContract.ListState.PAGINATING
                }

                setState {
                    copy(
                        isError = false,
                        isLoading = page == 1,
                        listState = newListState
                    )
                }

                repository
                    .getData(page)
                    .onFailure { handleFailure(page) }
                    .onSuccess { handleSuccess(it, page) }

            }
        }
    }

    private fun handleFailure(page: Int) {
        setState {

            var listState = OverviewScreenContract.ListState.PAGINATING_EXHAUST
            var canPaginate = false
            var isError = false

            if (page == 1) {
                listState = OverviewScreenContract.ListState.ERROR
                canPaginate = true
                isError = true
            }

            copy(
                isError = isError,
                isLoading = false,
                listState = listState,
                canPaginate = canPaginate
            )
        }
    }

    private fun handleSuccess(
        response: CollectionResponse,
        page: Int
    ) {
        setState {
            val updatedItems = if (page == 1) {
                response.artObjects
            } else {
                items.toMutableList().apply {
                    addAll(response.artObjects)
                }.distinctBy { it.objectNumber }
            }

            val canPaginate = response.artObjects.size == PAGE_SIZE
            val newPage = if (canPaginate) { page.inc() } else page

            copy(
                items = updatedItems,
                currentPage = newPage,
                isError = false,
                isLoading = false,
                listState = OverviewScreenContract.ListState.IDLE,
                canPaginate = canPaginate
            )
        }
    }

    override fun setInitialState(): OverviewScreenContract.State {
        return OverviewScreenContract.State(
            items = emptyList(),
            currentPage = 1,
            isError = false,
            isLoading = true,
            listState = OverviewScreenContract.ListState.LOADING,
            canPaginate = false
        )
    }

    override fun onEventReceived(event: OverviewScreenContract.Event) {
        when (event) {
            is OverviewScreenContract.Event.Refreshing -> {
                setState {
                    copy(currentPage = 1)
                }
                getItems(getState().value.currentPage)
            }
            is OverviewScreenContract.Event.Paginate -> {
                getItems(getState().value.currentPage)
            }
            is OverviewScreenContract.Event.ItemSelection -> {
                applyEffect {
                    OverviewScreenContract.Effect.Navigation.ToItemDetails(event.itemId)
                }
            }
        }
    }
}