package com.example.rijksdataexplorer.main.overview

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
class OverviewViewModelTest {

    @get:Rule
    val rule = MainCoroutineRule()

    private val repo = mockk<RijksRepository>(relaxed = true)

    @Test
    fun `should create correct initial state and call outer methods`() {
        val expectedState = getInitialState()

        val viewModel = OverviewViewModel(repo)
        val actualState = viewModel.setInitialState()

        assertEquals(actualState, expectedState)
    }

    @Test
    fun `should call repository on init`() {
        coEvery {
            repo.getData(any())
        } returns Result.success(getCollectionResponse())

        runBlocking {
            val viewModel = OverviewViewModel(repo)
        }

        coVerify {
            repo.getData(1)
        }
    }

    @Test
    fun `should set correct state after success request without pagination possibility`() {
        val expectedState = getAfterSuccessStateWithoutPagination()

        coEvery {
            repo.getData(any())
        } returns Result.success(getCollectionResponse())

        var viewModel: OverviewViewModel

        runBlocking {
            viewModel = OverviewViewModel(repo)
        }

        val actualState = viewModel.getState().value
        assertEquals(actualState, expectedState)
    }

    @Test
    fun `should set correct state after success request with pagination possibility`() {
        val expectedState = getAfterSuccessStateWithPagination()

        coEvery {
            repo.getData(any())
        } returns Result.success(getCollectionResponseWithManyItems())

        var viewModel: OverviewViewModel

        runBlocking {
            viewModel = OverviewViewModel(repo)
        }

        val actualState = viewModel.getState().value
        assertEquals(actualState, expectedState)
    }

    @Test
    fun `should set correct state after error on first load`() {
        val expectedState = getErrorState()

        coEvery {
            repo.getData(any())
        } returns Result.failure(Throwable())

        var viewModel: OverviewViewModel

        runBlocking {
            viewModel = OverviewViewModel(repo)
        }

        val actualState = viewModel.getState().value
        assertEquals(actualState, expectedState)
    }

    @Test
    fun `should set correct state after error on pagination load`() {
        val expectedPage = 2
        val expectedState = getPaginationExhausState()

        coEvery {
            repo.getData(any())
        } returns Result.success(getCollectionResponseWithManyItems()) andThen Result.failure(Throwable())

        var viewModel: OverviewViewModel

        runBlocking {
            viewModel = OverviewViewModel(repo)
            viewModel.onEventReceived(OverviewScreenContract.Event.Paginate)
        }

        coVerify {
            repo.getData(expectedPage)
        }

        val actualState = viewModel.getState().value
        assertEquals(actualState, expectedState)
    }

    @Test
    fun `should call repository method with page parameter equal to 1 on refresh`() {
        coEvery {
            repo.getData(any())
        } returns Result.success(getCollectionResponse())

        var viewModel: OverviewViewModel

        runBlocking {
            viewModel = OverviewViewModel(repo)
            viewModel.onEventReceived(OverviewScreenContract.Event.Refreshing)
        }

        coVerify(exactly = 2) {
            repo.getData(1)
        }
    }

    @Test
    fun `should process navigation event`() {
        coEvery {
            repo.getData(any())
        } returns Result.success(getCollectionResponse())

        var viewModel: OverviewViewModel
        val effectVal: OverviewScreenContract.Effect

        runBlocking {
            viewModel = OverviewViewModel(repo)
            viewModel.onEventReceived(OverviewScreenContract.Event.ItemSelection("item_id"))
            effectVal = viewModel.currentEffect.first()
        }

        assertEquals(effectVal, OverviewScreenContract.Effect.Navigation.ToItemDetails("item_id"))
    }
}