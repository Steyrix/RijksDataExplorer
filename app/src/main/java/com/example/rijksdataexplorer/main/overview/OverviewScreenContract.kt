package com.example.rijksdataexplorer.main.overview

import com.example.rijksdataexplorer.core.data.response.ArtObjectDto
import com.example.rijksdataexplorer.core.view.ViewEffect
import com.example.rijksdataexplorer.core.view.ViewEvent
import com.example.rijksdataexplorer.core.view.ViewState

class OverviewScreenContract {

    enum class ListState {
        IDLE,
        LOADING,
        PAGINATING,
        PAGINATING_EXHAUST,
        ERROR
    }

    data class State(
        val items: List<ArtObjectDto>,
        val currentPage: Int,
        val isError: Boolean,
        val isLoading: Boolean,
        val canPaginate: Boolean = false,
        val listState: ListState = ListState.IDLE
    ) : ViewState

    sealed class Event : ViewEvent {

        object Refreshing : Event()

        object Paginate : Event()

        data class ItemSelection(val itemId: String) : Event()
    }

    sealed class Effect : ViewEffect {

        sealed class Navigation : Effect() {
            data class ToItemDetails(val itemId: String) : Navigation()
        }
    }
}