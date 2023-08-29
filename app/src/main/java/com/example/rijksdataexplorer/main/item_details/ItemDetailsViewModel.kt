package com.example.rijksdataexplorer.main.item_details

import androidx.lifecycle.viewModelScope
import com.example.rijksdataexplorer.core.data.repository.RijksRepository
import com.example.rijksdataexplorer.core.data.response.CollectionDetailsResponse
import com.example.rijksdataexplorer.core.view.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class ItemDetailsViewModel
@Inject constructor(
    private val repository: RijksRepository
) : BaseViewModel<
    ItemDetailsScreenContract.State,
    ItemDetailsScreenContract.Event,
    ItemDetailsScreenContract.Effect
    >() {

    private var objectNumber: String = ""

    fun setObjectNumber(itemId: String) {
        objectNumber = itemId
        getDetailsData()
    }

    private fun getDetailsData() {
        viewModelScope.launch {
            setState {
                copy(
                    isError = false,
                    isLoading = true
                )
            }

            repository
                .getDetails(objectNumber)
                .onFailure { handleFailure() }
                .onSuccess { handleSuccess(it) }
        }
    }

    private fun handleFailure() {
        setState {
            copy(
                isError = true,
                isLoading = false
            )
        }
    }

    private fun handleSuccess(response: CollectionDetailsResponse) {
        setState {
            copy(
                isError = false,
                isLoading = false,
                item = response.artObject
            )
        }
    }

    override fun setInitialState(): ItemDetailsScreenContract.State {
        return ItemDetailsScreenContract.State(
            item = null,
            isError = false,
            isLoading = false
        )
    }

    override fun onEventReceived(event: ItemDetailsScreenContract.Event) {
        when(event) {
            is ItemDetailsScreenContract.Event.BackNavigation -> {
                applyEffect {
                    ItemDetailsScreenContract.Effect.Navigation.ToOverview
                }
            }
            is ItemDetailsScreenContract.Event.Refreshing -> {
                getDetailsData()
            }
        }
    }
}