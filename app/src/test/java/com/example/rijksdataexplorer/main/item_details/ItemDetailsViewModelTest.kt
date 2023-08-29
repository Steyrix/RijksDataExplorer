package com.example.rijksdataexplorer.main.item_details

import com.example.rijksdataexplorer.MainCoroutineRule
import com.example.rijksdataexplorer.core.data.repository.RijksRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ItemDetailsViewModelTest {

    @get:Rule
    val rule = MainCoroutineRule()

    private val repo = mockk<RijksRepository>(relaxed = true)

    @Test
    fun `should create correct initial state`() {
        val expectedState = getInitialState()
        val viewModel = ItemDetailsViewModel(repo)
        val actualState = viewModel.setInitialState()

        assertEquals(actualState, expectedState)
    }

    @Test
    fun `should call repository on object number set`() {
        coEvery {
            repo.getDetails(any())
        } returns Result.success(getCollectionDetailsResponse())

        val expectedObjectNumber = "SKA-S"
        val viewModel = ItemDetailsViewModel(repo)

        viewModel.setObjectNumber(expectedObjectNumber)

        coVerify(exactly = 1) {
            repo.getDetails(expectedObjectNumber)
        }
    }

    @Test
    fun `should set success state correctly`() {
        coEvery {
            repo.getDetails(any())
        } returns Result.success(getCollectionDetailsResponse())

        val expectedState = getSuccesslState()
        val expectedObjectNumber = "SKA-S"
        val viewModel = ItemDetailsViewModel(repo)

        viewModel.setObjectNumber(expectedObjectNumber)

        val actualState = viewModel.getState().value

        assertEquals(actualState, expectedState)
    }

    @Test
    fun `should set error state correctly`() {
        coEvery {
            repo.getDetails(any())
        } returns Result.failure(Throwable())

        val expectedState = getErrorState()
        val expectedObjectNumber = "SKA-S"
        val viewModel = ItemDetailsViewModel(repo)

        viewModel.setObjectNumber(expectedObjectNumber)

        val actualState = viewModel.getState().value

        assertEquals(actualState, expectedState)
    }

    @Test
    fun `should call repository on refresh`() {
        coEvery {
            repo.getDetails(any())
        } returns Result.success(getCollectionDetailsResponse())

        val expectedObjectNumber = "SKA-S"
        val viewModel = ItemDetailsViewModel(repo)

        viewModel.setObjectNumber(expectedObjectNumber)
        viewModel.onEventReceived(ItemDetailsScreenContract.Event.Refreshing)

        coVerify(exactly = 2) {
            repo.getDetails(expectedObjectNumber)
        }
    }

    @Test
    fun `should process navigation event`() {
        coEvery {
            repo.getDetails(any())
        } returns Result.success(getCollectionDetailsResponse())

        val expectedObjectNumber = "SKA-S"
        val viewModel = ItemDetailsViewModel(repo)

        viewModel.setObjectNumber(expectedObjectNumber)
        viewModel.onEventReceived(ItemDetailsScreenContract.Event.BackNavigation)

        val effectVal: ItemDetailsScreenContract.Effect

        runBlocking {
            effectVal = viewModel.currentEffect.first()
        }

        assertEquals(effectVal, ItemDetailsScreenContract.Effect.Navigation.ToOverview)
    }
}