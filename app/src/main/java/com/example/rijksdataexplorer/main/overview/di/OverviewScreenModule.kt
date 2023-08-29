package com.example.rijksdataexplorer.main.overview.di

import com.example.rijksdataexplorer.core.data.repository.RijksRepository
import com.example.rijksdataexplorer.main.overview.OverviewViewModel
import dagger.Module
import dagger.Provides

@Module
class OverviewScreenModule {

    @Provides
    @OverviewScreenScope
    fun provideViewModel(
        repository: RijksRepository
    ): OverviewViewModel = OverviewViewModel(repository)
}