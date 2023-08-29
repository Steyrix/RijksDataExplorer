package com.example.rijksdataexplorer.main.item_details.di

import com.example.rijksdataexplorer.core.di.AppComponent
import com.example.rijksdataexplorer.main.item_details.ItemDetailsViewModel
import dagger.Component

@Component(
    dependencies = [AppComponent::class],
    modules = [ItemDetailsScreenModule::class]
)
@ItemDetailsScreenScope
interface ItemDetailsScreenComponent {

    @Component.Builder
    interface Builder {

        fun build(): ItemDetailsScreenComponent

        fun appComponent(
            appComponent: AppComponent
        ): Builder
    }

    fun getViewModel(): ItemDetailsViewModel
}