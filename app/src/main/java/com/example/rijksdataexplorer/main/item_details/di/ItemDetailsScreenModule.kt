package com.example.rijksdataexplorer.main.item_details.di

import com.example.rijksdataexplorer.core.data.repository.RijksRepository
import com.example.rijksdataexplorer.main.item_details.ItemDetailsViewModel
import dagger.Module
import dagger.Provides

@Module
class ItemDetailsScreenModule {

    @Provides
    @ItemDetailsScreenScope
    fun provideViewModel(
        repository: RijksRepository
    ): ItemDetailsViewModel = ItemDetailsViewModel(repository)
}