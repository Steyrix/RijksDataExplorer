package com.example.rijksdataexplorer.main.item_details

import com.example.rijksdataexplorer.core.data.response.ObjectDetailsDto
import com.example.rijksdataexplorer.core.view.ViewEffect
import com.example.rijksdataexplorer.core.view.ViewEvent
import com.example.rijksdataexplorer.core.view.ViewState

class ItemDetailsScreenContract {

    data class State(
        val item: ObjectDetailsDto?,
        val isError: Boolean,
        val isLoading: Boolean
    ) : ViewState

    sealed class Event : ViewEvent {

        object Refreshing : Event()

        object BackNavigation : Event()
    }

    sealed class Effect : ViewEffect {

        sealed class Navigation : Effect() {
            object ToOverview : Navigation()
        }
    }
}