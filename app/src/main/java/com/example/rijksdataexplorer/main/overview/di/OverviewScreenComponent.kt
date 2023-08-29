package com.example.rijksdataexplorer.main.overview.di

import com.example.rijksdataexplorer.core.di.AppComponent
import com.example.rijksdataexplorer.main.overview.OverviewViewModel
import dagger.Component

@Component(
    dependencies = [AppComponent::class],
    modules = [OverviewScreenModule::class]
)
@OverviewScreenScope
interface OverviewScreenComponent {

    @Component.Builder
    interface Builder {

        fun build(): OverviewScreenComponent

        fun appComponent(
            appComponent: AppComponent
        ): Builder
    }

    fun getViewModel(): OverviewViewModel
}